package cc.travel.com.cctravel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by GW00070468 on 2017/10/25.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        this(context,null);
    }
    private long mLastClickTime;
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction()== MotionEvent.ACTION_DOWN) {
            long clickTime = System.currentTimeMillis();
            if (clickTime-mLastClickTime>600) {
                setClickable(true);

            }else {
                setClickable(false);
            }
            mLastClickTime=clickTime;
        }

        return super.onTouchEvent(ev);
    }

}
