package cat.app.tts.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import cat.app.tts.R;

public class RoundProgressBar extends View {

    public final static int POS_LEFT = 0;
    public final static int POS_TOP = 1;
    public final static int POS_RIGHT = 2;
    public final static int POS_BOTTOM = 3;

    private int mRoundColor;
    private int mProgressColor;
    private int mTextColor;
    private int mStartPosition;

    private float mRadius;
    private float mTextSize;
    private float mCurrentProgress = 1;
    private float mMaxProgress;
    private float mProgressWidth;

    private Paint mNumberPaint;
    private Paint mProgressPaint;
    private Paint mBackgroundPaint;
    /*
        public  RoundProgressBar(Context context) {
            super(context);
            //initTest();
            initAllPaint();
        }
    */
    public  RoundProgressBar(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        initConfig(context,attrs);
        //initTest();
        initAllPaint();
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig(context,attrs);
        initAllPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mRadius = Math.min(width,height) / 2.0f;
        //Log.d("Round",""+mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics mFontMetrics = mNumberPaint.getFontMetrics();
        float baseLine = (mFontMetrics.ascent + mFontMetrics.descent) / 2 - mFontMetrics.descent;
        String tmpCurrentProgress = (mCurrentProgress > 99) ? "99+" : String.valueOf((int)mCurrentProgress);
        float tmpDegree = ( mCurrentProgress / mMaxProgress ) * 360;
        int tmpStartDegree = getStartDegree();
        canvas.drawText(tmpCurrentProgress,mRadius - mNumberPaint.measureText(tmpCurrentProgress) / 2,
                mRadius - baseLine / 2,mNumberPaint);
        //canvas.drawText("999",mRadius + mNumberPaint.measureText("999") / 2,mRadius - mTextSize / 2,mNumberPaint);
        canvas.drawCircle(mRadius,mRadius,mRadius - mProgressWidth/2,mBackgroundPaint);
        canvas.drawArc(mProgressWidth / 2,mProgressWidth / 2,mRadius * 2 - mProgressWidth / 2,
                mRadius * 2 - mProgressWidth / 2,tmpStartDegree,tmpDegree,false,mProgressPaint);
    }

    private void initAllPaint() {
        mNumberPaint = new Paint();
        mNumberPaint.setColor(mTextColor);
        mNumberPaint.setStyle(Paint.Style.FILL);
        mNumberPaint.setTextAlign(Paint.Align.LEFT);
        mNumberPaint.setTextSize(mTextSize);
        mNumberPaint.setAntiAlias(true);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(mProgressColor);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth(mProgressWidth);
        mProgressPaint.setAntiAlias(true);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(mRoundColor);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mBackgroundPaint.setStrokeWidth(mProgressWidth);
        mBackgroundPaint.setAntiAlias(true);
    }

    private void initConfig(Context context,AttributeSet attrs) {
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs,R.styleable.RoundProgressBar);
        mMaxProgress = mTypeArray.getInteger(R.styleable.RoundProgressBar_maxProgress,100);
        mRoundColor = mTypeArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.WHITE);
        mProgressColor = mTypeArray.getColor(R.styleable.RoundProgressBar_progressColor,Color.BLUE);
        mTextColor = mTypeArray.getColor(R.styleable.RoundProgressBar_textColor,Color.BLUE);
        mTextSize = mTypeArray.getDimension(R.styleable.RoundProgressBar_textSize,16);
        mStartPosition = mTypeArray.getInteger(R.styleable.RoundProgressBar_startPos,1);
        mProgressWidth = mTypeArray.getDimension(R.styleable.RoundProgressBar_progressWidth,16);
        mCurrentProgress = mTypeArray.getInteger(R.styleable.RoundProgressBar_progress,75);
        Log.d("Round"," "+mMaxProgress+" "+mProgressWidth);
        mTypeArray.recycle();
    }

    private int getStartDegree() {
        switch (mStartPosition) {
            case POS_LEFT:
                return -180;
            case POS_TOP:
                return -90;
            case POS_RIGHT:
                return 0;
            case POS_BOTTOM:
                return 90;
            default:
                break;
        }
        return -90;
    }

    public void setCurrentProgress(int progress) {
        mCurrentProgress = (progress > mMaxProgress) ? mMaxProgress : progress;
    }

    private void initTest() {
        mMaxProgress = 100;
        mRoundColor = Color.rgb(200,200,200);
        mProgressColor = Color.BLUE;
        mTextColor = Color.BLUE;
        mTextSize = 256;
        mProgressWidth = 64;
        mStartPosition = POS_TOP;
        mCurrentProgress = 75;
    }

}