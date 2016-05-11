package com.example.sgdy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class FullActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //请求这个特性 这样Action Bar就会隐藏起来 页面的变化将不会影响到Activity的内容
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_full);
    }

    public void onToggleClick(View view) {
        //API16 点击可隐藏UI，再点击屏幕 Android系统会自动让控件重新出现
        view.setSystemUiVisibility(
                //这个标识会告诉Android在改变窗口大小来隐藏／显示系统元素时不要移动我们的布局
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        /**
                         * 这个标识会隐藏系统状态栏。如果请求ACTION_BAR_OVERLA，
                         * 同时会隐藏Action Bar
                         */
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        /**
                         * 这个标识会隐藏屏幕上的所有控件
                         */
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }
}
