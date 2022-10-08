#include <jni.h>
#include <string>

jint fibN(jint n) {
    if (n <= 0) return 0;
    if (n == 1) return 1;
    return fibN(n - 1) + fibN(n - 2);
}

jint fibNI(jint n) {
    jint previous = -1;
    jint result = 1;
    jint i = 0;
    jint sum = 0;
    for (i = 0; i <= n; i++) {
        sum = result + previous;
        previous = result;
        result = sum;
    }
    return result;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_anative_MainActivity_introFromJni(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from Kuro!\nThis is performance test between Kotlin and NDK code";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_anative_MainActivity_fibN(JNIEnv *env, jobject thiz, jint n) {
    return fibN(n);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_anative_MainActivity_fibNI(JNIEnv *env, jobject thiz, jint n) {
    return fibNI(n);
}