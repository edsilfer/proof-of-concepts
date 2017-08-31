package br.com.edsilfer.android_facebook_login.core

import br.com.edsilfer.android_facebook_login.core.di.app.AppComponent
import br.com.edsilfer.android_facebook_login.core.di.app.DaggerAppComponent
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by edgar on 03/08/17.
 */
class App : DaggerApplication() {

    @Inject
    lateinit var componentManager: ComponentManager

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = componentManager.startAppComponent()
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent.builder()
                    .application(this)
                    .build()

}
