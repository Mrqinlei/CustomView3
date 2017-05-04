package com.qinlei.customview3.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.qinlei.customview3.SizeUtil;

/**
 * Created by ql on 2017/5/3.
 */

public class TouTiaoLoadingView extends View {
    private int defaultWidth = 200;
    private int defaultHeight = 200;

    private int w, h;

    private Shader mLinearGradient = null;

    private Paint textPaint;

    private ValueAnimator valueAnimator;

    private int mLinearGradientW;

    public TouTiaoLoadingView(Context context) {
        super(context);
    }

    public TouTiaoLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouTiaoLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(SizeUtil.spToPx(getContext(), 24));
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureHanlder(defaultWidth, widthMeasureSpec);
        int height = measureHanlder(defaultHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureHanlder(int defaultSize, int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        valueAnimator = ValueAnimator.ofInt(-w, w);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLinearGradientW = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLinearGradient = new LinearGradient(mLinearGradientW, h / 4, w * 3 / 4 + mLinearGradientW, 0,
                new int[]{Color.GRAY, Color.BLACK, Color.GRAY},
                new float[]{0f, 0.5f, 1f}, Shader.TileMode.CLAMP);
        textPaint.setShader(mLinearGradient);

        canvas.save();
        canvas.rotate(356, w / 2, h / 2);
        Rect rect = SizeUtil.messureText("你关心的  ", textPaint);
        canvas.drawText("你关心的  ", (w - rect.width()) / 2, h / 2 - rect.height() / 2, textPaint);
        canvas.drawText("  才是头条", (w - rect.width()) / 2, h / 2 + rect.height() / 2, textPaint);
        canvas.restore();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }
}
