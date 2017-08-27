package br.com.edsilfer.android_facebook_login.core.components

import android.content.Context
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.android_facebook_login.core.di.ComponentManager
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import br.com.edsilfer.reactive_facebook.domain.FacebookRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by edgar on 04/08/17.
 */
class RouterImpl(
        private val context: Context,
        private val facebookAPIManager: FacebookAPIManager,
        private val facebookRepository: FacebookRepository
) : Router {

    override fun launchHomepageView() {
        facebookRepository
                .readUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            ComponentManager.initializeSessionComponent(it)
                            context.startActivity(HomepageViewImpl.getIntent(context))
                        },
                        { error ->
                            Timber.e(error.message)
                        }
                )
    }

    override fun logout() {
        ComponentManager.clearSessionComponent()
        facebookAPIManager.logout()
        context.startActivity(LoginViewImpl.getIntent(context))
        (context as? AppCompatActivity)?.finish()
    }

}
