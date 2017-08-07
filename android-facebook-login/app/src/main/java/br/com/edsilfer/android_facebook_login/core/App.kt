package br.com.edsilfer.android_facebook_login.core

import br.com.edsilfer.android_facebook_login.core.di.DaggerAppComponent
import br.com.edsilfer.android_facebook_login.facebook.domain.FacebookUtil
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
        Timber.i(FacebookUtil.generatedHashCode(this))
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)
}
