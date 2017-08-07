package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json

import br.com.edsilfer.tookit.core.util.InvalidData

/**
 * Created by edgar on 04/08/17.
 */
data class JSONData(
        val is_silhouette: Boolean = false,
        val url: String = InvalidData.UNINITIALIZED.getString()
)