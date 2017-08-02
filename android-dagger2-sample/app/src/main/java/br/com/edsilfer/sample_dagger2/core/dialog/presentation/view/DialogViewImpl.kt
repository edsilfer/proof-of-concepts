package br.com.edsilfer.sample_dagger2.core.dialog.presentation.view

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import br.com.edsilfer.sample_dagger2.R
import br.com.edsilfer.sample_dagger2.core.dialog.domain.DialogConfiguration
import com.tradeforce.core_toolkit.commons.util.InvalidData
import io.reactivex.Completable
import io.reactivex.CompletableEmitter

/**
 * Created by edgar on 02/08/17.
 */
class DialogViewImpl(val context: Context) : DialogView {

    override fun showInformation(configuration: DialogConfiguration): Completable {
        return Completable.create { emiter ->
            val dialog = AlertDialog.Builder(context)
            setConfiguration(dialog, configuration)
            onPositiveClick(dialog, emiter)
            onNegativeClicked(dialog, emiter)
            dialog.show()
        }

    }

    private fun setConfiguration(dialog: AlertDialog.Builder, configuration: DialogConfiguration) {
        dialog.setTitle(if (InvalidData.isInvalid(configuration.titleId)) configuration.title else context.getString(configuration.titleId))
        dialog.setMessage(if (InvalidData.isInvalid(configuration.messageId)) configuration.message else context.getString(configuration.messageId))
        dialog.setCancelable(configuration.isModal)
    }

    private fun onPositiveClick(dialog: AlertDialog.Builder, emiter: CompletableEmitter) {
        dialog.setPositiveButton(
                context.getString(R.string.str_button_label_ok),
                { dialogInterface: DialogInterface, i: Int ->
                    emiter.onComplete()
                }
        )
    }

    private fun onNegativeClicked(dialog: AlertDialog.Builder, emiter: CompletableEmitter) {
        dialog.setNegativeButton(
                context.getString(R.string.str_button_label_cancel),
                { dialogInterface: DialogInterface, i: Int ->
                    emiter.onComplete()
                }
        )
    }

}
