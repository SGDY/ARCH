package com.example.sgdy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.sgdy.animator.AnimateActivity;
import com.example.sgdy.animator.LoadingAnimActivity;
import com.example.sgdy.animator.ObjectAnimatorActivity1;
import com.example.sgdy.animator.ObjectAnimatorActivity2;
import com.example.sgdy.animator.TransitionActivity1;
import com.example.sgdy.animator.TransitionActivity2;
import com.example.sgdy.customview.CustomViewActivity1;
import com.example.sgdy.http.MultipartActivity;
import com.example.sgdy.photo.SelectPhotoActivity;

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
            Intent intent = new Intent(this, ObjectAnimatorActivity1.class);
            //Intent.FLAG_ACTIVITY_NO_ANIMATION让用户看起来两个活动是一个的感
            // 觉，阻止播放过渡动画
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
        findViewById(R.id.btn_object_animator2).setOnClickListener(v -> {
            Intent intent = new Intent(this, ObjectAnimatorActivity2.class);
            //Intent.FLAG_ACTIVITY_NO_ANIMATION让用户看起来两个活动是一个的感
            // 觉，阻止播放过渡动画
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
        findViewById(R.id.btn_custom_1).setOnClickListener(v -> {
            startActivity(new Intent(this, CustomViewActivity1.class));
            //重写活动的过渡效果 overridePendingTransition有两个参数，第一个是启动下一个活动要播放的动画的ID，第二个
            // 参数是关闭当前活动要播放的动画的ID
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
        findViewById(R.id.loading_anim_1).setOnClickListener(v -> {
            startActivity(new Intent(this, LoadingAnimActivity.class));
        });
        findViewById(R.id.btn_view_property_animation).setOnClickListener(v -> {
            startActivity(new Intent(this, AnimateActivity.class));
        });
        findViewById(R.id.btn_layout_transition).setOnClickListener(v ->
                //API11
                startActivity(new Intent(this, TransitionActivity1.class)));
        findViewById(R.id.btn_layout_transition2).setOnClickListener(v ->
                //API11
                startActivity(new Intent(this, TransitionActivity2.class)));

        findViewById(R.id.btn_photo_select).setOnClickListener(v -> {
            //
            startActivity(new Intent(this, SelectPhotoActivity.class));
        });
        findViewById(R.id.btn_multipart).setOnClickListener(v ->
                startActivity(new Intent(this, MultipartActivity.class)));
    }
}
