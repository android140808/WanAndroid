package avater.appscomm.com.mywanandroids;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SportDataView extends View {
    public static final int TYPE_DAY = 1;
    public static final int TYPE_WEEK = 2;
    public static final int TYPE_MONTH = 3;
    public static final String UNIT_STEP = "STEPS";
    public static final String UNIT_CAL = "CAL";
    public static final String UNIT_DIS_KM = "KM";
    public static final String UNIT_DIS_MILE = "MILES";
    public static final String UNIT_ACTIVE = "MIN";
    public static final String UNIT_SLEEP = "HRS";

    private int color_dark;
    private int color_red;
    private int color_step_value;
    private int color_step_unit;
    private int color_cal_value;
    private int color_cal_unit;
    private int color_dis_vlaue;
    private int color_dis_unit;
    private int color_act_value;
    private int color_act_unit;
    private int color_sleep_value;
    private int color_sleep_unit;
    private int mCurrentType;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private String[] tittles;
    private String[] tittles_Month = new String[]{"Monthly Average", "Monthly Activity", " Goal"};
    private String[] tittles_Week = new String[]{"Daily Average", "Weekly Activity", " Goal"};
    private int averageWidth;
    private Context mContext;
    private String mCurrentUnit;
    private String mCurrentValueAverage = "0";
    private String mCurrentValue = "0";
    private int mCurrentMax = 0;
    private int mValue = 0;


    public SportDataView(Context context) {
        this(context, null);
    }

    public SportDataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SportDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mCurrentType = 2;
        mCurrentUnit = UNIT_STEP;
        tittles = tittles_Month;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(dip2px(12));
        color_dark = getResources().getColor(R.color.dark);
        color_red = getResources().getColor(R.color.red);
        color_step_value = getResources().getColor(R.color.step_color);
        color_step_unit = getResources().getColor(R.color.step_color_unit);
        color_cal_value = getResources().getColor(R.color.calories_color);
        color_cal_unit = getResources().getColor(R.color.calories_color_unit);
        color_dis_vlaue = getResources().getColor(R.color.disdance_color);
        color_dis_unit = getResources().getColor(R.color.disdance_color_unit);
        color_act_value = getResources().getColor(R.color.activity_color);
        color_act_unit = getResources().getColor(R.color.activity_color_unit);
        color_sleep_value = getResources().getColor(R.color.sleep_color);
        color_sleep_unit = getResources().getColor(R.color.sleep_color_unit);

        mPaint.setColor(color_dark);
    }

    private int dip2px(float dpValues) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValues * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = widthSize;
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = dip2px(100);
        }
        Log.e("TAG", "重新设置大小");
        setMeasuredDimension(mWidth, mHeight);//重新设置大小
    }

    @Override
    protected void onDraw(Canvas canvas) {
        averageWidth = mWidth / 3;
        switch (mCurrentType) {
            case TYPE_DAY:
                drawDay(canvas);
                break;
            case TYPE_WEEK:
                tittles = tittles_Week;
                drawTittle(canvas);
                break;
            case TYPE_MONTH:
                tittles = tittles_Month;
                drawTittle(canvas);
                break;
        }
        drawBottom(canvas);
    }

    private void drawDay(Canvas canvas) {
        mPaint.setColor(color_red);
        mPaint.setStyle(Paint.Style.FILL);
        int x = 0, y = 0;
        String text = "Actual";
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        int width = rect.width();
        y = rect.height() * 2;
        x = mWidth / 2 - width;
        int radius = 5;
        int endx = 0;
        setColorToPaint(true);
        for (int i = 0; i < 4; i++) {
            canvas.drawCircle(x + (20 * i), y, radius, mPaint);
            if (i == 3) {
                endx = x + (20 * i);
            }
        }
        canvas.drawLine(x, y, endx, y, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color_dark);
        canvas.drawText(text, endx + dip2px(10), y + y / 4, mPaint);
    }

    private void drawTittle(Canvas canvas) {
        Rect rect = new Rect();
        mPaint.setColor(color_dark);
        mPaint.getTextBounds(tittles[0], 0, tittles[0].length(), rect);
        int w = rect.width() / 2;
        int x = averageWidth / 2 - w;
        int y = dip2px(15);
        canvas.drawText(tittles[0], x, y, mPaint);

        mPaint.getTextBounds(tittles[1], 0, tittles[1].length(), rect);
        x = averageWidth + (averageWidth / 2 - rect.width() / 2);
        canvas.drawText(tittles[1], x, y, mPaint);

        String targer = getString() + tittles[2];
        mPaint.getTextBounds(targer, 0, targer.length(), rect);
        x = averageWidth * 2 + (averageWidth / 2 - rect.width() / 2);
        canvas.drawText(targer, x, y, mPaint);

        String text = mCurrentValueAverage;
        if ((mCurrentType == SportDataView.TYPE_WEEK || mCurrentType == SportDataView.TYPE_MONTH) && (mCurrentUnit.equals(SportDataView.UNIT_DIS_MILE) || mCurrentUnit.equals(SportDataView.UNIT_DIS_KM))) {
            DecimalFormat df1 = new DecimalFormat("0.#");
            df1.setRoundingMode(RoundingMode.DOWN);
            float va = Float.parseFloat(mCurrentValueAverage);
            text = df1.format(va / 1000);
        } else if (mCurrentUnit.equals(UNIT_SLEEP)) {
            text = mCurrentValue;
            DecimalFormat df1 = new DecimalFormat("0.0#");
            df1.setRoundingMode(RoundingMode.DOWN);
            float va = Float.parseFloat(text);
            text = df1.format(va / 60);
        }
        mPaint.getTextBounds(text, 0, text.length(), rect);
        setColorToPaint(true);
        int endy = y + rect.height() * 2;
        canvas.drawText(text, averageWidth / 2 - w, y + rect.height() * 2, mPaint);

        setColorToPaint(false);
        canvas.drawText(mCurrentUnit, averageWidth / 2 - w + rect.width() + rect.width() / 2, y + rect.height() * 2, mPaint);

        x = averageWidth + averageWidth / 3;
        int radius = 5;
        int endx = 0;
        setColorToPaint(true);
        for (int i = 0; i < 4; i++) {
            canvas.drawCircle(x + (20 * i), y + rect.height() * 2, radius, mPaint);
            if (i == 3) {
                endx = x + (20 * i);
            }
        }
        canvas.drawLine(x, y + rect.height() * 2, endx, y + rect.height() * 2, mPaint);

        mPaint.setColor(mContext.getResources().getColor(R.color.blue));
        text = "- - - -";
        mPaint.getTextBounds(text, 0, text.length(), rect);
        x = 2 * averageWidth + (averageWidth - rect.width()) / 2;
        canvas.drawText(text, x, endy, mPaint);
    }

    private void setColorToPaint(boolean step) {
        int color = 0;
        switch (mCurrentUnit) {
            case UNIT_STEP:
                if (step) {
                    color = color_step_value;
                } else {
                    color = color_step_unit;
                }
                break;
            case UNIT_CAL:
                if (step) {
                    color = color_cal_value;
                } else {
                    color = color_cal_unit;
                }
                break;
            case UNIT_DIS_KM:
                if (step) {
                    color = color_dis_vlaue;
                } else {
                    color = color_dis_unit;
                }
                break;
            case UNIT_DIS_MILE:
                if (step) {
                    color = color_dis_vlaue;
                } else {
                    color = color_dis_unit;
                }
                break;
            case UNIT_ACTIVE:
                if (step) {
                    color = color_act_value;
                } else {
                    color = color_act_unit;
                }
                break;
            case UNIT_SLEEP:
                if (step) {
                    color = color_sleep_value;
                } else {
                    color = color_sleep_unit;
                }
                break;
        }
        mPaint.setColor(color);
    }

    private void drawBottom(Canvas canvas) {
        mPaint.setColor(color_dark);
        Rect rect = new Rect();
        int x = dip2px(20);
        int y = mHeight - dip2px(10) * 2;
        rect.left = x;
        rect.top = mHeight - dip2px(15);
        rect.right = mWidth - x;
        rect.bottom = mHeight - dip2px(5);
        if (mCurrentType > 1) {
            setColorToPaint(true);
        }
        canvas.drawRect(rect, mPaint);
        //画进度
        if (mCurrentType == 1 && mValue > 0) {
            Rect pb = new Rect();
            pb.left = dip2px(20);
            pb.top = mHeight - dip2px(15);
            if (mValue >= mCurrentMax) {
                mValue = mCurrentMax;
            }
            int length = (int) (((rect.right) * mValue / mCurrentMax + dip2px(20)));
            pb.right = length >= rect.right ? rect.right : length;
            pb.bottom = mHeight - dip2px(5);
            setColorToPaint(true);
            canvas.drawRect(pb, mPaint);
        }

        setColorToPaint(true);
        String values = mCurrentValue;
        if (mCurrentUnit.equals(UNIT_DIS_KM) || mCurrentUnit.equals(UNIT_DIS_MILE)) {
            DecimalFormat df1 = new DecimalFormat("0.#");
            df1.setRoundingMode(RoundingMode.DOWN);
            float va = Float.parseFloat(mCurrentValue);
            values = df1.format(va / 1000);
        } else if (mCurrentUnit.equals(UNIT_SLEEP)) {
            values = mCurrentValue;
            DecimalFormat df1 = new DecimalFormat("0.0#");
            df1.setRoundingMode(RoundingMode.DOWN);
            float va = Float.parseFloat(values);
            values = df1.format(va / 60);
        }
        canvas.drawText(values, x, y, mPaint);
        Rect unit = new Rect();
        mPaint.getTextBounds(mCurrentValue, 0, mCurrentValue.length(), unit);
        int x_unit = unit.width();
        setColorToPaint(false);
        canvas.drawText(mCurrentUnit, 2 * x + x_unit / 2, y, mPaint);

        String date = "day";
        if (mCurrentType == 2) {
            date = "week";
        } else if (mCurrentType == 3) {
            date = "month";
        }
        String last = getString() + " this " + date;
        mPaint.getTextBounds(last, 0, last.length(), rect);
        mPaint.setColor(color_dark);
        canvas.drawText(last, dip2px(20), y - rect.height() * 2 + 10, mPaint);
    }

    private String getString() {
        String re = "Steps";
        switch (mCurrentUnit) {
            case UNIT_STEP:
                re = "Steps";
                break;
            case UNIT_CAL:
                re = "Cal";
                break;
            case UNIT_DIS_KM:
                re = "Distance";
                break;
            case UNIT_DIS_MILE:
                re = "Distance";
                break;
            case UNIT_ACTIVE:
                re = "Time";
                break;
            case UNIT_SLEEP:
                re = "Sleep";
                break;
        }
        return re;
    }


    public void setTittle(int type, String unitValue, int... average) {
        mCurrentType = type;
        mCurrentUnit = unitValue;
        if (average.length > 0) {
            mCurrentValueAverage = average[0] + "";
        }
        invalidate();
    }

    public void setMax(int max) {
        mCurrentMax = max;
    }

    public void setProgress(int pb) {
        mValue = pb;
        mCurrentValue = pb + "";
        invalidate();
    }
}
