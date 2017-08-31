package br.com.edsilfer.android_facebook_login.login.presentation.presenter

import br.com.edsilfer.android_facebook_login.core.components.Router
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
        private val loadingView: LoadingView
) : LoginPresenter {

    override fun attach() {
        if (isUserLoggedIn()) {
            Timber.i("LoginPresenterImpl attach")
            router.launchHomepage()
                    .doOnSubscribe { loadingView.show() }
                    .subscribe({ loadingView.dismiss() }, { loadingView.dismiss() })

        }
    }

    private fun isUserLoggedIn() = AccessToken.getCurrentAccessToken() != null

    override fun detach() {}

    /**
     * Called when Facebook launchHomepage finishes successfully
     */
    override fun onSuccess(result: LoginResult?) {
        router.launchHomepage()
                .doOnSubscribe { loadingView.show() }
                .subscribe({ loadingView.dismiss() }, { loadingView.dismiss() })
    }

    /**
     * Called when Facebook launchHomepage is cancelled
     */
    override fun onCancel() {
        Timber.e("Facebook Login was cancelled")
    }

    /**
     * Called when Facebook launchHomepage finds an error during its processing
     */
    override fun onError(error: FacebookException?) {
        Timber.e("Login with Facebook failed: ${error?.message}")
    }

}
