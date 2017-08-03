package br.com.edsilfer.sample_dagger2.module_alpha.presentation.view

import android.os.Bundle
import br.com.edsilfer.sample_dagger2.R
import br.com.edsilfer.sample_dagger2.core.components.BaseActivity
import br.com.edsilfer.sample_dagger2.core.components.BasePresenter
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.presenter.AlphaPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.alpha_view.*
import javax.inject.Inject

class AlphaViewImpl : BaseActivity(), AlphaView {

    @Inject
    lateinit var presenter: AlphaPresenter

    private val launchSecondaryView by lazy { button_launchSecondaryView }

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alpha_view)

        launchSecondaryView.setOnClickListener { presenter.onLaunchSecondaryViewClick() }
    }

}
