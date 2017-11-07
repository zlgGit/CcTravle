package cc.travel.com.cctravel.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.ola_ev.cctravlel.R;

import cc.travel.com.cctravel.Utils;

/**
 * Created by ola_insis on 2017/11/7.
 */

public class MyTestView extends View {

    private Paint mPaint;
    private Context mContext;
    private Paint mPaint1;
    private int mFirstX;
    private int mImgPointX;
    private int mImgPointY;
    private int mFirstY;
    private Matrix mImgMatrix;
    private static int TO_UP_STATUS= 1;//表示下一步状态
    
    private static int TO_DOWN_STATUS = 2;//表示下一步状态
    private int mTopStatus;
    private Bitmap mBitmap;
    private long mLastTime;
    private int mQuadEndHight;
    private boolean interupt;// true 表示拦截，事件，不让他发出去

    public MyTestView(Context context) {
        this(context, null);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        mContext = context;
        mPaint1 = new Paint();
        mPaint1.setColor(Color.RED);
        mPaint1.setAntiAlias(true);
        mPaint1.setStrokeWidth(20);
        //不要在draw方法里面做图片处理

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mContext.getResources(), R.drawable.less, opts);
        int outHeight = opts.outHeight;
        opts.inSampleSize = outHeight / Utils.dip2px(mContext, 30);
        opts.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.less, opts);
        mTopStatus = TO_DOWN_STATUS;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int mQuadEndHight = getHeight() - Utils.dip2px(mContext, 20);
        int quadEndWidth = getWidth();
        //贝塞尔曲线

        Path path = new Path();
        path.moveTo(0, mQuadEndHight);
        path.quadTo(quadEndWidth / 2, -mQuadEndHight * 9 / 10, quadEndWidth, mQuadEndHight);
        canvas.drawPath(path, mPaint);


        mImgMatrix = new Matrix();
        int imgWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        mImgPointX = quadEndWidth / 2 - imgWidth / 2;
        mImgPointY = mQuadEndHight / 2 - bitmapHeight / 2;
        //用图片绘制反转箭头，因为要用到旋转
        if (mTopStatus == TO_UP_STATUS) {
            mImgMatrix.postRotate(0, quadEndWidth / 2, mQuadEndHight / 2);
            mImgMatrix.preTranslate(mImgPointX, mImgPointY);
            canvas.drawBitmap(mBitmap, mImgMatrix, mPaint1);
        }
        if (mTopStatus == TO_DOWN_STATUS) {
            mImgMatrix.preTranslate(mImgPointX, mImgPointY);
            mImgMatrix.postRotate(180, quadEndWidth / 2, mQuadEndHight / 2);
            canvas.drawBitmap(mBitmap, mImgMatrix, mPaint1);
        }
        canvas.drawRect(new Rect(0, mQuadEndHight, getWidth(), getHeight()), mPaint);
        Log.i("---", "mTopStatus " + mTopStatus);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultW = wSize;
        int resultH = hSize;
        if (wMode == MeasureSpec.AT_MOST) {
            resultW = wSize;
        }
        if (hMode == MeasureSpec.AT_MOST) {
            resultH = Utils.dip2px(mContext, 100);
        }
        setMeasuredDimension(resultW, resultH);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mContext = null;
        mBitmap.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mFirstX = (int) event.getX();
                mFirstY = (int) event.getY();
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - mLastTime > 500) {

                    if (checkPointPostion(mFirstX, mFirstY)) {
                        interupt = false;
                        return true;
                    }
                    interupt = true;

                } else {
                    interupt = true;
                }


                mLastTime = System.currentTimeMillis();
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (!interupt) {
                    if (checkPointPostion(mFirstX, mFirstY)) {
                        if (mOnTopClickListener != null) {
                            if (mTopStatus == TO_UP_STATUS) {
                                mOnTopClickListener.onTopOpen();
                                mTopStatus = TO_DOWN_STATUS;
                            } else {
                                mOnTopClickListener.onTopClose();
                                mTopStatus = TO_UP_STATUS;
                            }


                            invalidate();
                        }
                    }
                }
                break;


        }
        return true;
    }

    /**
     * 检查点是否在箭头周围
     * @param x
     * @param y
     * @return
     */
    private boolean checkPointPostion(int x, int y) {
        float dx = Math.abs(mImgPointX) - Math.abs(x);
        float dy = Math.abs(mImgPointY) - Math.abs(y);

        int dip2px = Utils.dip2px(mContext, 35);
        return Math.sqrt(dx * dx + dy * dy) < dip2px;

    }

    public interface OnTopClickListener {
        void onTopOpen();

        void onTopClose();
    }

    private OnTopClickListener mOnTopClickListener;

    public void setOnTopClickListener(OnTopClickListener onTopClickListener) {
        mOnTopClickListener = onTopClickListener;
    }
}
