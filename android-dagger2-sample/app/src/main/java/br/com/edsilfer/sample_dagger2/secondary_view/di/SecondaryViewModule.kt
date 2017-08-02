package br.com.edsilfer.sample_dagger2.secondary_view.di

import br.com.edsilfer.sample_dagger2.core.dialog.presentation.view.DialogView
import br.com.edsilfer.sample_dagger2.core.dialog.presentation.view.DialogViewImpl
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.presenter.SecondaryPresenter
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.presenter.SecondaryPresenterImpl
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.view.SecondaryView
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.view.SecondaryViewImpl
import dagger.Module
import dagger.Provides

/**
 * Created by edgar on 02/08/17.
 */
@Module
class SecondaryViewModule {

    @Provides
    fun providesDialogView (secondaryViewImpl: SecondaryViewImpl) : DialogView = DialogViewImpl(secondaryViewImpl)

    @Provides
    fun providesSecondaryView (secondaryViewImpl: SecondaryViewImpl) : SecondaryView = secondaryViewImpl

    @Provides
    fun providesSecondaryPresenter(
            secondaryView: SecondaryView,
            dialogView: DialogView
    ): SecondaryPresenter = SecondaryPresenterImpl(secondaryView, dialogView)

}
