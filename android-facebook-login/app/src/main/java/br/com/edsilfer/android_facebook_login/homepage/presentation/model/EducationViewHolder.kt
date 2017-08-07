package br.com.edsilfer.android_facebook_login.homepage.presentation.model

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.edsilfer.android_facebook_login.R

/**
 * Created by edgar on 06/08/17.
 */
class EducationViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val textView_description: TextView = view.findViewById(R.id.textView_education)

}