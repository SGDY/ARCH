package com.example.sgdy.customview.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sgdy on 16/5/16.
 */
public class TextImageButton extends FrameLayout {

    private ImageView imageView;
    private TextView textView;

    //这个构造函数通常用于在代码中新建视图
    public TextImageButton(Context context) {
        //使用this调用实际完成所有工作的函数，从而实现了其他两个构造函数
        this(context, null);
    }
    //将XML转换为视图，XML文件中定义的属性会传递给AttributeSet参数。
    public TextImageButton(Context context, AttributeSet attrs) {
        //以这种方式构建自定义控件就确保了我们能在XML布局中定义此视图，如果不实现这两
        // 个属性化的构造参数 就不能在XML布局中使用自定义的控件
        this(context, attrs, 0);
    }

    public TextImageButton(Context context, AttributeSet attrs, int defaultStyle) {
        //通过系统的按钮样式初始化父布局
        //这样会设置clickable属性和按钮背景来匹配当前的主题
        super(context, attrs, android.R.attr.buttonStyle);
        //创建子试图
        imageView = new ImageView(context, attrs, defaultStyle);
        textView = new TextView(context, attrs, defaultStyle);
        //创建子视图的LayoutParams为WRAP_CONTENT并居中显示
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        //添加子试图
        this.addView(imageView, params);
        this.addView(textView, params);
        /**
         * 如果有图片，切换到图片模式
         */
        if (imageView.getDrawable() != null) {
            textView.setVisibility(GONE);
            imageView.setVisibility(VISIBLE);
        } else {
            textView.setVisibility(VISIBLE);
            imageView.setVisibility(GONE);
        }
    }
    //存取器
    public void setText(CharSequence text) {
        //切换到文字模式
        textView.setVisibility(VISIBLE);
        imageView.setVisibility(GONE);
        //设置文字
        textView.setText(text);
    }

    public void setImageResource(int resId) {
        //切换到图片模式
        textView.setVisibility(GONE);
        imageView.setVisibility(VISIBLE);
        //设置图片
        imageView.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        //切换到图片模式
        textView.setVisibility(GONE);
        imageView.setVisibility(VISIBLE);
        //设置图片
        imageView.setImageDrawable(drawable);
    }
}
