package br.com.edsilfer.sample_dagger2.main_view.presentation.view

import android.os.Bundle
import br.com.edsilfer.sample_dagger2.R
import br.com.edsilfer.sample_dagger2.core.components.BaseActivity
import br.com.edsilfer.sample_dagger2.core.components.BasePresenter
import br.com.edsilfer.sample_dagger2.main_view.presentation.presenter.MainPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_view.*
import javax.inject.Inject

class MainViewImpl : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)
        button_launchSecondaryView.setOnClickListener { presenter.onLaunchSecondaryViewClick() }
    }

}
