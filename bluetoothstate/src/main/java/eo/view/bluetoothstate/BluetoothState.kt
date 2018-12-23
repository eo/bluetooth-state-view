package eo.view.bluetoothstate

interface BluetoothState {

    enum class Theme {
        ROUNDED,
        SHARP
    }

    enum class State {
        OFF,
        ON,
        SEARCH,
        SEARCHING,
        CONNECTING,
        CONNECTED
    }

    var theme: Theme
    var color: Int

    var state: State
    var animateStateChanges: Boolean
}