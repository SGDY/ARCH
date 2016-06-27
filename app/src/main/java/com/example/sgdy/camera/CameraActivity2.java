package com.example.sgdy.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.android.sgdy.coreutil.LogUtil;
import com.example.sgdy.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraActivity2 extends Activity implements SurfaceHolder.Callback
        , Camera.ShutterCallback, Camera.PictureCallback {

    Camera mCamera;
    SurfaceView mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);

        mPreview = (SurfaceView) findViewById(R.id.preview);
        mPreview.getHolder().addCallback(this);
        //需要支持Android3.0之前的版本
        mPreview.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mCamera = Camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCamera.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(mPreview.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters params = mCamera.getParameters();
        //得到设备支持的尺寸，并选择第一个尺寸（最大）
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();//params.getSupportedPictureSizes()
        for (Camera.Size size : sizes) {
            LogUtil.i(size.width + " --- " + size.height);
        }

        Camera.Size selected = sizes.get(2);
        params.setPreviewSize(selected.width, selected.height);

        List<Camera.Size> sizePictures = params.getSupportedPictureSizes();
        for (Camera.Size size : sizePictures) {
            LogUtil.i(size.width + " <==> " + size.height);
        }
        params.setPictureSize(sizePictures.get(2).width,sizePictures.get(2).height);
        //要想旋转摄像头输出的数据，需要调用Camera.Parameters的setRotation()方法。这个方法的实现效果取决于设备。
        //也许会旋转实际输出的图像，也许会通过旋转参数更新EXIF数据，或者两者皆有。
//        params.setRotation(0);
        mCamera.setParameters(params);
        //改变摄像头的预览方向。调用Camera.setDisplayOrientation来旋转得到的数据，从而匹配Activity的方向。
        //有效值为0、90、180、270，0表示默认的横屏显示。主要影响视频采集前预览数据该如何在Surface上进行绘制。
        mCamera.setDisplayOrientation(90);

        mCamera.startPreview();
    }

    public void onCancelClick(View view) {
        finish();
    }

    public void onSnapClick(View view) {
        //拍摄照片
        mCamera.takePicture(this,null,null,this);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        try {
            //将图片保存到外部存储器中
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            FileOutputStream out = new FileOutputStream(new File(directory, "picture.jpg"));
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //必须重新启动预览
        mCamera.startPreview();
    }
    //Camera回调方法
    @Override
    public void onShutter() {
        Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
    }
}
