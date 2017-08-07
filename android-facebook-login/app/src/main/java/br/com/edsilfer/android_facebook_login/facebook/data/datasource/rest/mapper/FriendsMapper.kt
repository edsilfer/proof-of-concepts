package br.com.edsilfer.android_facebook_login.facebook.data.datasource.rest.mapper

import br.com.edsilfer.android_facebook_login.facebook.domain.entity.User
import com.google.gson.Gson
import org.json.JSONObject

/**
 * Created by edgar on 04/08/17.
 */
object FriendsMapper {

    fun transform(json: JSONObject): List<User> {
        val jsonFriends = json.getJSONObject("friends").getJSONArray("data")
        val parsedFriends: MutableList<User> = mutableListOf()
        (0..jsonFriends.length() - 1)
                .map { jsonFriends[it] }
                .mapTo(parsedFriends) { Gson().fromJson(it.toString(), User::class.java) }
        return parsedFriends
    }

}