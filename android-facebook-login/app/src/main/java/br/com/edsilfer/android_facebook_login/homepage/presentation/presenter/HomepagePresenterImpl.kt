package br.com.edsilfer.android_facebook_login.homepage.presentation.presenter

import br.com.edsilfer.android_facebook_login.facebook.domain.FacebookRepository
import br.com.edsilfer.android_facebook_login.homepage.domain.observables.PopupObservable
import br.com.edsilfer.android_facebook_login.homepage.presentation.view.HomepageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by edgar on 04/08/17.
 */
class HomepagePresenterImpl(
        val view: HomepageView,
        val facebookRepository: FacebookRepository
) : HomepagePresenter {


    override fun attach() {
        fetchUser()
        PopupObservable
                .registerInto()
                .subscribe(
                        { isPopupVisible -> if (isPopupVisible) view.lightUp() else view.lightDown() }
                )
    }

    private fun fetchUser() {
        facebookRepository
                .readUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { user -> view.loadUser(user) },
                        { error -> Timber.e("Something went wrong while reading User. Error: ${error.message}") }
                )
    }

    override fun detach() {
    }


}