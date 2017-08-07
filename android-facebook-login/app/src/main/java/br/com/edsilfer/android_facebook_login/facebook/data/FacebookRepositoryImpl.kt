package br.com.edsilfer.android_facebook_login.facebook.data

import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.GraphAPIDataSource
import br.com.edsilfer.android_facebook_login.facebook.domain.FacebookRepository
import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User
import io.reactivex.Maybe

/**
 * Created by edgar on 04/08/17.
 */
class FacebookRepositoryImpl(val graphAPIDataSource: GraphAPIDataSource) : FacebookRepository {

    override fun readUser(): Maybe<User> = graphAPIDataSource.readFacebookUser()

    override fun listFriends(): Maybe<List<User>> = graphAPIDataSource.listFriends()

}
