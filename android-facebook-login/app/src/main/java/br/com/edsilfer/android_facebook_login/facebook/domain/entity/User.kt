package br.com.edsilfer.android_facebook_login.facebook.domain.entity

import br.com.edsilfer.tookit.core.util.InvalidData
import java.util.*

/**
 * Created by edgar on 04/08/17.
 */
data class User(
        val id: String = InvalidData.UNINITIALIZED.getString(),
        val name: String = InvalidData.UNINITIALIZED.getString(),
        val firstName: String = InvalidData.UNINITIALIZED.getString(),
        val lastName: String = InvalidData.UNINITIALIZED.getString(),
        val email: String = InvalidData.UNINITIALIZED.getString(),
        val gender: String = InvalidData.UNINITIALIZED.getString(),
        val birthday: Date = Date(),
        val profilePicture: String = InvalidData.UNINITIALIZED.getString(),
        val coverPicture: String = InvalidData.UNINITIALIZED.getString(),
        val education: List<Education> = emptyList(),
        val hometown: String = InvalidData.UNINITIALIZED.getString(),
        val religion : String = InvalidData.UNINITIALIZED.getString()

)
