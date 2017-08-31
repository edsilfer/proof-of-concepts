package br.com.edsilfer.android_facebook_login.core.components

import io.reactivex.Completable

/**
 * Created by edgar on 04/08/17.
 */
interface Router {

    fun launchHomepage(): Completable

    fun logout()

}
