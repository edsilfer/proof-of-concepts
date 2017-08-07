package br.com.edsilfer.android_facebook_login.facebook.domain.entity

import br.com.edsilfer.tookit.core.util.InvalidData

/**
 * Created by edgar on 04/08/17.
 */
data class Education (
        val id :  String = InvalidData.UNINITIALIZED.getString(),
        val name :  String = InvalidData.UNINITIALIZED.getString(),
        val type :  String = InvalidData.UNINITIALIZED.getString(),
        val year :  String = InvalidData.UNINITIALIZED.getString(),
        val course : String = InvalidData.UNINITIALIZED.getString()
)