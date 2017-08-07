package br.com.edsilfer.android_facebook_login.core.components

import android.content.Context
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewImpl

/**
 * Created by edgar on 04/08/17.
 */
class RouterImpl(val context: Context) : Router {

    override fun launchHomepageView() {
        context.startActivity(HomepageViewImpl.getIntent(context))
    }

}