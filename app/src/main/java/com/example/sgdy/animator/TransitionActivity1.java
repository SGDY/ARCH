package com.example.sgdy.animator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sgdy.R;

/**
 * 添加视图 android默认的效果
 */
public class TransitionActivity1 extends Activity {

    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        mContainer = (LinearLayout) findViewById(R.id.verticalContainer);

    }

    public void onAddClick(View view) {
        Button button = new Button(this);
        button.setText("Click To Remove");
        button.setOnClickListener(v -> mContainer.removeView(v));
        mContainer.addView(button, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
