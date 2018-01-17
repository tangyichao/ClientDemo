#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jlong

JNICALL
Java_com_com_tangyc_retrofit_client_SoSocketJNI_initJNI(
        JNIEnv *env,
        jobject /* this */,jstring) {
    return 0;
}
extern "C"
JNIEXPORT jobject

JNICALL
Java_com_com_tangyc_retrofit_client_SoSocketJNI_initType(
        JNIEnv *env,
        jobject /* this */,jlong,jstring,jobject) {
    return 0;
}
extern "C"
JNIEXPORT jobject

JNICALL
Java_com_com_tangyc_retrofit_client_SoSocketJNI_getType(
        JNIEnv *env,
        jobject /* this */,jlong,jobject) {
    return 0;
}
