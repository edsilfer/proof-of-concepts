package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.json

/**
 * Created by edgar on 04/08/17.
 */
object GraphAPISchema {
    val ARG_USER_PERMISSIONS = listOf(
            "public_profile",
            "email",
            "user_birthday",
            "user_about_me",
            "user_hometown",
            "user_education_history",
            "user_religion_politics",
            "user_friends",
            "user_photos"
    )
    val ARG_BIRTHDAY_DATE_FORMAT = "mm/dd/yyyy"
    val ARG_GRAPH_REQUEST_FIELDS_KEY = "fields"
    val ARG_GRAPH_REQUEST_FIELDS_VALUE = "id,name,email,gender,birthday,first_name,last_name,picture.type(large),about,cover,education,hometown,religion"

}
