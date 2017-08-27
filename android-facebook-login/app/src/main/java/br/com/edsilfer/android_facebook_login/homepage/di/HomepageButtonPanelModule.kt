package br.com.edsilfer.android_facebook_login.homepage.di

import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.core.components.RouterImpl
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenter
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenterImpl
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageButtonPanelView
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageButtonPanelViewImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import br.com.edsilfer.reactive_facebook.domain.FacebookRepository
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 06/08/17.
 */
@Module
class HomepageButtonPanelModule(
        val homepageButtonPanelView: HomepageButtonPanelView
) {

    @Provides
    fun providesRouter(
            facebookAPIManager: FacebookAPIManager,
            facebookRepository: FacebookRepository
    ): Router = RouterImpl(
            (homepageButtonPanelView as HomepageButtonPanelViewImpl).context,
            facebookAPIManager,
            facebookRepository
    )

    @Provides
    fun providesHomepageButtonPanelPresenter(router: Router): HomepageButtonPanelPresenter =
            HomepageButtonPanelPresenterImpl(router, homepageButtonPanelView)

}
