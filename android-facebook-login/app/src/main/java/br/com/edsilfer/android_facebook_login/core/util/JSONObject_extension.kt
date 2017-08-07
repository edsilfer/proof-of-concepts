package br.com.edsilfer.android_facebook_login.core.util

import org.json.JSONObject

/**
 * Created by edgar on 04/08/17.
 */
fun JSONObject.getStringOrDefault(key: String, defaultValue: String): String {
    try {
        return getString(key)
    } catch (e: Exception) {
        return defaultValue
    }
}