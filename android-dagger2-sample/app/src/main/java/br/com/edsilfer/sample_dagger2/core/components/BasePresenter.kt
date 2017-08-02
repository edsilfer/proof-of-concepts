package br.com.edsilfer.sample_dagger2.core.components

/**
 * Created by edgar on 01/08/17.
 */
interface BasePresenter {

    fun attachTo(view: BaseView)

    fun detachFrom(view: BaseView)

}
