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
    }

    private fun setupConnectingAnimations() {
        setInfiniteAnimatedVectorDrawable(
            roundedConnectingImageView,
            R.drawable.ic_bluetooth_connecting_rounded
        )

        setInfiniteAnimatedVectorDrawable(
            sharpConnectingImageView,
            R.drawable.ic_bluetooth_connecting_sharp
        )
    }

    private fun setupSearchingAnimations() {
        setInfiniteAnimatedVectorDrawable(
            roundedSearchingImageView,
            R.drawable.ic_bluetooth_searching_rounded
        )

        setInfiniteAnimatedVectorDrawable(
            sharpSearchingImageView,
            R.drawable.ic_bluetooth_searching_sharp
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
}
