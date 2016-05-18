package com.example.sgdy.animator;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.sgdy.R;

public class ObjectAnimatorActivity1 extends Activity {

    private boolean mIsHeads;
    private AnimatorSet animatorSet;
    private Bitmap mHeadsImage, mTailsImage;
    private ImageView mFilpImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

        mFilpImage = (ImageView) findViewById(R.id.flip_image);
        mFilpImage.setImageBitmap(mHeadsImage);
        mIsHeads = true;

        animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip);
        //设置目标组件
        animatorSet.setTarget(mFilpImage);
        ObjectAnimator flipAnimator = (ObjectAnimator) animatorSet.getChildAnimations().get(0);
        flipAnimator.addUpdateListener(animation -> {
            //animation.getAnimatedFraction()返回当前动画完成的百分比
            if (animation.getAnimatedFraction() >= 0.25f && mIsHeads) {
                mFilpImage.setImageBitmap(mHeadsImage);
                mIsHeads = false;
            }
            if (animation.getAnimatedFraction() >= 0.75f && !mIsHeads) {
                mFilpImage.setImageBitmap(mTailsImage);
                mIsHeads = true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            animatorSet.start();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
