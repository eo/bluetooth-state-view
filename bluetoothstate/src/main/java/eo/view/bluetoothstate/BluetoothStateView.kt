package eo.view.bluetoothstate

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import eo.view.bluetoothstate.BluetoothState.State
import eo.view.bluetoothstate.BluetoothState.Theme


class BluetoothStateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.bluetoothStateStyle
) : View(context, attrs, defStyleAttr), BluetoothState {

    private val bluetoothStateDrawable: BluetoothStateDrawable

    private val drawableCallback = object : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {
            removeCallbacks(what)
        }

        override fun invalidateDrawable(who: Drawable) {
            invalidate()
        }

        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
            postDelayed(what, `when`)
        }
    }

    override var theme: BluetoothState.Theme
        get() = bluetoothStateDrawable.theme
        set(value) {
            bluetoothStateDrawable.theme = value
            invalidate()
        }

    override var color: Int
        get() = bluetoothStateDrawable.color
        set(value) {
            bluetoothStateDrawable.color = value
            invalidate()
        }

    override var state: BluetoothState.State
        get() = bluetoothStateDrawable.state
        set(value) {
            bluetoothStateDrawable.state = value
            invalidate()
        }

    override var animateStateChanges: Boolean
        get() = bluetoothStateDrawable.animateStateChanges
        set(value) {
            bluetoothStateDrawable.animateStateChanges = value
        }


    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BluetoothStateView,
            defStyleAttr,
            R.style.Widget_BluetoothState
        )

        val themes = Theme.values()
        val themeIndex = typedArray.getInt(
            R.styleable.BluetoothStateView_bluetoothTheme,
            Theme.SHARP.ordinal
        ).coerceIn(0, themes.lastIndex)

        bluetoothStateDrawable = BluetoothStateDrawable(context, themes[themeIndex])
        bluetoothStateDrawable.callback = drawableCallback
        bluetoothStateDrawable.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)

        color = typedArray.getColor(R.styleable.BluetoothStateView_bluetoothColor, color)

        val states = State.values()
        val stateIndex = typedArray.getInt(
            R.styleable.BluetoothStateView_bluetoothState,
            State.ON.ordinal
        ).coerceIn(0, states.lastIndex)

        state = states[stateIndex]

        animateStateChanges = typedArray.getBoolean(
            R.styleable.BluetoothStateView_bluetoothAnimateStateChanges,
            animateStateChanges
        )

        typedArray.recycle()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        bluetoothStateDrawable.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bluetoothStateDrawable.setBounds(left, top, right, bottom)
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)

        bluetoothStateDrawable.setPadding(left, top, right, bottom)
    }

    override fun setPaddingRelative(start: Int, top: Int, end: Int, bottom: Int) {
        super.setPaddingRelative(start, top, end, bottom)

        when (layoutDirection) {
            LAYOUT_DIRECTION_RTL -> bluetoothStateDrawable.setPadding(end, top, start, bottom)
            else -> bluetoothStateDrawable.setPadding(start, top, end, bottom)
        }
    }
}