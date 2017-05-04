package com.qinlei.customview3.dingwei;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.qinlei.customview3.R;
import com.qinlei.customview3.SizeUtil;

/**
 * Created by ql on 2017/5/3.
 */

public class DingDingDingWeiView extends View {

    private int defaultWidth = 200;
    private int defaultHeight = 200;

    private int w, h;

    private Paint outCirclePaint;
    private Paint inCirclePaint;
    private Paint textPaint;

    private Shader mSweepGradient = null;

    private int angle;
    private ValueAnimator valueAnimator;

    public DingDingDingWeiView(Context context) {
        super(context);
    }

    public DingDingDingWeiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DingDingDingWeiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        outCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        outCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.lightBlue_plus));
        outCirclePaint.setTextSize(SizeUtil.spToPx(getContext(), 24));

        inCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        inCirclePaint.setColor(ContextCompat.getColor(getContext(), R.color.darkBlue));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(SizeUtil.spToPx(getContext(), 24));

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

        mSweepGradient = new SweepGradient(w / 2, h / 2, ContextCompat.getColor(getContext(), R.color.lightBlue)
                , ContextCompat.getColor(getContext(), R.color.darkBlue));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(w / 2, h / 2, w / 2, outCirclePaint);

        if (valueAnimator == null || !valueAnimator.isRunning()) {
            inCirclePaint.setShader(null);
            canvas.drawCircle(w / 2, h / 2, w / 2 - 20, inCirclePaint);
            Rect rect = SizeUtil.messureText("下班打卡", outCirclePaint);
            canvas.drawText("下班打卡", (w - rect.width()) / 2,
                    (h - rect.height()) / 2 + rect.height(), textPaint);
        } else {
            canvas.save();
            canvas.rotate(angle, w / 2, h / 2);
            inCirclePaint.setShader(mSweepGradient);
            canvas.drawCircle(w / 2, h / 2, w / 2 - 20, inCirclePaint);
            canvas.restore();
            //测量文本
            Rect rect = SizeUtil.messureText("定位中...", outCirclePaint);
            canvas.drawText("定位中...", (w - rect.width()) / 2,
                    (h - rect.height()) / 2 + rect.height(), textPaint);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }

    public void startScan() {
        valueAnimator = ValueAnimator.ofInt(0, 359);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void stopScan() {
        valueAnimator.cancel();
        invalidate();
    }

}
