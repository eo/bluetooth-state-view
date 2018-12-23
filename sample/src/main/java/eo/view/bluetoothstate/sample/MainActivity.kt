package eo.view.bluetoothstate.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eo.view.bluetoothstate.BluetoothState
import eo.view.bluetoothstate.BluetoothStateView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupOnOffStateClicker(roundedTurningOnOffBluetoothState)
        setupOnOffStateClicker(sharpTurningOnOffBluetoothState)
    }

    private fun setupOnOffStateClicker(stateView: BluetoothStateView) {
        stateView.setOnClickListener {
            stateView.state = if (stateView.state == BluetoothState.State.ON) {
                BluetoothState.State.OFF
            } else {
                BluetoothState.State.ON
            }
        }
    }
}
