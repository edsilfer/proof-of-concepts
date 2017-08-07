package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.homepage.di.DaggerHomepageButtonPanelComponent
import br.com.edsilfer.android_facebook_login.homepage.di.HomepageButtonPanelModule
import br.com.edsilfer.android_facebook_login.homepage.domain.observables.PopupObservable
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepageButtonPanelPresenter
import kotlinx.android.synthetic.main.homepage_button_panel.view.*
import javax.inject.Inject


/**
 * Created by edgar on 06/08/17.
 */
class HomepageButtonPanelViewImpl @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), HomepageButtonPanelView {

    @Inject
    lateinit var presenter: HomepageButtonPanelPresenter

    private val more by lazy { linearLayout_moreContainer }
    private val updateInfos by lazy { linearLayout_updateInfosContainer }
    private val activityLog by lazy { linearLayout_activityLogContainer }

    init {
        View.inflate(context, R.layout.homepage_button_panel, this)
        injectDependencies()
        addButtonsClickListeners()
    }

    private fun injectDependencies() {
        DaggerHomepageButtonPanelComponent
                .builder()
                .homepageButtonPanelModule(HomepageButtonPanelModule(this))
                .build()
                .inject(this)
    }

    private fun addButtonsClickListeners() {
        more.setOnClickListener { showPopup(it) }
        updateInfos.setOnClickListener { presenter.onUpdateInfosClick() }
        activityLog.setOnClickListener { presenter.onActivityLogClick() }
    }

    fun showPopup(view: View) {
        PopupObservable.publish(true)
        val popup = PopupMenu(context, view)
        popup.setOnDismissListener { PopupObservable.publish(false) }
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.menu_popup_more, popup.menu)
        popup.setOnMenuItemClickListener { item -> true }
        popup.show()
    }

}