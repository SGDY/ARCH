package com.example.sgdy.animator;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sgdy.R;

/**
 * Created by 上官丹意 on 2016/05/17 08:22.
 */
public class LoadingAnimActivity extends Activity {

    private ImageView imgView;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_anim);
        imgView = (ImageView) findViewById(R.id.image_view);
        //给动画资源赋值
        animationDrawable = (AnimationDrawable) imgView.getDrawable();
    }
}
