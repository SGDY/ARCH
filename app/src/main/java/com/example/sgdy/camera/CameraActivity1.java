package com.example.sgdy.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sgdy.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CameraActivity1 extends Activity {

    private static final int REQUEST_IMAGE = 100;
    private static final int REQUEST_IMAGE2 = 101;

    Button captureButton;
    ImageView imageView;

    File destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera1);

        captureButton = (Button) findViewById(R.id.capture);
        captureButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_IMAGE);
            } catch (Exception e) {
                //如果没有找到Camera，在这里处理异常
                //还可以使用PackageManager.hasSystemFeature()方法并传入PackageManager.FEATURE_CAMERA
                //作为参数，以此来查询摄像头硬件是否存在。
                e.printStackTrace();
            }
        });

        findViewById(R.id.capture2).setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //添加附加信息来保存全尺寸的图片
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
                startActivityForResult(intent,REQUEST_IMAGE2);
            } catch (Exception e) {
                //如果没有找到Camera，在这里处理异常
                //还可以使用PackageManager.hasSystemFeature()方法并传入PackageManager.FEATURE_CAMERA
                //作为参数，以此来查询摄像头硬件是否存在。
                e.printStackTrace();
            }
        });

        imageView = (ImageView) findViewById(R.id.image);
        destination = new File(Environment.getExternalStorageDirectory(), "image.jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                //处理并显示图片 这里会拍摄照片并在"data"附加信息字段中返回按比例缩小的位图。
                Bitmap userImage = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(userImage);
            } else if (requestCode == REQUEST_IMAGE2) {
                try {
                    FileInputStream in = new FileInputStream(destination);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 10;//降低采样率10倍
                    //为了避免将全尺寸的照片加载到内存中，需要通过BitmapFactory.Options对尚未在屏幕上显示的图片进行缩放。
                    Bitmap userImage = BitmapFactory.decodeStream(in, null, options);
                    imageView.setImageBitmap(userImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
