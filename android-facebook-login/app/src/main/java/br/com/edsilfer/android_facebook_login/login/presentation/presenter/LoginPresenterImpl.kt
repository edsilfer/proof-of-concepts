package br.com.edsilfer.android_facebook_login.login.presentation.presenter

import android.arch.lifecycle.Lifecycle
import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.toolkit.core.components.BasePresenter
import br.com.edsilfer.toolkit.core.components.SchedulersCoupler
import br.com.edsilfer.toolkit.presentation.view.LoadingView
import com.facebook.AccessToken
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import timber.log.Timber

/**
 * Created by edgar on 03/08/17.
 */
class LoginPresenterImpl(
        private val router: Router,
        lifecycle: Lifecycle,
        private val loadingView: LoadingView,
        private val schedulers: SchedulersCoupler
) : BasePresenter(lifecycle), LoginPresenter {

    private val ARG_MESSAGE_001 = "Login was cancelled"
    private val ARG_MESSAGE_002 = "Login failed"

    override fun onStart() {
        super.onStart()
        if (isUserLoggedIn()) routeToHomepage()
    }

    private fun routeToHomepage() {
        addDisposable(router.launchHomepage()
                .compose(schedulers.convertToAsyncCompletable())
                .compose(loadingView.completableChain())
                .subscribe()
        )
    }

    private fun isUserLoggedIn() = AccessToken.getCurrentAccessToken() != null

    override fun onSuccess(result: LoginResult?) = routeToHomepage()

    override fun onCancel() = Timber.e(ARG_MESSAGE_001)

    override fun onError(error: FacebookException?) = Timber.e(ARG_MESSAGE_002 + error?.message)

}
