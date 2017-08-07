package br.com.edsilfer.android_facebook_login.login.di

import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.core.components.RouterImpl
import br.com.edsilfer.android_facebook_login.facebook.domain.FacebookRepository
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenter
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenterImpl
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginView
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 03/08/17.
 */
@Module
class LoginModule {

    @Provides
    fun providesLoginView(loginViewImpl: LoginViewImpl): LoginView = loginViewImpl

    @Provides
    fun providesRouter (loginViewImpl: LoginViewImpl) : Router = RouterImpl(loginViewImpl)

    @Provides
    fun providesLoginPresenter(
            loginView: LoginView,
            router : Router
    ): LoginPresenter = LoginPresenterImpl(loginView, router)

}
