package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json

import br.com.edsilfer.tookit.core.util.InvalidData

/**
 * Created by edgar on 04/08/17.
 */
data class JSONEducation(
        val id: String = InvalidData.UNINITIALIZED.getString(),
        val school: JSONSchool?,
        val type: String = InvalidData.UNINITIALIZED.getString(),
        val year: JSONYear?,
        val concentration: List<JSONConcentration>? = emptyList()
)