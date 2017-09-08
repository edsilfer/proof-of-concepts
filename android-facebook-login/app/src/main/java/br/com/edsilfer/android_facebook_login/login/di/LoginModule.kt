package br.com.edsilfer.android_facebook_login.login.di

import android.arch.lifecycle.Lifecycle
import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.core.components.RouterImpl
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManager
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenter
import br.com.edsilfer.android_facebook_login.login.presentation.presenter.LoginPresenterImpl
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import br.com.edsilfer.toolkit.core.components.SchedulersCoupler
import br.com.edsilfer.toolkit.core.components.SchedulersCouplerImpl
import br.com.edsilfer.toolkit.presentation.view.LoadingView
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 03/08/17.
 */
@Module
class LoginModule {

    @Provides
    fun providesSchedulersCoupler () : SchedulersCoupler = SchedulersCouplerImpl()

    @Provides
    fun providesLifecycle(loginViewImpl: LoginViewImpl): Lifecycle = loginViewImpl.lifecycle

    @Provides
    fun providesLoadingView(loginViewImpl: LoginViewImpl): LoadingView = LoadingView(loginViewImpl.supportFragmentManager)

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
            loadingView: LoadingView,
            schedulersCoupler: SchedulersCoupler
    ): LoginPresenter = LoginPresenterImpl(
            router,
            lifecycle,
            loadingView,
            schedulersCoupler
    )

}
