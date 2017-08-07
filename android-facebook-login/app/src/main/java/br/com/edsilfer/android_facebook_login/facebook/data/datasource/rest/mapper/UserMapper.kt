package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.mapper

import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json.*
import br.com.edsilfer.android_facebook_login.facebook.domain.entity.Education
import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User
import br.com.edsilfer.tookit.core.util.InvalidData
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by edgar on 04/08/17.
 */
object UserMapper {

    fun transform(jsonUser: JSONUser): User {
        return User(
                id = jsonUser.id,
                email = jsonUser.email,
                name = jsonUser.name,
                firstName = jsonUser.first_name,
                lastName = jsonUser.last_name,
                gender = jsonUser.gender,
                birthday = parse(jsonUser.birthday),
                profilePicture = parse(jsonUser.picture),
                coverPicture = parse(jsonUser.cover),
                education = parse(jsonUser.education),
                hometown = parse(jsonUser.hometown),
                religion = jsonUser.religion
        )
    }

    private fun parse(unformattedBirthday: String): Date {
        try {
            val formatter = SimpleDateFormat(GraphAPISchema.ARG_BIRTHDAY_DATE_FORMAT)
            return formatter.parse(unformattedBirthday)
        } catch (e: Exception) {
            Timber.e("Unable to parse user birthday. Error: ${e.message}")
        }
        return Date()
    }

    private fun parse(jsonPicture: JSONPicture?): String {
        if (jsonPicture != null && jsonPicture.data != null) {
            return jsonPicture.data.url
        }
        return InvalidData.INVALID.getString()
    }

    private fun parse(jsonCover: JSONCover?): String {
        return jsonCover?.source ?: InvalidData.INVALID.getString()
    }

    private fun parse(jsonEducation: List<JSONEducation>?): List<Education> {
        return jsonEducation?.map { EducationMapper.transform(it) } ?: emptyList()
    }

    private fun parse(jsonHometown: JSONHometown?): String {
        return jsonHometown?.name ?: InvalidData.UNINITIALIZED.getString()
    }

}
