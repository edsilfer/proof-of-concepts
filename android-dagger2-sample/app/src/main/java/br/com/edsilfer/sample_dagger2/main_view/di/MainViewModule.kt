package br.com.edsilfer.sample_dagger2.main_view.di

import br.com.edsilfer.sample_dagger2.core.components.FakeWorker
import br.com.edsilfer.sample_dagger2.core.components.Router
import br.com.edsilfer.sample_dagger2.core.components.RouterImpl
import br.com.edsilfer.sample_dagger2.main_view.presentation.presenter.MainPresenter
import br.com.edsilfer.sample_dagger2.main_view.presentation.presenter.MainPresenterImpl
import br.com.edsilfer.sample_dagger2.main_view.presentation.view.MainView
import br.com.edsilfer.sample_dagger2.main_view.presentation.view.MainViewImpl
import dagger.Module
import dagger.Provides


/**
 * Created by edgar on 02/08/17.
 */
@Module
class MainViewModule {

    @Provides
    fun providesMainView (mainViewImpl: MainViewImpl) : MainView = mainViewImpl

    @Provides
    fun providesRouter (mainViewImpl: MainViewImpl) : Router = RouterImpl(mainViewImpl)

    @Provides
    fun providesMainPresenter(
            mainView: MainView,
            worker: FakeWorker,
            router : Router
    ): MainPresenter = MainPresenterImpl(mainView, worker, router)

}
