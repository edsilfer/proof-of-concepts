package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageButtonPanelViewImpl
import dagger.Component

/**
 * Created by edgar on 06/08/17.
 */
@Component(modules = arrayOf(HomepageButtonPanelModule::class))
interface HomepageButtonPanelComponent {

    fun inject(homepageButtonPanelViewImpl: HomepageButtonPanelViewImpl)

}