package br.com.edsilfer.sample_dagger2.main_view.presentation.presenter

import br.com.edsilfer.sample_dagger2.core.components.BaseView
import br.com.edsilfer.sample_dagger2.core.components.FakeWorker
import br.com.edsilfer.sample_dagger2.core.components.Router
import br.com.edsilfer.sample_dagger2.main_view.presentation.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by edgar on 01/08/17.
 */
class MainPresenterImpl(
        val view : MainView,
        val worker: FakeWorker,
        val router : Router
) : MainPresenter {

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
