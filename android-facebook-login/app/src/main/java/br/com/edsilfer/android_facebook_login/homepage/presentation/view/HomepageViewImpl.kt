package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import android.animation.Animator
import android.arch.lifecycle.LifecycleActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.WindowManager
import android.widget.LinearLayout
import br.com.edsilfer.android_facebook_login.R
import br.com.edsilfer.android_facebook_login.homepage.presentation.model.EducationViewModel
import br.com.edsilfer.android_facebook_login.homepage.presentation.presenter.HomepagePresenter
import br.com.edsilfer.reactive_facebook.domain.entity.User
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.homepage_cover_picture.*
import kotlinx.android.synthetic.main.homepage_profile_picture.*
import kotlinx.android.synthetic.main.homepage_view.*
import javax.inject.Inject


/**
 * Created by edgar on 04/08/17.
 */
class HomepageViewImpl : LifecycleActivity(), HomepageView {

    companion object {
        private val ARG_LIGHT_ANIMATION_DURATION = 500L

        fun getIntent(context: Context): Intent = Intent(context, HomepageViewImpl::class.java)
    }

    @Inject
    lateinit var user: User
    @Inject
    lateinit var picasso: Picasso
    @Inject
    lateinit var presenter: HomepagePresenter

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage_view)
        setStatusBarTransparent()
        loadUser()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun setStatusBarTransparent() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    private fun loadUser() {
        listView_education.isNestedScrollingEnabled = false
        picasso.load(user.coverPicture).centerCrop().fit().into(imageView_cover)
        picasso.load(user.profilePicture).centerCrop().fit().into(imageView_profile)
        textView_username.text = user.name
        listView_education.adapter = EducationListAdapter(user.education.map { EducationViewModel(it.course, it.name) })
        listView_education.isFocusable = false
    }

    private fun turnLightOff() {
        view_light.animate()
                .setDuration(ARG_LIGHT_ANIMATION_DURATION)
                .setListener(
                        object : Animator.AnimatorListener {
                            override fun onAnimationRepeat(p0: Animator?) {
                            }

                            override fun onAnimationEnd(p0: Animator?) {
                                view_light.visibility = LinearLayout.GONE
                            }

                            override fun onAnimationCancel(p0: Animator?) {
                            }

                            override fun onAnimationStart(p0: Animator?) {
                            }

                        }
                )
                .alpha(0f)
                .start()
    }

    private fun turnLightOn() {
        view_light.animate()
                .setDuration(ARG_LIGHT_ANIMATION_DURATION)
                .setListener(
                        object : Animator.AnimatorListener {
                            override fun onAnimationRepeat(p0: Animator?) {
                            }

                            override fun onAnimationEnd(p0: Animator?) {
                            }

                            override fun onAnimationCancel(p0: Animator?) {
                            }

                            override fun onAnimationStart(p0: Animator?) {
                                view_light.alpha = 0f
                                view_light.visibility = LinearLayout.VISIBLE
                            }

                        }
                )
                .alpha(1f)
                .start()
    }

    override fun <T> onPopupStateChange(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            upstream ->
            upstream.doOnNext {
                isPopupVisible ->
                if (isPopupVisible is Boolean && isPopupVisible) {
                    turnLightOn()
                } else {
                    turnLightOff()
                }
            }
        }
    }

}
