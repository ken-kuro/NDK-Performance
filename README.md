# NDK
https://stackoverflow.com/questions/46459414/why-is-my-native-c-code-running-so-much-slower-than-java-on-android
-> Optimize C code by using release build

https://stackoverflow.com/questions/67423616/how-android-runtime-compiles-java-more-efficiently-than-the-clang-c-%D0%A1-compiler
-> Recent ART and JIT optimize Java Code faster than it was on Dalvik VM

-> There is no performance advantage in writing C/C++ code for recent Android devices (starting from v7.0 Nougat - ART VM), unless you're an experienced C/C++ developer and deeply understand CPU architecture, so you can do optimization much better than Android Runtime does.

-> Also, Android NDK (C/C++) is still the only way for Android Developers to:

* Port your native C/C++ code to Android.
* Use C/C++ game engines and libraries (like Vulkan or TensorFlow).
* Use platform-specific APIs not available in Android SDK.
