package br.com.edsilfer.android_facebook_login.homepage.presentation.presenter

import br.com.edsilfer.android_facebook_login.homepage.domain.observables.PopupObservable
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageView

/**
 * Created by edgar on 04/08/17.
 */
class HomepagePresenterImpl(val view: HomepageView) : HomepagePresenter {

    override fun attach() {
        PopupObservable
                .registerInto()
                .subscribe(
                        { isPopupVisible -> if (isPopupVisible) view.lightUp() else view.lightDown() }
                )
    }

    override fun detach() {
    }

}