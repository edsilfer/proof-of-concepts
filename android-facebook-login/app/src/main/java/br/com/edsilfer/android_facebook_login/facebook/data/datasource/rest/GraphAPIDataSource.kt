package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest

import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User
import io.reactivex.Maybe


/**
 * Created by edgar on 04/08/17.
 */
interface GraphAPIDataSource {

    fun readFacebookUser(): Maybe<User>

    fun listFriends(): Maybe<List<User>>
}
