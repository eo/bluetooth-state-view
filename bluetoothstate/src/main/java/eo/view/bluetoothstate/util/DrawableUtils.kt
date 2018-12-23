package eo.view.bluetoothstate.util

import android.graphics.Rect
import android.graphics.drawable.Drawable

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