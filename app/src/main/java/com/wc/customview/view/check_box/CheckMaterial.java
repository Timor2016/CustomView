package com.wc.customview.view.check_box;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;

import com.wc.customview.R;

/**
 * 作者：wangchao
 * 时间：2017/9/13 0013 13:47
 * 描述：checkBox
 */
public class CheckMaterial extends View implements Checkable {

    /**
     * 弹跳幅度
     */
    private final float BOUNCE_VALUE = 0.2f;
    /*边框颜色*/
    private int boardColor;
    /*边框宽度*/
    private int boardWide = 6;
    /*选中时颜色*/
    private int checkColor;
    /*对勾颜色*/
    private int markColor;
    /*对勾宽度*/
    private int markWidth = 6;
    /*边框画笔*/
    private Paint boardPaint;
    /*选中时画笔*/
    private Paint checkBgPaint;
    /*对勾画笔*/
    private Paint checkMarkPaint;
    /*画圆半径*/
    private float progress;
    private ObjectAnimator progressAnimate;
    private boolean isChecked = false;

    /*圆半径*/
    private float rad;
    /*对勾*/
    private float rightLine = 0;
    private float LeftLine = 0;


    public CheckMaterial(Context context) {
        this(context, null);
    }

    public CheckMaterial(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckMaterial(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    /**
     * 初始化相关参数
     *
     * @param attrs
     */
    private void init(AttributeSet attrs) {

        boardColor = getContext().getResources().getColor(R.color.colorPrimary);
        checkColor = getContext().getResources().getColor(R.color.colorPrimary);
        markColor = Color.WHITE;

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CheckBox_Material);
        boardColor = array.getColor(R.styleable.CheckBox_Material_border_color, boardColor);
        checkColor = array.getColor(R.styleable.CheckBox_Material_check_color, checkColor);
        boardWide = array.getDimensionPixelSize(R.styleable.CheckBox_Material_border_wide, boardWide);
        markColor = array.getColor(R.styleable.CheckBox_Material_mark_color, markColor);
        markWidth = array.getDimensionPixelSize(R.styleable.CheckBox_Material_mark_width, markWidth);
        isChecked = array.getBoolean(R.styleable.CheckBox_Material_is_check, isChecked);

        boardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(boardWide);
        boardPaint.setColor(boardColor);

        checkBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        checkBgPaint.setColor(boardColor);

        checkMarkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        checkMarkPaint.setColor(markColor);
        checkMarkPaint.setStrokeWidth(markWidth);
        checkMarkPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = dp(0);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(widthMeasureSpec);
        if (width > height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
        rightLine = getMeasuredWidth() / 3;
        LeftLine = getMeasuredWidth() / 6;
        rightLine = (float) (rightLine * Math.sqrt(2) / 2);
        LeftLine = (float) (LeftLine * Math.sqrt(2) / 2);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**半径*/
        rad = getMeasuredWidth() / 2 - 6;

        float p = isChecked ? progress : (1.0f - progress);

        if (p < BOUNCE_VALUE) {
            rad -= dp(2) * p;
        } else if (p < BOUNCE_VALUE * 2) {
            rad -= dp(2) - dp(2) * p;
        }

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, rad, boardPaint);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, rad * progress, checkBgPaint);

        canvas.drawLine(getMeasuredWidth() / 2 - rad / 8, getMeasuredHeight() / 2 + rad / 4,
                getMeasuredWidth() / 2 + progress * rightLine - rad / 8,
                getMeasuredHeight() / 2 - progress * rightLine + rad / 4,
                checkMarkPaint);

        canvas.drawLine(getMeasuredWidth() / 2 - rad / 8, getMeasuredHeight() / 2 + rad / 4,
                getMeasuredWidth() / 2 - progress * LeftLine - rad / 8,
                getMeasuredHeight() / 2 - progress * LeftLine + rad / 4, checkMarkPaint);

    }

    /**
     * 动画
     *
     * @param isChecked
     */
    private void animateProgress(boolean isChecked) {
        progressAnimate = ObjectAnimator.ofFloat(this, "progress", isChecked ? 1.0f : 0.0f);
        progressAnimate.setDuration(200);
        progressAnimate.start();
    }

    public float getProgress() {
        return progress;
    }

    private void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }


    @Override
    public void setChecked(boolean checked) {
        Log.e("checked", checked + "");
        if (checked == isChecked) {
            return;
        }
        isChecked = checked;
        animateProgress(checked);
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }

    /**
     * 转DP
     *
     * @param value
     * @return
     */
    private int dp(float value) {
        if (value == 0) {
            return 0;
        }
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) Math.ceil(density * value);
    }

}
