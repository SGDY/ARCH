package com.example.sgdy.customview.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sgdy.R;

/**
 * Created by sgdy on 16/6/13.
 */
public class TitleLayout extends LinearLayout {

    //XML中会调用这个构造函数
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //第二个参数是给加载好的布局再添加一个父布局，这里指定TitleLayout
        LayoutInflater.from(context).inflate(R.layout.custom_view_2, this);
        Button btnLeft = (Button) findViewById(R.id.btn_left);
        btnLeft.setOnClickListener(v -> ((Activity) getContext()).finish());
        Button btnRight = (Button) findViewById(R.id.btn_right);
        btnRight.setOnClickListener(v -> {

        });
    }
}
