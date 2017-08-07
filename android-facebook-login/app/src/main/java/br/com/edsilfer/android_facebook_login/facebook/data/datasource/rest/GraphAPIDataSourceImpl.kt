package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest

import android.os.Bundle
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json.GraphAPISchema
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json.JSONUser
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.mapper.FriendsMapper
import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.mapper.UserMapper
import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.gson.Gson
import io.reactivex.Maybe
import io.reactivex.MaybeEmitter
import org.json.JSONObject
import timber.log.Timber


/**
 * Created by edgar on 04/08/17.
 */
class GraphAPIDataSourceImpl : GraphAPIDataSource {

    enum class Errors(val message: String) {
        USER_IS_NULL("Graph API User response returned null"),
        INVALID_ACCESS_TOKEN("An active access token must be used to query information about the current user.")
    }

    override fun readFacebookUser(): Maybe<User> {
        return Maybe.create<User> { emitter ->
            val accessToken = AccessToken.getCurrentAccessToken()
            val request = GraphRequest.newMeRequest(accessToken) { user, response ->
                if (response.error != null) emitter.onError(IllegalStateException(response.error.errorMessage))
                parseReadUserResponse(user, emitter)
            }
            setReadUserRequestParameters(request, GraphAPISchema.ARG_GRAPH_REQUEST_FIELDS_VALUE)
            executeReadUserRequest(accessToken, request)
        }
    }

    private fun parseReadUserResponse(user: JSONObject?, emitter: MaybeEmitter<User>) {
        try {
            if (user != null) emitter.onSuccess(UserMapper.transform(Gson().fromJson(user.toString(), JSONUser::class.java)))
            Timber.e(Errors.USER_IS_NULL.message)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    /**
     * With Graph API v2.0 and above, calls to /me/friends return only the friends who also use your app that have also granted the user_friends permission.
     */
    override fun listFriends(): Maybe<List<User>> {
        val accessToken = AccessToken.getCurrentAccessToken()
        return Maybe.create<List<User>> { emitter ->
            val request = GraphRequest.newMeRequest(accessToken) {
                friends, response ->
                if (response.error != null) emitter.onError(IllegalStateException(response.error.errorMessage))
                emitter.onSuccess(FriendsMapper.transform(friends))
            }
            setReadUserRequestParameters(request, "friends")
            executeReadUserRequest(accessToken, request)
        }
    }

    private fun setReadUserRequestParameters(request: GraphRequest, fields: String) {
        val parameters = Bundle()
        parameters.putString(GraphAPISchema.ARG_GRAPH_REQUEST_FIELDS_KEY, fields)
        request.parameters = parameters
    }

    private fun executeReadUserRequest(accessToken: AccessToken, request: GraphRequest) {
        if (!accessToken.isExpired) {
            request.executeAndWait()
        } else {
            Timber.e(Errors.INVALID_ACCESS_TOKEN.message)
        }
    }

}
