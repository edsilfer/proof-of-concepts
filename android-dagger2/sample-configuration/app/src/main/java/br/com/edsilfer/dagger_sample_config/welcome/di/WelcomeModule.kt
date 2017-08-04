package br.com.edsilfer.dagger_sample_config.welcome.di

import br.com.edsilfer.dagger_sample_config.welcome.presentation.presenter.WelcomePresenter
import br.com.edsilfer.dagger_sample_config.welcome.presentation.presenter.WelcomePresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 03/08/17.
 */
@Module
class WelcomeModule {

    @Provides
    fun provides () : WelcomePresenter = WelcomePresenterImpl()

}
