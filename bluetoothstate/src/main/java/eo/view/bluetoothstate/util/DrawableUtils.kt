package eo.view.bluetoothstate.util

import android.graphics.Rect
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

internal fun Drawable.scaleToFit(destRect: Rect, padding: Rect) {
    if (destRect.isEmpty || destRect.height() == 0) {
        bounds = destRect
    }

    val availableWidth = destRect.width() - padding.left - padding.right
    val availableHeight = destRect.height() - padding.top - padding.bottom
    val availableAspectRatio = availableWidth.toFloat() / availableHeight

    val drawableAspectRatio = intrinsicWidth.toFloat() / intrinsicHeight
    var drawableWidth = availableWidth
    var drawableHeight = availableHeight

    if (availableAspectRatio > drawableAspectRatio) {
        drawableWidth = (availableHeight * drawableAspectRatio).toInt()
    } else {
        drawableHeight = (availableWidth / drawableAspectRatio).toInt()
    }

    val drawableOffsetX = padding.left + (availableWidth - drawableWidth) / 2
    val drawableOffsetY = padding.top + (availableHeight - drawableHeight) / 2

    setBounds(
        drawableOffsetX,
        drawableOffsetY,
        drawableOffsetX + drawableWidth,
        drawableOffsetY + drawableHeight
    )
}

internal fun Drawable.startInfiniteAvdAnimation() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this is AnimatedVectorDrawable) {
        registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                scheduleSelf({
                    start()
                }, 0L)
            }
        })
        start()
    } else if (this is AnimatedVectorDrawableCompat) {
        registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                scheduleSelf({
                    start()
                }, 0L)
            }
        })
        start()
    }
}

internal fun Drawable.stopAvdAnimation() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this is AnimatedVectorDrawable) {
        clearAnimationCallbacks()
        stop()
    } else if (this is AnimatedVectorDrawableCompat) {
        clearAnimationCallbacks()
        stop()
    }
}

internal fun Drawable.startOneShotAvdAnimation() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && this is AnimatedVectorDrawable) {
        start()
    } else if (this is AnimatedVectorDrawableCompat) {
        start()
    }
}