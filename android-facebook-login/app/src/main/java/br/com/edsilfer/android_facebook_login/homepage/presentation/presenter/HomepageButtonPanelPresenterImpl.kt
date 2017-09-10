package br.com.edsilfer.android_facebook_login.homepage.presentation.presenter

import android.view.MenuItem
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.core.components.Router
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageViewOptions

/**
 * Created by edgar on 06/08/17.
 */
class HomepageButtonPanelPresenterImpl(
        private val router: Router,
        private val view: HomepageViewOptions
) : HomepageButtonPanelPresenter {
    override fun onActivityLogClick() {}

    override fun onUpdateInfosClick() {}

    override fun onPopupItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                router.logout()
                return true
            }
        }
        return false
    }
}
