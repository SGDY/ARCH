package com.example.sgdy.animator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sgdy.R;

/**
 * View显示和隐藏视图
 */
public class AnimateActivity extends Activity {

    View viewToAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        Button button = (Button) findViewById(R.id.toggleButton);
        button.setOnClickListener(v -> {
            if (viewToAnimate.getAlpha() > 0) {
                //如果视图已经可见，将其从右侧滑出
                viewToAnimate.animate().alpha(0f).translationX(1000f);
            } else {
                //如果视图是隐藏的，原地做渐显动画
                //Property Animation会实际修改视图，因此必须首先恢复视图的位置
                viewToAnimate.setTranslationX(0f);
                viewToAnimate.animate().alpha(1f);
            }
        });

        viewToAnimate = findViewById(R.id.theView);
    }
}
