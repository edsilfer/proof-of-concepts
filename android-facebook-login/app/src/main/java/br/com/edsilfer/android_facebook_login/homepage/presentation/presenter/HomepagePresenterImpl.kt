package br.com.edsilfer.android_facebook_login.homepage.presentation.presenter

import br.com.edsilfer.android_facebook_login.homepage.domain.observables.PopupObservable
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by edgar on 04/08/17.
 */
class HomepagePresenterImpl(val view: HomepageView) : HomepagePresenter {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun attach() {
        if (compositeDisposable.isDisposed) compositeDisposable = CompositeDisposable()
        registerForPopUpLifecycleNotifications()
    }

    private fun registerForPopUpLifecycleNotifications() {
        compositeDisposable.add(
                PopupObservable
                        .registerInto()
                        .subscribe(
                                { isPopupVisible -> if (isPopupVisible) view.lightUp() else view.lightDown() }
                        )
        )
    }

    override fun detach() {
        compositeDisposable.clear()
    }

}