package br.com.edsilfer.sample_dagger2.core

import android.app.Activity
import android.app.Application
import br.com.edsilfer.sample_dagger2.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by edgar on 01/08/17.
 */
class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

}
