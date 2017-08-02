package br.com.edsilfer.sample_dagger2.secondary_view.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.com.edsilfer.sample_dagger2.R
import br.com.edsilfer.sample_dagger2.core.components.BaseActivity
import br.com.edsilfer.sample_dagger2.core.components.BasePresenter
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.presenter.SecondaryPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.secondary_view.*
import javax.inject.Inject

/**
 * Created by edgar on 02/08/17.
 */
class SecondaryViewImpl : BaseActivity(), SecondaryView {

    @Inject
    lateinit var presenter : SecondaryPresenter

    companion object {
        fun getIntent (context : Context) : Intent = Intent (context, SecondaryViewImpl::class.java)
    }

    override fun getPresenter(): BasePresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_view)

        button_tipOfTheDay.setOnClickListener { presenter.onTipOfTheDayClick() }
    }

}
