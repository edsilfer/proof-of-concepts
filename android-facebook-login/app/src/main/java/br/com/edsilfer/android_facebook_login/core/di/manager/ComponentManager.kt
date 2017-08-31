package br.com.edsilfer.android_facebook_login.core.di.manager

import br.com.edsilfer.android_facebook_login.core.di.app.AppComponent
import io.reactivex.Completable

/**
 * Created by edgar on 30/08/17.
 */
interface ComponentManager {

    fun startAppComponent() : AppComponent

    fun startSessionComponent(): Completable

    fun stopSessionComponent()

}