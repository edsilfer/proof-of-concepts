package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.core.components.RouterImpl
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManager
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenter
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenterImpl
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewOptions
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 06/08/17.
 */
@Module
class HomepageViewOptionsModule(val homepageButtonPanelView: HomepageViewOptions) {

    @Provides
    fun providesRouter(
            componentManager2: ComponentManager,
            facebookAPIManager: FacebookAPIManager
    ): Router = RouterImpl(
            homepageButtonPanelView.getContext(),
            facebookAPIManager,
            componentManager2
    )

    @Provides
    fun providesHomepageButtonPanelPresenter(
            router: Router
    ): HomepageButtonPanelPresenter =
            HomepageButtonPanelPresenterImpl(router, homepageButtonPanelView)

}
