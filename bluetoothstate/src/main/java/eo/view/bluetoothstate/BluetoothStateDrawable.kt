package eo.view.bluetoothstate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import eo.view.bluetoothstate.BluetoothState.State
import eo.view.bluetoothstate.BluetoothState.Theme
import eo.view.bluetoothstate.util.*


class BluetoothStateDrawable(
    private val context: Context,
    theme: Theme = Theme.ROUNDED
) : Drawable(), BluetoothState {

    private var drawable: Drawable
    private val padding = Rect()

    private val drawableCallback = object : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {
            unscheduleSelf(what)
        }

        override fun invalidateDrawable(who: Drawable) {
            invalidateSelf()
        }

        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
            scheduleSelf(what, `when`)
        }
    }

    override var theme: Theme = theme
        set(value) {
            if (field != value) {
                field = value
                updateDrawableForCurrentStateAndTheme()
            }
        }

    @ColorInt
    override var color: Int = Color.BLACK
        set(value) {
            field = value
            DrawableCompat.setTint(drawable, value)
            invalidateSelf()
        }

    override var state: State = State.ON
        set(value) {
            field = value
            updateDrawableForCurrentStateAndTheme()
        }

    override var animateStateChanges: Boolean = true


    init {
        drawable = context.getDrawableCompat(
            if (theme == Theme.ROUNDED) {
                R.drawable.vd_bluetooth_on_rounded
            } else {
                R.drawable.vd_bluetooth_on_sharp
            }
        )

        color = context.getColorFromAttr(R.attr.colorControlNormal)
    }

    override fun onBoundsChange(bounds: Rect) {
        drawable.scaleToFit(bounds, padding)
    }

    override fun draw(canvas: Canvas) {
        drawable.draw(canvas)
    }

    override fun getIntrinsicHeight() = drawable.intrinsicHeight

    override fun getIntrinsicWidth() = drawable.intrinsicWidth

    override fun setAlpha(alpha: Int) {
        drawable.alpha = alpha
    }

    override fun getOpacity(): Int {
        return drawable.opacity
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        drawable.colorFilter = colorFilter
    }

    override fun getPadding(paddingOut: Rect): Boolean {
        if (padding.left == 0 && padding.top == 0 && padding.right == 0 && padding.bottom == 0) {
            return super.getPadding(padding)
        }

        paddingOut.set(this.padding)
        return true
    }

    fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        padding.set(left, top, right, bottom)
        drawable.scaleToFit(bounds, padding)
    }

    private fun updateDrawableForCurrentStateAndTheme() {
        when (state) {
            State.ON -> setOnStateDrawable()
            State.OFF -> setOffStateDrawable()
            State.SEARCH -> setSearchStateDrawable()
            State.SEARCHING -> setSearchingStateDrawable()
            State.CONNECTING -> setConnectingStateDrawable()
            State.CONNECTED -> setConnectedStateDrawable()
        }
    }

    private fun setOnStateDrawable() {
        if (animateStateChanges) {
            setOneShotAnimatedVectorDrawable(
                if (theme == Theme.ROUNDED) {
                    R.drawable.avd_bluetooth_turning_on_rounded
                } else {
                    R.drawable.avd_bluetooth_turning_on_sharp
                }
            )
        } else {
            setVectorDrawable(
                if (theme == Theme.ROUNDED) {
                    R.drawable.vd_bluetooth_on_rounded
                } else {
                    R.drawable.vd_bluetooth_on_sharp
                }
            )
        }
    }

    private fun setOffStateDrawable() {
        if (animateStateChanges) {
            setOneShotAnimatedVectorDrawable(
                if (theme == Theme.ROUNDED) {
                    R.drawable.avd_bluetooth_turning_off_rounded
                } else {
                    R.drawable.avd_bluetooth_turning_off_sharp
                }
            )
        } else {
            setVectorDrawable(
                if (theme == Theme.ROUNDED) {
                    R.drawable.vd_bluetooth_off_rounded
                } else {
                    R.drawable.vd_bluetooth_off_sharp
                }
            )
        }
    }

    private fun setSearchStateDrawable() {
        setVectorDrawable(
            if (theme == Theme.ROUNDED) {
                R.drawable.vd_bluetooth_search_rounded
            } else {
                R.drawable.vd_bluetooth_search_sharp
            }
        )
    }

    private fun setSearchingStateDrawable() {
        setInfiniteAnimatedVectorDrawable(
            if (theme == Theme.ROUNDED) {
                R.drawable.avd_bluetooth_searching_rounded
            } else {
                R.drawable.avd_bluetooth_searching_sharp
            }
        )
    }

    private fun setConnectingStateDrawable() {
        setInfiniteAnimatedVectorDrawable(
            if (theme == Theme.ROUNDED) {
                R.drawable.avd_bluetooth_connecting_rounded
            } else {
                R.drawable.avd_bluetooth_connecting_sharp
            }
        )
    }

    private fun setConnectedStateDrawable() {
        setVectorDrawable(
            if (theme == Theme.ROUNDED) {
                R.drawable.vd_bluetooth_connected_rounded
            } else {
                R.drawable.vd_bluetooth_connected_sharp
            }
        )
    }

    private fun setVectorDrawable(@DrawableRes vdRes: Int) {
        val vd = context.getDrawableCompat(vdRes)
        vd.scaleToFit(bounds, padding)

        replaceDrawable(vd)
        invalidateSelf()
    }

    private fun setOneShotAnimatedVectorDrawable(@DrawableRes avdRes: Int) {
        val avd = context.getDrawableCompat(avdRes)
        avd.scaleToFit(bounds, padding)

        replaceDrawable(avd)
        avd.startOneShotAvdAnimation()
        invalidateSelf()
    }

    private fun setInfiniteAnimatedVectorDrawable(@DrawableRes avdRes: Int) {
        val avd = context.getAvdWithAnimationCallback(avdRes)
        avd.scaleToFit(bounds, padding)

        replaceDrawable(avd)
        avd.startInfiniteAvdAnimation()
        invalidateSelf()
    }

    private fun replaceDrawable(newDrawable: Drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            newDrawable.colorFilter = drawable.colorFilter
        }
        DrawableCompat.setTint(newDrawable, color)
        newDrawable.alpha = drawable.alpha

        drawable.stopAvdAnimation()
        drawable.callback = null
        drawable = newDrawable
        drawable.callback = drawableCallback
    }
}