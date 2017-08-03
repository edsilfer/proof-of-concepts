package br.com.edsilfer.sample_dagger2.core.domain

import android.content.Context
import br.com.edsilfer.sample_dagger2.module_beta.presentation.view.BetaViewImpl

/**
 * Created by edgar on 02/08/17.
 */
class RouterImpl (val context : Context) : Router {

    override fun launchSecondaryView() {
        context.startActivity(BetaViewImpl.getIntent(context))
    }

}
