package br.com.edsilfer.sample_dagger2.core.components

import android.support.v7.app.AppCompatActivity

/**
 * Created by edgar on 01/08/17.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onStart() {
        super.onStart()
        getPresenter().attachTo(this)
    }

    override fun onStop() {
        super.onStop()
        getPresenter().detachFrom(this)
    }

    abstract fun getPresenter() : BasePresenter

}
