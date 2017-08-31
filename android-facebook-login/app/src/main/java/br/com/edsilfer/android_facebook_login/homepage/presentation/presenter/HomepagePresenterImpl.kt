package br.com.edsilfer.android_facebook_login.homepage.presentation.presenter

import android.arch.lifecycle.Lifecycle
import br.com.edsilfer.android_facebook_login.homepage.domain.observables.PopupObservable
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageView
import br.com.edsilfer.tookit.core.components.BasePresenter

/**
 * Created by edgar on 04/08/17.
 */
class HomepagePresenterImpl(
        val view: HomepageView,
        lifecycle: Lifecycle
) : BasePresenter(lifecycle), HomepagePresenter {

    override fun onStart() {
        super.onStart()
        registerForPopUpLifecycleNotifications()
    }

    private fun registerForPopUpLifecycleNotifications() {
        addDisposable(
                PopupObservable
                        .registerInto()
                        .subscribe(
                                { isPopupVisible ->
                                    if (isPopupVisible) view.lightUp() else view.lightDown()
                                }
                        )
        )
    }

}
