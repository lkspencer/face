package com.lkspencer.kirk.face.api;

/**
 * Created by Kirk on 6/11/2015.
 * JNI calls will go here
 */
public class FaceLib {

  public native static void StartLearning(String name);
  public native static void StopLearning();
  public native static boolean DetectMatch(String name);

  static {
    // name of the module from the Android.mk file
    System.loadLibrary("com_lkspencer_kirk_face_api_FaceLib");
  }
}
