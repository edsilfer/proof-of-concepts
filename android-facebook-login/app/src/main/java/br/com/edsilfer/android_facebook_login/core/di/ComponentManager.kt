package br.com.edsilfer.android_facebook_login.core.di

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.android_facebook_login.core.di.app.AppComponent
import br.com.edsilfer.android_facebook_login.core.di.app.DaggerAppComponent
import br.com.edsilfer.android_facebook_login.core.di.session.SessionComponent
import br.com.edsilfer.reactive_facebook.domain.entity.User

/**
 * Created by edgar on 26/08/17.
 */
object ComponentManager {

    private lateinit var app: App
    private lateinit var appComponent: AppComponent
    private var sessionComponent: SessionComponent? = null

    fun initializeAppComponent(app: App) {
        this.app = app
        appComponent = DaggerAppComponent
                .builder()
                .application(app)
                .build()
        appComponent.inject(app)
    }

    fun initializeSessionComponent(user: User) {
        sessionComponent = appComponent.sessionSubComponent()
                .user(user)
                .build()

        sessionComponent?.inject(app)
    }

    fun clearSessionComponent() {
        sessionComponent = null
    }

}
