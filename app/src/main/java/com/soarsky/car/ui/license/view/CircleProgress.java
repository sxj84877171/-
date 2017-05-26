package com.soarsky.car.ui.license.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.soarsky.car.R;


/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：自定义的progress<br>
 * 该类为 CircleProgress<br>
 */
public class CircleProgress extends View {
    private Paint finishedPaint;
    private Paint unfinishedPaint;
    private Paint innerCirclePaint;

    protected Paint progressPaint;
    protected Paint innerBottomTextPaint;

    private RectF strokeWidthRect = new RectF();

    private float textSize;
    private int textColor;
    private int innerBottomTextColor;
    private int progress = 0;
    private int max;
    private int finishedStrokeColor;
    private int unfinishedStrokeColor;
    private int startingDegree;
    private float strokeWidth;
    private int innerBackgroundColor;
    private float buttomTextDistance;
    private float progressDistance;

    public float getProgressDistance() {
        return progressDistance;
    }

    public void setProgressDistance(float progressDistance) {
        this.progressDistance = progressDistance;
    }

    public float getButtomTextDistance() {
        return buttomTextDistance;
    }

    public void setButtomTextDistance(float buttomTextDistance) {
        this.buttomTextDistance = buttomTextDistance;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    private float innerBottomTextSize;
    private String innerBottomText;

    private final float default_stroke_width;
    private final int default_finished_color = Color.RED;
    private final int default_unfinished_color = Color.LTGRAY;
    private final int default_text_color = Color.RED;
    private final int default_inner_bottom_text_color = Color.BLACK;
    private final int default_inner_background_color = Color.TRANSPARENT;
    private final int default_max = 12;
    private final int default_startingDegree = 0;
    private final float defaultButtomTextDistance = 20;
    private final float defaultProgressDistance = 0;
    private final float default_text_size;
    private final float default_inner_bottom_text_size;


    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        default_text_size = PxDpUtil.sp2px(getResources(), 40);
        default_stroke_width = PxDpUtil.dp2px(getResources(), 11);
        default_inner_bottom_text_size = PxDpUtil.sp2px(getResources(), 14);

        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgress, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
        initPainters();
    }

    protected void initPainters() {
        progressPaint = new TextPaint();
        progressPaint.setColor(textColor);
        progressPaint.setTextSize(textSize);
        progressPaint.setAntiAlias(true);
        progressPaint.setFakeBoldText(true);

        innerBottomTextPaint = new TextPaint();
        innerBottomTextPaint.setColor(innerBottomTextColor);
        innerBottomTextPaint.setTextSize(innerBottomTextSize);
        innerBottomTextPaint.setAntiAlias(true);


        finishedPaint = new Paint();
        finishedPaint.setColor(finishedStrokeColor);
        finishedPaint.setStyle(Paint.Style.STROKE);
        finishedPaint.setAntiAlias(true);
        finishedPaint.setStrokeWidth(strokeWidth);

        unfinishedPaint = new Paint();
        unfinishedPaint.setColor(unfinishedStrokeColor);
        unfinishedPaint.setStyle(Paint.Style.STROKE);
        unfinishedPaint.setAntiAlias(true);
        unfinishedPaint.setStrokeWidth(strokeWidth);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(innerBackgroundColor);
        innerCirclePaint.setAntiAlias(true);
    }

    protected void initByAttributes(TypedArray attributes) {
        finishedStrokeColor = attributes.getColor(R.styleable.CircleProgress_donut_finished_color, default_finished_color);
        unfinishedStrokeColor = attributes.getColor(R.styleable.CircleProgress_donut_unfinished_color, default_unfinished_color);
        buttomTextDistance = attributes.getDimension(R.styleable.CircleProgress_donut_bottom_text_distance, defaultButtomTextDistance);
        progressDistance = attributes.getDimension(R.styleable.CircleProgress_donut_progress_distance, defaultProgressDistance);
        max = attributes.getInt(R.styleable.CircleProgress_donut_max, default_max);
        progress = attributes.getInt(R.styleable.CircleProgress_donut_progress, 0);
        strokeWidth = attributes.getDimension(R.styleable.CircleProgress_donut_stroke_width, default_stroke_width);

        textColor = attributes.getColor(R.styleable.CircleProgress_donut_text_color, default_text_color);
        textSize = attributes.getDimension(R.styleable.CircleProgress_donut_text_size, default_text_size);
        innerBottomTextSize = attributes.getDimension(R.styleable.CircleProgress_donut_inner_bottom_text_size, default_inner_bottom_text_size);
        innerBottomTextColor = attributes.getColor(R.styleable.CircleProgress_donut_inner_bottom_text_color, default_inner_bottom_text_color);
        innerBottomText = attributes.getString(R.styleable.CircleProgress_donut_inner_bottom_text);

        startingDegree = attributes.getInt(R.styleable.CircleProgress_donut_circle_starting_degree, default_startingDegree);
        innerBackgroundColor = attributes.getColor(R.styleable.CircleProgress_donut_background_color, default_inner_background_color);
    }

    @Override
    public void invalidate() {
        initPainters();
        super.invalidate();
    }



    private float getProgressAngle() {
        return getProgress() / (float) max * 360f;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if (max > 0) {
            this.max = max;
            invalidate();
        }
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        this.invalidate();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        this.invalidate();
    }

    public int getFinishedStrokeColor() {
        return finishedStrokeColor;
    }

    public void setFinishedStrokeColor(int finishedStrokeColor) {
        this.finishedStrokeColor = finishedStrokeColor;
        this.invalidate();
    }

    public int getUnfinishedStrokeColor() {
        return unfinishedStrokeColor;
    }

    public void setUnfinishedStrokeColor(int unfinishedStrokeColor) {
        this.unfinishedStrokeColor = unfinishedStrokeColor;
        this.invalidate();
    }

    public int getInnerBackgroundColor() {
        return innerBackgroundColor;
    }

    public void setInnerBackgroundColor(int innerBackgroundColor) {
        this.innerBackgroundColor = innerBackgroundColor;
        this.invalidate();
    }


    public String getInnerBottomText() {
        return innerBottomText;
    }

    public void setInnerBottomText(String innerBottomText) {
        this.innerBottomText = innerBottomText;
        this.invalidate();
    }


    public float getInnerBottomTextSize() {
        return innerBottomTextSize;
    }

    public void setInnerBottomTextSize(float innerBottomTextSize) {
        this.innerBottomTextSize = innerBottomTextSize;
        this.invalidate();
    }

    public int getInnerBottomTextColor() {
        return innerBottomTextColor;
    }

    public void setInnerBottomTextColor(int innerBottomTextColor) {
        this.innerBottomTextColor = innerBottomTextColor;
        this.invalidate();
    }

    public int getStartingDegree() {
        return startingDegree;
    }

    public void setStartingDegree(int startingDegree) {
        this.startingDegree = startingDegree;
        this.invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        strokeWidthRect.set(strokeWidth,
                strokeWidth,
                getWidth() - strokeWidth,
                getHeight() - strokeWidth);
        float innerCircleRadius = (getWidth() - strokeWidth) / 2f;
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, innerCircleRadius, innerCirclePaint);
        canvas.drawArc(strokeWidthRect, getStartingDegree(), getProgressAngle(), false, finishedPaint);
        canvas.drawArc(strokeWidthRect, getStartingDegree() + getProgressAngle(), 360 - getProgressAngle(), false, unfinishedPaint);
        String text = "" + getProgress();
        canvas.drawText(text, (getWidth() - progressPaint.measureText(text)) / 2.0f, (getHeight()) / 2.0f - PxDpUtil.dp2px(getResources(), progressDistance), progressPaint);
        if (!TextUtils.isEmpty(getInnerBottomText())) {
            innerBottomTextPaint.setTextSize(innerBottomTextSize);
            canvas.drawText(getInnerBottomText(), (getWidth() - innerBottomTextPaint.measureText(getInnerBottomText())) / 2.0f, (getHeight() / 2.0f) + PxDpUtil.dp2px(getResources(), buttomTextDistance), innerBottomTextPaint);
        }
    }

    public void setDonut_progress(String percent) {
        if (!TextUtils.isEmpty(percent)) {
            setProgress(Integer.parseInt(percent));
        }
    }



    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_TEXT_SIZE = "text_size";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_SIZE = "inner_bottom_text_size";
    private static final String INSTANCE_INNER_BOTTOM_TEXT = "inner_bottom_text";
    private static final String INSTANCE_INNER_BOTTOM_TEXT_COLOR = "inner_bottom_text_color";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PROGRESS = "progress";
    private static final String INSTANCE_BACKGROUND_COLOR = "inner_background_color";
    private static final String INSTANCE_STARTING_DEGREE = "starting_degree";
    private static final String INSTANCE_STROKE_WIDTH = "stroke_Width";
    private static final String INSTANCE_BUTTOM_TEXT_DISTANCE = "buttom_text_distance";
    private static final String INSTANCE_PROGRESS_DISTANCE = "progress_Distance";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, getTextColor());
        bundle.putFloat(INSTANCE_TEXT_SIZE, getTextSize());
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE, getInnerBottomTextSize());
        bundle.putFloat(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor());
        bundle.putString(INSTANCE_INNER_BOTTOM_TEXT, getInnerBottomText());
        bundle.putInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR, getInnerBottomTextColor());
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, getFinishedStrokeColor());
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, getUnfinishedStrokeColor());
        bundle.putInt(INSTANCE_MAX, getMax());
        bundle.putInt(INSTANCE_STARTING_DEGREE, getStartingDegree());
        bundle.putInt(INSTANCE_PROGRESS, getProgress());
        bundle.putFloat(INSTANCE_STROKE_WIDTH, getStrokeWidth());
        bundle.putFloat(INSTANCE_BUTTOM_TEXT_DISTANCE, getButtomTextDistance());
        bundle.putFloat(INSTANCE_PROGRESS_DISTANCE, getProgressDistance());
        bundle.putInt(INSTANCE_BACKGROUND_COLOR, getInnerBackgroundColor());

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            textSize = bundle.getFloat(INSTANCE_TEXT_SIZE);
            innerBottomTextSize = bundle.getFloat(INSTANCE_INNER_BOTTOM_TEXT_SIZE);
            buttomTextDistance = bundle.getFloat(INSTANCE_BUTTOM_TEXT_DISTANCE);
            progressDistance = bundle.getFloat(INSTANCE_PROGRESS_DISTANCE);
            innerBottomText = bundle.getString(INSTANCE_INNER_BOTTOM_TEXT);
            innerBottomTextColor = bundle.getInt(INSTANCE_INNER_BOTTOM_TEXT_COLOR);
            strokeWidth = bundle.getFloat(INSTANCE_STROKE_WIDTH);
            finishedStrokeColor = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
            unfinishedStrokeColor = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
            innerBackgroundColor = bundle.getInt(INSTANCE_BACKGROUND_COLOR);
            initPainters();
            setMax(bundle.getInt(INSTANCE_MAX));
            setStartingDegree(bundle.getInt(INSTANCE_STARTING_DEGREE));
            setProgress(bundle.getInt(INSTANCE_PROGRESS));
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
