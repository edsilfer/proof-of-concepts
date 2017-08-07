package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.homepage.presentation.model.EducationViewHolder
import br.com.edsilfer.android_facebook_login.homepage.presentation.model.EducationViewModel

/**
 * Created by edgar on 06/08/17.
 */
class EducationListAdapter(val data: List<EducationViewModel>) : RecyclerView.Adapter<EducationViewHolder>() {

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(
            parent: ViewGroup?,
            viewType: Int
    ): EducationViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val view = inflater.inflate(R.layout.education_list_item, parent, false)
        return EducationViewHolder(view)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.textView_description.text = Html.fromHtml("Went to ${data[position].course} at <b>${data[position].school}</b>")
    }

}