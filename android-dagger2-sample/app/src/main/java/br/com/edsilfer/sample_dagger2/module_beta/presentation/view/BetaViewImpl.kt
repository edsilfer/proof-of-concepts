package br.com.edsilfer.sample_dagger2.module_beta.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.edsilfer.sample_dagger2.R
import br.com.edsilfer.sample_dagger2.core.components.BaseActivity
import br.com.edsilfer.sample_dagger2.core.components.BasePresenter
import br.com.edsilfer.sample_dagger2.module_beta.presentation.presenter.BetaPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.beta_view.*
import javax.inject.Inject

/**
 * Created by edgar on 02/08/17.
 */
class BetaViewImpl : BaseActivity(), BetaView {

    @Inject
    lateinit var presenter : BetaPresenter

    private val tipOfTheDay by lazy { button_tipOfTheDay }

    companion object {
        fun getIntent (context : Context) : Intent = Intent (context, BetaViewImpl::class.java)
    }

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beta_view)

        tipOfTheDay.setOnClickListener { presenter.onTipOfTheDayClick() }
    }

}
