package br.com.edsilfer.android_facebook_login.core

import br.com.edsilfer.android_facebook_login.core.di.ComponentManager
import br.com.edsilfer.android_facebook_login.core.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


/**
 * Created by edgar on 03/08/17.
 */
class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        ComponentManager.initializeAppComponent(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()
}