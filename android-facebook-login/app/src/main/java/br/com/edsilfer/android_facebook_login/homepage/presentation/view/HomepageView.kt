package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import io.reactivex.ObservableTransformer

/**
 * Created by edgar on 04/08/17.
 */
interface HomepageView {

    fun <T> onPopupStateChange(): ObservableTransformer<T, T>

}