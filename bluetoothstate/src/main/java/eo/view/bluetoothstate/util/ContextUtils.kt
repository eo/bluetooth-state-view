package eo.view.bluetoothstate.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat


internal fun Context.getDrawableCompat(@DrawableRes drawableRes: Int): Drawable {
    val drawable = AppCompatResources.getDrawable(this, drawableRes)

    return drawable ?: throw IllegalArgumentException("Cannot get drawable!")
}

/**
 * AnimatedVectorDrawable in API 21 & 22 does not have registerAnimationCallback
 * therefore AnimatedVectorDrawableCompat is forced on those API versions
 */
internal fun Context.getAvdWithAnimationCallback(@DrawableRes drawableRes: Int): Drawable {
    val drawable = when (Build.VERSION.SDK_INT) {
        Build.VERSION_CODES.LOLLIPOP,
        Build.VERSION_CODES.M -> AnimatedVectorDrawableCompat.create(this, drawableRes)
        else -> AppCompatResources.getDrawable(this, drawableRes)
    }

    return drawable
        ?: throw IllegalArgumentException("Cannot get drawable with animation callback!")
}

@ColorInt
internal fun Context.getColorFromAttr(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrRes, typedValue, true)

    return if (typedValue.resourceId == 0) {
        typedValue.data
    } else {
        ContextCompat.getColor(this, typedValue.resourceId)
    }
}