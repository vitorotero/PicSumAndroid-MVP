package br.com.vitorotero.picsum.utils

import android.animation.Animator
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.addListener
import kotlin.math.max

abstract class TransictionsUtils {

    companion object {
        fun revealActivity(view: View, positionX: Int, positionY: Int, radius: Float, duration: Long) {
            val circularReveal: Animator =
                ViewAnimationUtils.createCircularReveal(view, positionX, positionY, 0f, radius)

            circularReveal.duration = duration
            circularReveal.interpolator = AccelerateInterpolator()
            view.visibility = View.VISIBLE
            circularReveal.start()
        }

        fun unRevealActivity(
            view: View,
            revealX: Int,
            revealY: Int,
            radius: Float,
            duration: Long,
            onEnd: () -> Unit
        ) {
            val finalRadius = (max(view.width, view.height) * 1.1).toFloat()
            val circularReveal = ViewAnimationUtils.createCircularReveal(view, revealX, revealY, finalRadius, radius)

            circularReveal.duration = duration
            circularReveal.addListener(onEnd = { onEnd.invoke() })
            circularReveal.start()
        }
    }

}