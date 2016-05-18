package com.example.sgdy.animator;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.sgdy.R;

public class ObjectAnimatorActivity2 extends Activity {

    private ObjectAnimator mFilpper;
    private boolean mIsHeads;
    private Bitmap mHeadsImage, mTailsImage;
    private ImageView mFilpImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper2);

        mHeadsImage = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        mTailsImage = BitmapFactory.decodeResource(getResources(), R.drawable.icon);

        mFilpImage = (ImageView) findViewById(R.id.flip_image);
        mFilpImage.setImageBitmap(mHeadsImage);
        mIsHeads = true;

        //沿着Y轴旋转
        mFilpper = ObjectAnimator.ofFloat(mFilpImage, "rotationX", 0f, 360f);
        mFilpper.setDuration(500);
        //addUpdateListener动画运行过程中的常规回调方法
        mFilpper.addUpdateListener(animation -> {
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
            mFilpper.start();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
