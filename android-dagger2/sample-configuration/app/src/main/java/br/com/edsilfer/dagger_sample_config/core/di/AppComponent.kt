package br.com.edsilfer.dagger_sample_config.core.di

import br.com.edsilfer.dagger_sample_config.core.App
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
                AppModule::class,
                AndroidInjectionModule::class,
                ActivitiesBuilder::class
        )
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}
