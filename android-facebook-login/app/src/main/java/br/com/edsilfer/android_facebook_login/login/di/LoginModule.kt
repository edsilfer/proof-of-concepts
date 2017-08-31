package br.com.edsilfer.android_facebook_login.login.di

import android.arch.lifecycle.Lifecycle
import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.core.components.RouterImpl
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManager
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenter
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenterImpl
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import br.com.edsilfer.tookit.presentation.view.LoadingScreenImpl
import br.com.edsilfer.tookit.presentation.view.contract.LoadingScreen
import br.com.edsilfer.tookit.presentation.view.contract.LoadingView
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 03/08/17.
 */
@Module
class LoginModule {

    @Provides
    fun providesLifecycle(loginViewImpl: LoginViewImpl): Lifecycle = loginViewImpl.lifecycle

    @Provides
    fun providesLoadingView(loginViewImpl: LoginViewImpl): LoadingView = loginViewImpl

    @Provides
    fun providesLoadingScreen(loginViewImpl: LoginViewImpl): LoadingScreen =
            LoadingScreenImpl(loginViewImpl.supportFragmentManager)

    @Provides
    fun providesRouter(
            loginViewImpl: LoginViewImpl,
            componentManager2: ComponentManager,
            facebookAPIManager: FacebookAPIManager
    ): Router = RouterImpl(
            loginViewImpl,
            facebookAPIManager,
            componentManager2
    )

    @Provides
    fun providesLoginPresenter(
            router: Router,
            lifecycle: Lifecycle,
            loadingView: LoadingView
    ): LoginPresenter = LoginPresenterImpl(
            router,
            lifecycle,
            loadingView
    )

}
