package br.com.edsilfer.android_facebook_login.homepage.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import br.com.edsilfer.android_facebook_login.R
import kotlinx.android.synthetic.main.edit_picture.view.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created by edgar on 06/08/17.
 */
class EditPictureView @JvmOverloads constructor(
        context: Context,
        val attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        private var ARG_CURRENT_EDIT_ICON = 1000L
    }

    enum class EditIcon { PICTURE, VIDEO }

    private val camera by lazy { imageView_camera }
    private var currentIcon: EditIcon = EditIcon.PICTURE

    init {
        View.inflate(context, R.layout.edit_picture, this)
        startCameraAnimation()
    }

    private fun startCameraAnimation() {
        if (animateCameraIcon()) {
            val scheduleTaskExecutor = Executors.newScheduledThreadPool(5)
            scheduleTaskExecutor.scheduleAtFixedRate(
                    Runnable {
                        when (currentIcon) {
                            EditIcon.PICTURE -> {
                                currentIcon = EditIcon.VIDEO
                                camera.setImageResource(R.drawable.ic_camera_video)
                            }
                            EditIcon.VIDEO -> {
                                currentIcon = EditIcon.PICTURE
                                camera.setImageResource(R.drawable.ic_camera_picture)
                            }
                        }
                    },
                    0,
                    ARG_CURRENT_EDIT_ICON,
                    TimeUnit.MILLISECONDS
            )
        }
    }

    private fun animateCameraIcon(): Boolean {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.EditPictureView, 0, 0)
        try {
            return styledAttributes.getBoolean(R.styleable.EditPictureView_animate, false)
        } finally {
            styledAttributes.recycle()
        }
    }

}