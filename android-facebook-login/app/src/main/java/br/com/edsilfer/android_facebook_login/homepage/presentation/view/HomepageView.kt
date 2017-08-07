package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User

/**
 * Created by edgar on 04/08/17.
 */
interface HomepageView {

    fun loadUser(user: User)

    fun lightDown()

    fun lightUp()

}