package com.lkspencer.kirk.face;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import com.lkspencer.kirk.face.api.FaceLib;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener {

  private final String TAG = "asdf";
  private CameraBridgeViewBase mOpenCvCameraView;

  private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
    @Override
    public void onManagerConnected(int status) {
      switch (status) {
        case LoaderCallbackInterface.SUCCESS:
        {
          Log.i(TAG, "OpenCV loaded successfully");
          mOpenCvCameraView.enableView();
        } break;
        default:
        {
          super.onManagerConnected(status);
        } break;
      }
    }
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
      Log.i(TAG, "called onCreate");
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.activity_main);
    mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.HelloOpenCvView);
    mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
    mOpenCvCameraView.setCvCameraViewListener(this);
    String message = FaceLib.HelloJni();
    Toast.makeText(this, "value: " + message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onPause() {
    super.onPause();
    if (mOpenCvCameraView != null)
      mOpenCvCameraView.disableView();
  }

  @Override
  protected void onResume() {
    super.onResume();
    OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCameraViewStarted(int width, int height) {

  }

  @Override
  public void onCameraViewStopped() {

  }

  @Override
  public Mat onCameraFrame(Mat inputFrame) {
    return inputFrame;
  }

  public void onDestroy() {
    super.onDestroy();
    if (mOpenCvCameraView != null)
      mOpenCvCameraView.disableView();
  }

}
