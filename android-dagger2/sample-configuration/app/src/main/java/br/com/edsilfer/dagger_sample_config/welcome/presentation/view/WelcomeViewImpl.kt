package br.com.edsilfer.dagger_sample_config.welcome.presentation.view

import android.os.Bundle
import br.com.edsilfer.dagger_sample_config.R
import br.com.edsilfer.dagger_sample_config.welcome.presentation.presenter.WelcomePresenter
import br.com.edsilfer.tookit.core.components.BaseActivity
import br.com.edsilfer.tookit.core.components.BasePresenter
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by edgar on 03/08/17.
 */
class WelcomeViewImpl : BaseActivity(), WelcomeView {
    @Inject
    lateinit var presenter : WelcomePresenter

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_view)
    }

}
