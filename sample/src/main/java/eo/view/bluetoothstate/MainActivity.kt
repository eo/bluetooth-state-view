package eo.view.bluetoothstate

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupConnectingAnimations()
        setupSearchingAnimations()
        setupTurningOnOffAnimations()
    }

    private fun setupConnectingAnimations() {
        setInfiniteAnimatedVectorDrawable(
            roundedConnectingImageView,
            R.drawable.avd_bluetooth_connecting_rounded
        )

        setInfiniteAnimatedVectorDrawable(
            sharpConnectingImageView,
            R.drawable.avd_bluetooth_connecting_sharp
        )
    }

    private fun setupSearchingAnimations() {
        setInfiniteAnimatedVectorDrawable(
            roundedSearchingImageView,
            R.drawable.avd_bluetooth_searching_rounded
        )

        setInfiniteAnimatedVectorDrawable(
            sharpSearchingImageView,
            R.drawable.avd_bluetooth_searching_sharp
        )
    }

    private fun setupTurningOnOffAnimations() {
        setTwoStatesInfiniteAnimatedVectorDrawable(
            sharpTurningOnOffImageView,
            R.drawable.avd_bluetooth_turning_off_sharp,
            R.drawable.avd_bluetooth_turning_on_sharp,
            500L
        )
    }

    private fun setInfiniteAnimatedVectorDrawable(
        imageView: ImageView,
        @DrawableRes avdResource: Int
    ) {
        val avd = AnimatedVectorDrawableCompat.create(this, avdResource)
        imageView.setImageDrawable(avd)

        avd?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                imageView.post { avd.start() }
            }
        })
        avd?.start()
    }

    private fun setTwoStatesInfiniteAnimatedVectorDrawable(
        imageView: ImageView,
        @DrawableRes avdResource1: Int,
        @DrawableRes avdResource2: Int,
        delayBetweenStates: Long
    ) {
        val avd1 = AnimatedVectorDrawableCompat.create(this, avdResource1)
        val avd2 = AnimatedVectorDrawableCompat.create(this, avdResource2)

        imageView.setImageDrawable(avd1)

        avd1?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                imageView.postDelayed({
                    imageView.setImageDrawable(avd2)
                    avd2?.start()
                }, delayBetweenStates)
            }
        })

        avd2?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                imageView.postDelayed({
                    imageView.setImageDrawable(avd1)
                    avd1?.start()
                }, delayBetweenStates)
            }
        })

        avd1?.start()
    }
}
