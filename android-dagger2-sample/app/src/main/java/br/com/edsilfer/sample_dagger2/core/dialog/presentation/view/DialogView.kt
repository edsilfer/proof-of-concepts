package br.com.edsilfer.sample_dagger2.core.dialog.presentation.view

import br.com.edsilfer.sample_dagger2.core.dialog.domain.DialogConfiguration
import io.reactivex.Completable

/**
 * Created by edgar on 02/08/17.
 */
interface DialogView {

    fun showInformation(configuration : DialogConfiguration) : Completable

}
