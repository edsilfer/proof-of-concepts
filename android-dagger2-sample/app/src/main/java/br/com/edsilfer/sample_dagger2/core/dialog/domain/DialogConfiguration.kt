package br.com.edsilfer.sample_dagger2.core.dialog.domain

import com.tradeforce.core_toolkit.commons.util.InvalidData

/**
 * Created by edgar on 02/08/17.
 */
data class DialogConfiguration(
        val titleId: Int = InvalidData.UNINITIALIZED.getInt(),
        val title: String = InvalidData.UNINITIALIZED.getString(),
        val messageId: Int = InvalidData.UNINITIALIZED.getInt(),
        val message: String = InvalidData.UNINITIALIZED.getString(),
        val isModal: Boolean = true
)
