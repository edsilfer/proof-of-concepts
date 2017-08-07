package br.com.edsilfer.android_facebook_login.homepage.domain.observables

import io.reactivex.subjects.PublishSubject

/**
 * Created by edgar on 06/08/17.
 */
object PopupObservable {

    private val publisher: PublishSubject<Boolean> = PublishSubject.create()

    fun registerInto(): PublishSubject<Boolean> = publisher

    fun publish(isPopupVisible: Boolean) {
        publisher.onNext(isPopupVisible)
    }

}