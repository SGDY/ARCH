package com.example.sgdy.animator;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sgdy.R;

/**
 * 添加视图 自定义的效果
 */
public class TransitionActivity2 extends Activity {

    LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        //布局改变时的动画
        mContainer = (LinearLayout) findViewById(R.id.verticalContainer);
        LayoutTransition transition = new LayoutTransition();
        mContainer.setLayoutTransition(transition);

        //通过翻转进入的动画代替默认的出现动画
        Animator appearAnim = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f).setDuration(
                transition.getDuration(LayoutTransition.APPEARING)
        );
        transition.setAnimator(LayoutTransition.APPEARING, appearAnim);

        //通过翻转消失的动画代替默认的消失动画
        Animator disappearAnim = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).setDuration(
                transition.getDuration(LayoutTransition.DISAPPEARING)
        );
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearAnim);

        //通过滑动动画代替默认的布局改变时的动画
        //我们需要立即设置一些动画属性，所以创建了多个
        //PropertyValueHolder对象的动画
        //这个动画会让视图滑动进入并短暂地缩小一半长度
        PropertyValuesHolder pvhSlide = PropertyValuesHolder.ofFloat("y", 0, 1);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.5f, 1f);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f, 1f);
        Animator changingAppearingAnim = ObjectAnimator.ofPropertyValuesHolder(this, pvhSlide, pvhScaleY, pvhScaleX);
        changingAppearingAnim.setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changingAppearingAnim);
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
