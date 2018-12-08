package eo.view.bluetoothstate

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupConnectingIcon()
        setupSearchingIcon()
    }

    private fun setupConnectingIcon() {
        val avd = AnimatedVectorDrawableCompat.create(this,
            R.drawable.ic_bluetooth_connecting_rounded)
        connectingImageView.setImageDrawable(avd)

        avd?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                connectingImageView.post { avd.start() }
            }
        })
        avd?.start()
    }

    private fun setupSearchingIcon() {
        searchingImageView.setImageResource(R.drawable.ic_bluetooth_search_rounded)
    }
}
