package cn.edu.jnu.supershopper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义折线图
 */
public class BrokeLineView extends View {
    private static final String TAG = "BrokeLineView";
    private int[] yLables = {1, 6, 11, 16, 21, 26}; //固定y轴数字
    private List<Float> yValues = new ArrayList<>();
    private int mHeight;
    private int originX; // 原点x坐标
    private int intervalX = 44; // 坐标刻度的间隔
    private int intervalY = 24; // y轴刻度的间隔

    private int firstPointX; //第一个点x坐标
    private int xyTextSize = sp2px(10); //xy轴文字大小
    private int lableCountY = yLables.length; // Y轴刻度个数
    private float minValueY = 1; // y轴最小值
    private float maxValueY = 26; // y轴最大值

    private int backGroundColor = Color.parseColor("#ffffff"); // view的背景颜色
    private Paint paintWhite, paintBlue, paintRed, paintBack, paintText, dashPaint;


    public BrokeLineView(Context context) {
        this(context, null);
    }

    public BrokeLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrokeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.STROKE);

        paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.parseColor("#0198cd"));
        paintBlue.setStrokeWidth(3f);
        paintBlue.setStyle(Paint.Style.STROKE);

        paintBack = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBack.setColor(Color.parseColor("#272727"));
        paintBack.setStyle(Paint.Style.FILL);

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(Color.RED);
        paintRed.setStrokeWidth(3f);
        paintRed.setStyle(Paint.Style.STROKE);

        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(xyTextSize);
        paintText.setStrokeWidth(2f);

        dashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashPaint.setColor(Color.WHITE);
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setStrokeWidth(1f);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
//            mWidth = getWidth();
            mHeight = getHeight();

            Log.e(TAG, "" + mHeight);
            originX = 0;
            firstPointX = 0;
            setBackgroundColor(backGroundColor);
        }
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //注意：drawLine位置不能改变
        drawLine(canvas);
    }


    /**
     * 画折线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
//        canvas.save();
        // 画折线
        float aver = (lableCountY - 1) * intervalY / (maxValueY - minValueY); //y轴最小单位的距离
        Path path = new Path();
        //先移动到第一个点的位置
        path.moveTo(firstPointX, mHeight - yValues.get(0) * aver + minValueY * aver);

        //绘制折线
        for (int i = 0; i < yValues.size(); i++) {
            path.lineTo(firstPointX + i * intervalX, mHeight - yValues.get(i) * aver + minValueY * aver);
        }
        canvas.drawPath(path, paintBlue);


        //将折线超出x轴坐标的部分截取掉（左边）
        paintBack.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        RectF rectF = new RectF(0, 0, originX, mHeight);
        canvas.drawRect(rectF, paintBack);
//        canvas.restore();
    }


    public void setYValues(List<Float> values) {
        this.yValues = values;
        for (int i = 0; i < yValues.size(); i++) {
            // 找出y轴的最大最小值
            if (yValues.get(i) > maxValueY) {
                maxValueY = yValues.get(i);
            }
            if (yValues.get(i) < minValueY) {
                minValueY = yValues.get(i);
            }
        }
    }

    /**
     * sp转换px
     */
    public int sp2px(int spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
