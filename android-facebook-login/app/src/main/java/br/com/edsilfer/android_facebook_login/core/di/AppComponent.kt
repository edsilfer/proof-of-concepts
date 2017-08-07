package br.com.edsilfer.android_facebook_login.core.di

import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.android_facebook_login.core.di.ActivitiesBuilder
import br.com.edsilfer.android_facebook_login.core.di.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by edgar on 03/08/17.
 */
@Singleton
@Component(
        modules = arrayOf(
                AndroidInjectionModule::class,
                ActivitiesBuilder::class,
                AppModule::class
        )
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()



}
