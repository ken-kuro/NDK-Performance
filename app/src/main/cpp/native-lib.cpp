#include <jni.h>
#include <string>
#include <cmath>

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

jint loopN(jint count) {
    jint result = 0;
    for (jint i = 0; i < count; i++) {
        for (jint j = 0; j < 100; j++) {
            result += 34432; result++;
            result -= 34431; result--;
        } }
    return result;
}

int isPrimeN(jlong a) {
    jlong n;
    if (a == 2) {
        return 1;
    } else if (a <= 1 || a % 2 == 0) {
        return 0;
    }
    jlong max = sqrt(a);
    for (n = 3; n <= max; n += 2) {
        if (a % n == 0) { return 0; }
    }
    return 1;
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
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_anative_MainActivity_loopN(JNIEnv *env, jobject thiz, jint n) {
    return loopN(n);
}
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_anative_MainActivity_isPrimeN(JNIEnv *env, jobject thiz, jlong a) {
    return isPrimeN(a);
}