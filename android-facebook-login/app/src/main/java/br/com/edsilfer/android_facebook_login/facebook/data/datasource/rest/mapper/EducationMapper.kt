package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.mapper

import br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json.JSONEducation
import br.com.edsilfer.android_facebook_login.facebook.domain.entity.Education
import br.com.edsilfer.tookit.core.util.InvalidData

/**
 * Created by edgar on 04/08/17.
 */
object EducationMapper {

    fun transform(jsonEducation: JSONEducation): Education {
        return Education(
                id = jsonEducation.id,
                name = jsonEducation.school?.name ?: InvalidData.INVALID.getString(),
                type = jsonEducation.type,
                year = jsonEducation.year?.name ?: InvalidData.INVALID.getString(),
                course = parseCourse(jsonEducation)
        )
    }

    private fun parseCourse(jsonEducation: JSONEducation): String {
        if (jsonEducation.concentration != null && jsonEducation.concentration.isNotEmpty()) {
            return jsonEducation.concentration.first().name
        }

        return jsonEducation.type
    }

}