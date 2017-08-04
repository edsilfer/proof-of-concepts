package br.com.edsilfer.dagger_sample_config.welcome.presentation.presenter

import timber.log.Timber

/**
 * Created by edgar on 03/08/17.
 */
class WelcomePresenterImpl : WelcomePresenter {

    override fun attach() {
        Timber.i("${WelcomePresenterImpl::class.simpleName} attached successfully")
    }

    override fun detach() {
        Timber.i("${WelcomePresenterImpl::class.simpleName} detached successfully")
    }

}
