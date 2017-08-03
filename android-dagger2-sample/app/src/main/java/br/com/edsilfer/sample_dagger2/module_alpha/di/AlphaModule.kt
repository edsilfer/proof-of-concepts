package br.com.edsilfer.sample_dagger2.module_alpha.di

import br.com.edsilfer.sample_dagger2.core.domain.FakeWorker
import br.com.edsilfer.sample_dagger2.core.domain.Router
import br.com.edsilfer.sample_dagger2.core.domain.RouterImpl
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.presenter.AlphaPresenter
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.presenter.AlphaPresenterImpl
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.view.AlphaView
import br.com.edsilfer.sample_dagger2.module_alpha.presentation.view.AlphaViewImpl
import dagger.Module
import dagger.Provides


/**
 * Created by edgar on 02/08/17.
 */
@Module
class AlphaModule {

    @Provides
    fun providesMainView (mainViewImpl: AlphaViewImpl) : AlphaView = mainViewImpl

    @Provides
    fun providesRouter (mainViewImpl: AlphaViewImpl) : Router = RouterImpl(mainViewImpl)

    @Provides
    fun providesMainPresenter(
            mainView: AlphaView,
            worker: FakeWorker,
            router : Router
    ): AlphaPresenter = AlphaPresenterImpl(mainView, worker, router)

}
