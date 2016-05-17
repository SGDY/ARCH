package com.example.sgdy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sgdy.animator.FlipperActivity;
import com.example.sgdy.animator.LoadingAnimActivity;
import com.example.sgdy.customview.CustomViewActivity1;

public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initComponent();
    }

    private void initComponent() {
        findViewById(R.id.btn_refresh).setOnClickListener(v ->
                startActivity(new Intent(this, PullToRefreshActivity.class))
        );
        findViewById(R.id.btn_dark).setOnClickListener(v -> {
            //调暗屏幕导航控件以及系统状态栏 API14
            int currentVis = v.getSystemUiVisibility();
            int newVis;
            if ((currentVis & View.SYSTEM_UI_FLAG_LOW_PROFILE) == View.SYSTEM_UI_FLAG_LOW_PROFILE) {
                newVis = View.SYSTEM_UI_FLAG_VISIBLE;
            } else {
                newVis = View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            v.setSystemUiVisibility(newVis);
        });
        findViewById(R.id.btn_hide).setOnClickListener(v -> {
            //隐藏导航控件 API14
            //导航控件隐藏后，只要点击一下屏幕，Android系统就会让控件自动重新出现
            //需要注意隐藏后 布局大小会改变
            v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        });
        findViewById(R.id.btn_full_sceen).setOnClickListener(v -> {
            //API16
            startActivity(new Intent(this, FullActivity.class));
        });
        findViewById(R.id.btn_object_animator).setOnClickListener(v -> {
            startActivity(new Intent(this, FlipperActivity.class));
        });
        findViewById(R.id.btn_custom_1).setOnClickListener(v -> {
            startActivity(new Intent(this, CustomViewActivity1.class));
        });
        findViewById(R.id.loading_anim_1).setOnClickListener(v -> {
            startActivity(new Intent(this, LoadingAnimActivity.class));
        });
    }
}
