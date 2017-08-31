package br.com.edsilfer.android_facebook_login.login.presentation.presenter

import android.arch.lifecycle.Lifecycle
import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.tookit.core.components.BasePresenter
import br.com.edsilfer.tookit.presentation.view.contract.LoadingView
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
        private val loadingView: LoadingView
) : BasePresenter(lifecycle), LoginPresenter {

    override fun onStart() {
        super.onStart()
        if (isUserLoggedIn()) {
            addDisposable(
                    router.launchHomepage()
                            .doOnSubscribe { loadingView.show() }
                            .subscribe(
                                    { loadingView.dismiss() },
                                    { loadingView.dismiss() }
                            )
            )

        }
    }

    private fun isUserLoggedIn() = AccessToken.getCurrentAccessToken() != null

    /**
     * Facebook login finished successfully
     */
    override fun onSuccess(result: LoginResult?) {
        addDisposable(
                router.launchHomepage()
                        .doOnSubscribe { loadingView.show() }
                        .subscribe(
                                { loadingView.dismiss() },
                                { loadingView.dismiss() }
                        )
        )
    }

    /**
     * Facebook login cancelled
     */
    override fun onCancel() {
        Timber.e("Facebook Login was cancelled")
    }

    /**
     * Facebook login finds an error
     */
    override fun onError(error: FacebookException?) {
        Timber.e("Login with Facebook failed: ${error?.message}")
    }

}
