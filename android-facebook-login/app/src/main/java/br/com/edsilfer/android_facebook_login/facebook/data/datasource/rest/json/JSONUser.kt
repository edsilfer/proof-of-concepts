package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json

import br.com.edsilfer.tookit.core.util.InvalidData

/**
 * Created by edgar on 04/08/17.
 */
data class JSONUser(
        val id: String = InvalidData.UNINITIALIZED.getString(),
        val name: String = InvalidData.UNINITIALIZED.getString(),
        val email: String = InvalidData.UNINITIALIZED.getString(),
        val gender: String = InvalidData.UNINITIALIZED.getString(),
        val birthday: String = InvalidData.UNINITIALIZED.getString(),
        val first_name: String = InvalidData.UNINITIALIZED.getString(),
        val last_name: String = InvalidData.UNINITIALIZED.getString(),
        val picture: JSONPicture?,
        val cover: JSONCover?,
        val education: List<JSONEducation>? = emptyList(),
        val hometown: JSONHometown?,
        val religion: String = InvalidData.UNINITIALIZED.getString()
)