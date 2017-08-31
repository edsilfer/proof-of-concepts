package br.com.edsilfer.android_facebook_login.core.components

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.edsilfer.android_facebook_login.core.di.manager.ComponentManager
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl
import br.com.edsilfer.android_facebook_login.login.presentation.view.LoginViewImpl
import br.com.edsilfer.reactive_facebook.domain.FacebookAPIManager
import io.reactivex.Completable

/**
 * Created by edgar on 04/08/17.
 */
class RouterImpl(
        private val context: Context,
        private val facebookAPIManager: FacebookAPIManager,
        private val componentManager: ComponentManager
) : Router {

    override fun launchHomepage(): Completable =
            componentManager.startSessionComponent()
                    .doOnComplete {
                        doDefaultLaunching(context, HomepageViewImpl.getIntent(context), true)
                    }

    override fun logout() {
        facebookAPIManager.logout()
        componentManager.stopSessionComponent()
        doDefaultLaunching(context, LoginViewImpl.getIntent(context), true)
    }

    private fun doDefaultLaunching(context: Context, intent: Intent, finishCaller: Boolean) {
        context.startActivity(intent)
        if (finishCaller) {
            when (context) {
                is Activity -> context.finish()
                is AppCompatActivity -> context.finish()
            }
        }
    }
}
