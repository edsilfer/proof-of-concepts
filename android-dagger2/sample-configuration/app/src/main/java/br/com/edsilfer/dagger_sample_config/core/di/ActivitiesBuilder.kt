package br.com.edsilfer.dagger_sample_config.core.di

import br.com.edsilfer.dagger_sample_config.welcome.di.WelcomeModule
import br.com.edsilfer.dagger_sample_config.welcome.presentation.view.WelcomeViewImpl
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by edgar on 03/08/17.
 */
@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector(modules = arrayOf(WelcomeModule::class))
    abstract fun provides(): WelcomeViewImpl

}
