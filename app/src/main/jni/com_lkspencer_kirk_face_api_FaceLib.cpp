#include <jni.h>
#include <android/log.h>

namespace com_lkspencer_kirk_face_api {
  static void StartLearning (JNIEnv *env, jclass clazz, jstring name) {

  }

  static void StopLearning (JNIEnv *env, jclass clazz) {

  }

  static jboolean DetectMatch (JNIEnv *env, jclass clazz, jstring name) {
    return 0;
  }

  static jstring HelloJni (JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("Hello JNI, C++ Rocks and Zac can read minds...");
  }

  // { "JavaMethodName", "JNITypeSignature",
  static JNINativeMethod method_table[] = {
    { "StartLearning", "(Ljava/lang/String;)V", (void *) StartLearning },
    { "StopLearning", "()V", (void *) StopLearning },
    { "DetectMatch", "(Ljava/lang/String;)Z", (void *) DetectMatch },
    { "HelloJni", "()Ljava/lang/String;", (void *) HelloJni },
  };
}

using namespace com_lkspencer_kirk_face_api;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
  JNIEnv* env;
  if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
    return -1;
  }

  jclass clazz = env->FindClass("com/lkspencer/kirk/face/api/FaceLib");
  if (clazz) {
    env->RegisterNatives(clazz, method_table, sizeof(method_table) / sizeof(method_table[0]));
    env->DeleteLocalRef(clazz);
    return JNI_VERSION_1_6;
  }

  return -1;
}