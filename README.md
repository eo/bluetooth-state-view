# Bluetooth State View
![Icon](/sample/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

Material design animated Bluetooth state view for Android

[ ![Download](https://api.bintray.com/packages/eo/view/bluetoothstate/images/download.svg) ](https://bintray.com/eo/view/bluetoothstate/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg)](https://github.com/eo/battery-meter-view/blob/master/LICENSE)
[![License](https://img.shields.io/badge/minSdkVersion-19-red.svg)](https://developer.android.com/about/dashboards/)

Download
--------
```groovy
dependencies {
  implementation 'eo.view:bluetoothstate:1.0.0'
}
```

You also have the option to download individual animated vector drawables:
```groovy
dependencies {
  implementation 'eo.avd:bluetoothconnecting:1.0.0'
  implementation 'eo.avd:bluetoothsearching:1.0.0'
  implementation 'eo.avd:bluetoothturningonoff:1.0.0'
}
```

Usage
-----
Library contains both `BluetoothStateView` and `BluetoothStateDrawable` classes. Following XML attributes have corresponding class properties.

```xml
<eo.view.bluetoothstate.BluetoothStateView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:bluetoothAnimateStateChanges="true"
    app:bluetoothColor="@android:color/black"
    app:bluetoothTheme="rounded"
    app:bluetoothState="searching" />
```

Themes & Animations
---------------
|   | Connecting | Searching | Turning On & Off
| - | ---------- | --------- | ----------------
**Rounded** | ![Rounded Connecting](/images/connecting_rounded.gif) | ![Rounded Searching](/images/searching_rounded.gif) | ![Rounded Turning On/Off](/images/turningonoff_rounded.gif)
**Sharp** | ![Sharp Connecting](/images/connecting_sharp.gif) | ![Sharp Searching](/images/searching_sharp.gif) | ![Sharp Turning On/Off](/images/turningonoff_sharp.gif)

Sample
------
Download sample app under releases to play with the library

License
-------

    Copyright 2018 Erdem Orman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
