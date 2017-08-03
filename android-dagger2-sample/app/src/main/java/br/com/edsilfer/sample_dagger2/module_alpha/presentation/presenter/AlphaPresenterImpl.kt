package br.com.edsilfer.sample_dagger2.module_alpha.presentation.presenter

import br.com.edsilfer.sample_dagger2.core.components.BaseView
import br.com.edsilfer.sample_dagger2.core.domain.FakeWorker
import br.com.edsilfer.sample_dagger2.core.domain.Router
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.view.AlphaView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by edgar on 01/08/17.
 */
class AlphaPresenterImpl(
        val view : AlphaView,
        val worker: FakeWorker,
        val router : Router
) : AlphaPresenter {

    override fun attachTo(view: BaseView) {
        Timber.i("View attached successfully")

        worker.doFakeJob()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    override fun detachFrom(view: BaseView) {
        Timber.i("View detached successfully")
    }

    override fun onLaunchSecondaryViewClick() {
        router.launchSecondaryView()


    }

}
