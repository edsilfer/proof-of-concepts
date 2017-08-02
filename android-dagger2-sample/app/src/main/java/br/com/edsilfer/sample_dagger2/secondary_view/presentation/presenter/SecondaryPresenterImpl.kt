package br.com.edsilfer.sample_dagger2.secondary_view.presentation.presenter

import br.com.edsilfer.sample_dagger2.R
import br.com.edsilfer.sample_dagger2.core.components.BaseView
import br.com.edsilfer.sample_dagger2.core.dialog.domain.DialogConfiguration
import br.com.edsilfer.sample_dagger2.core.dialog.presentation.view.DialogView
import br.com.edsilfer.sample_dagger2.secondary_view.presentation.view.SecondaryView
import timber.log.Timber

/**
 * Created by edgar on 02/08/17.
 */
class SecondaryPresenterImpl(
        val view : SecondaryView,
        val dialogView: DialogView
) : SecondaryPresenter {

    override fun attachTo(view: BaseView) {
        Timber.i("View attached successfully")
    }

    override fun detachFrom(view: BaseView) {
        Timber.i("View detached successfully")
    }

    override fun onTipOfTheDayClick() {
        dialogView.showInformation(configuration = DialogConfiguration(
                titleId = R.string.str_dialog_title_information,
                messageId = R.string.str_tip_of_the_day_dagger_works_if_set_correctly
        ))
                .doOnComplete {
                    Timber.i("Dialog ok button was clicked")
                }
                .subscribe()
    }

}
