package br.com.edsilfer.sample_dagger2.module_beta.di

import br.com.edsilfer.sample_dagger2.core.dialog.presentation.view.DialogView
import br.com.edsilfer.sample_dagger2.core.dialog.presentation.view.DialogViewImpl
import br.com.edsilfer.sample_dagger2.module_beta.presentation.presenter.BetaPresenter
import br.com.edsilfer.sample_dagger2.module_beta.presentation.presenter.BetaPresenterImpl
import br.com.edsilfer.sample_dagger2.module_beta.presentation.view.BetaView
import br.com.edsilfer.sample_dagger2.module_beta.presentation.view.BetaViewImpl
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 02/08/17.
 */
@Module
class BetaModule {

    @Provides
    fun providesDialogView (secondaryViewImpl: BetaViewImpl) : DialogView = DialogViewImpl(secondaryViewImpl)

    @Provides
    fun providesSecondaryView (secondaryViewImpl: BetaViewImpl) : BetaView = secondaryViewImpl

    @Provides
    fun providesSecondaryPresenter(
            secondaryView: BetaView,
            dialogView: DialogView
    ): BetaPresenter = BetaPresenterImpl(secondaryView, dialogView)

}
