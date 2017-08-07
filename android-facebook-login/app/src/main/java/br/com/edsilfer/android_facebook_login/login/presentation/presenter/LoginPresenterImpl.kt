package br.com.edsilfer.android_facebook_login.login.presentation.presenter

import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginView
import com.facebook.AccessToken
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import timber.log.Timber

/**
 * Created by edgar on 03/08/17.
 */
class LoginPresenterImpl(
        val view: LoginView,
        val router: Router
) : LoginPresenter {

    override fun attach() {
        if (AccessToken.getCurrentAccessToken() != null) router.launchHomepageView()
    }

    override fun detach() {}

    override fun onSuccess(result: LoginResult?) {
        router.launchHomepageView()
    }

    override fun onCancel() {
        Timber.e("Facebook Login was cancelled")
    }

    override fun onError(error: FacebookException?) {
        Timber.e("Login with Facebook failed: ${error?.message}")
    }

}
