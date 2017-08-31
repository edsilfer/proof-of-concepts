package br.com.edsilfer.android_facebook_login.homepage.presentation.presenter

import android.view.MenuItem

/**
 * Created by edgar on 06/08/17.
 */
interface HomepageButtonPanelPresenter {

    fun onPopupItemClick(item: MenuItem): Boolean

    fun onActivityLogClick()

    fun onUpdateInfosClick()

}
