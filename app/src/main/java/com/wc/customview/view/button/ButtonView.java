package com.wc.customview.view.button;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wc.customview.R;

/**
 * 作者：wangchao
 * 时间：2017/9/15 0015 10:36
 * 描述：
 */
public class ButtonView extends View implements View.OnClickListener {

    private Paint squarePaint;
    private int squareColor;
    private RectF squareRectf;

    private Paint boardPaint;
    private int boardSatrtColor;
    private int boardEndColor;
    private int boardColor;


    /*文本绘制*/
    private String text = "确定";
    private int testSize = 30;
    private Paint textPaint;
    private int textColor;
    /*文本的绘制范围*/
    private Rect textRect;

    /*动画*/
    private ObjectAnimator animator;
    private float progress;
    private boolean isCycle = false;


    public ButtonView(Context context) {
        this(context, null);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            squareColor = getContext().getColor(R.color.colorPrimaryDark);
        } else {
            squareColor = getContext().getResources().getColor(R.color.colorPrimaryDark);
        }
        textColor = Color.WHITE;

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            boardSatrtColor = getContext().getColor(R.color.colorPrimaryDark);
        } else {
            boardSatrtColor = getContext().getResources().getColor(R.color.colorPrimaryDark);
        }
        boardColor = boardSatrtColor;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            boardEndColor = getContext().getColor(R.color.colorAccent);
        } else {
            boardEndColor = getContext().getResources().getColor(R.color.colorAccent);
        }

        squarePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        squarePaint.setColor(squareColor);
        squareRectf = new RectF();

        textRect = new Rect();
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setTextSize(testSize);
        textPaint.getTextBounds(text, 0, text.length(), textRect);

        boardPaint = new Paint();
        boardPaint.setAntiAlias(true);
        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(10);

        this.setOnClickListener(this);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boardPaint.setColor(boardColor);
        float wide = (getMeasuredWidth() - getMeasuredHeight()) / 2;
        float startX = getWidth() / 2 - textRect.width() / 2;
        float startY = getHeight() / 2 + textRect.height() / 2;
        squareRectf.set(0 + wide * progress, 0 + 5, getMeasuredWidth() - wide * progress, getMeasuredHeight() - 5);
        float mCornerSize = getHeight() / 2 * progress;
        canvas.drawRoundRect(squareRectf, mCornerSize, mCornerSize, squarePaint);
        canvas.drawRoundRect(squareRectf, mCornerSize, mCornerSize, boardPaint);

        // 绘制文字
        canvas.drawText(text, startX, startY, textPaint);
    }


    private void startAnimation() {
        animator = ObjectAnimator.ofFloat(this, "progress", isCycle ? 1.0f : 0.0f);
        animator.setDuration(500);
        animator.start();


    }

    private void setARGB(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24);
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24);
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        boardColor = ((startA + (int) (fraction * (endA - startA))) << 24) |
                ((startR + (int) (fraction * (endR - startR))) << 16) |
                ((startG + (int) (fraction * (endG - startG))) << 8) |
                ((startB + (int) (fraction * (endB - startB))));

    }


    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        setARGB(progress, boardSatrtColor, boardEndColor);
        invalidate();
    }


    @Override
    public void onClick(View v) {
        isCycle = !isCycle;
        startAnimation();
    }
}
