package com.example.sgdy.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.android.sgdy.coreutil.ToastUtil;
import com.example.sgdy.R;

public class PermissionActivity extends Activity {

    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        tvContent = (TextView) findViewById(R.id.tv_content);

        findViewById(R.id.btn_current_api).setOnClickListener(v -> {
            //获取当前的api版本
            ToastUtil.showToast(this, String.valueOf(Build.VERSION.SDK_INT));
        });

        findViewById(R.id.btn_call_phone).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    //用intent启动拨打电话
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                    startActivity(intent);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
            } else {
                //用intent启动拨打电话
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                tvContent.setText(tvContent.getText() + permissions[i] + "<-->" + grantResults[i]);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
