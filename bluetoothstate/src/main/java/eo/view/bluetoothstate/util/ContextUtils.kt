package eo.view.bluetoothstate.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

internal fun Context.createVectorDrawable(@DrawableRes vdRes: Int): VectorDrawableCompat {
    val vd = VectorDrawableCompat.create(resources, vdRes, theme)

    return vd ?: throw IllegalArgumentException("Cannot create vector drawable!")
}

internal fun Context.createAnimatedVectorDrawable(@DrawableRes avdRes: Int): AnimatedVectorDrawableCompat {
    val avd = AnimatedVectorDrawableCompat.create(this, avdRes)

    return avd ?: throw IllegalArgumentException("Cannot create animated vector drawable!")
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