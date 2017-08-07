package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenter
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenterImpl
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageButtonPanelView
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 06/08/17.
 */
@Module
class HomepageButtonPanelModule(val homepageButtonPanelView: HomepageButtonPanelView) {

    @Provides
    fun providesHomepageButtonPanelPresenter(): HomepageButtonPanelPresenter = HomepageButtonPanelPresenterImpl(homepageButtonPanelView)

}