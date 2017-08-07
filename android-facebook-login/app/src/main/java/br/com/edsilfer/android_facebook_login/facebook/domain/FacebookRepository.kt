package br.com.edsilfer.android_facebook_login.facebook.domain

import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User
import io.reactivex.Maybe

/**
 * Created by edgar on 04/08/17.
 */
interface FacebookRepository {

    fun readUser(): Maybe<User>

    fun listFriends(): Maybe<List<User>>

}
