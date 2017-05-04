package com.qinlei.customview3;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;

/**
 * Created by ql on 2017/5/4.
 */

public class SizeUtil {

    /**
     * 测量文本大小
     * @param text
     * @param paint
     * @return
     */
    public static Rect messureText(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    /**
     * sp 转 px
     * @param context
     * @param textSize
     * @return
     */
    public static int spToPx(Context context, int textSize) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                textSize, context.getResources().getDisplayMetrics());
    }

    /**
     * dp 转 px
     * @param context
     * @param size
     * @return
     */
    public static int dpToPx(Context context, int size) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                size, context.getResources().getDisplayMetrics());
    }

}
