package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json

import br.com.edsilfer.tookit.core.util.InvalidData

/**
 * Created by edgar on 04/08/17.
 */
data class JSONCover(
        val id: String = InvalidData.UNINITIALIZED.getString(),
        val offset_x: Int = InvalidData.UNINITIALIZED.getInt(),
        val offset_y: Int = InvalidData.UNINITIALIZED.getInt(),
        val source: String = InvalidData.UNINITIALIZED.getString()
)