package br.com.edsilfer.android_facebook_login.facebook.domain

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Base64
import java.security.MessageDigest


/**
 * Created by edgar on 06/08/17.
 */
object FacebookUtil {

    enum class Errors(val message: String) { HASH_NOT_FOUND("Hash code for application unable to be generated") }

    fun generatedHashCode(context: Context): String {
        @SuppressLint("PackageManagerGetSignatures")
        val info = context
                .packageManager
                .getPackageInfo(
                        "br.com.edsilfer.android_facebook_login",
                        PackageManager.GET_SIGNATURES
                )

        for (signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            return Base64.encodeToString(md.digest(), Base64.DEFAULT)
        }

        return Errors.HASH_NOT_FOUND.message
    }

}