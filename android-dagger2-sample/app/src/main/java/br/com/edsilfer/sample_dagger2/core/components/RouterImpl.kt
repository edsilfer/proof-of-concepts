package br.com.edsilfer.sample_dagger2.core.components

import android.content.Context
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.view.SecondaryViewImpl

/**
 * Created by edgar on 02/08/17.
 */
class RouterImpl (val context : Context) : Router {

    override fun launchSecondaryView() {
        context.startActivity(SecondaryViewImpl.getIntent(context))
    }

}
