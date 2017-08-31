package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.core.App
import br.com.edsilfer.android_facebook_login.homepage.di.DaggerHomepageViewOptionsComponent
import br.com.edsilfer.android_facebook_login.homepage.di.HomepageViewOptionsModule
import br.com.edsilfer.android_facebook_login.homepage.domain.observables.PopupObservable
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenter
import kotlinx.android.synthetic.main.homepage_button_panel.view.*
import javax.inject.Inject


/**
 * Created by edgar on 06/08/17.
 */
class HomepageViewOptionsImpl @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), HomepageViewOptions {

    @Inject
    lateinit var presenter: HomepageButtonPanelPresenter

    init {
        View.inflate(context, R.layout.homepage_button_panel, this)
        injectDependencies()
        addButtonsClickListeners()
    }

    private fun injectDependencies() {
        DaggerHomepageViewOptionsComponent
                .builder()
                .appComponent((context.applicationContext as App).appComponent)
                .homepageViewOptionsModule(HomepageViewOptionsModule(this))
                .build()
                .inject(this)
    }

    private fun addButtonsClickListeners() {
        linearLayout_moreContainer.setOnClickListener { showPopup(it) }
        linearLayout_updateInfosContainer.setOnClickListener { presenter.onUpdateInfosClick() }
        linearLayout_activityLogContainer.setOnClickListener { presenter.onActivityLogClick() }
    }

    private fun showPopup(view: View) {
        PopupObservable.publish(true)
        val popup = PopupMenu(context, view)
        popup.setOnDismissListener { PopupObservable.publish(false) }
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_popup_more, popup.menu)
        popup.setOnMenuItemClickListener { presenter.onPopupItemClick(it) }
        popup.show()
    }

}
