package cc.travel.com.cctravel;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GW00070468 on 2017/10/25.
 */

public class MyTabLayout extends TabLayout implements View.OnClickListener {
    public static final String TYPE_KUAICHE = "快车";
    public static final String TYPE_DAIJIA = "代驾";
    public static final String TYPE_SHUNFENGCHE = "顺风车";
    public static final String TYPE_FENSHIZULIN = "分时租赁";
    private long mLastClickTime;
    private Context mContext;
    private long mLastTime;

    public MyTabLayout(Context context) {
        this(context, null);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initTab();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - mLastClickTime > 600) {
                setClickable(true);

            } else {
                setClickable(false);
            }
            mLastClickTime = clickTime;
        }

        return super.onTouchEvent(ev);
    }


    public void initTab() {
        MyTextView t1 = new MyTextView(mContext);
        t1.setOnClickListener(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        t1.setLayoutParams(params);
        t1.setText("快车");
        t1.setTag("快车");
        addTab(newTab().setCustomView(t1).setTag("快车"), true);

        MyTextView t2 = new MyTextView(mContext);
        t2.setOnClickListener(this);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        t2.setLayoutParams(params2);
        t2.setText("顺风车");
        t2.setTag("顺风车");
        addTab(newTab().setCustomView(t2).setTag("顺风车"));

        MyTextView t3 = new MyTextView(mContext);
        t3.setOnClickListener(this);
        ViewGroup.LayoutParams params3 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        t3.setLayoutParams(params3);
        t3.setText("代驾");
        t3.setTag("代驾");
        addTab(newTab().setCustomView(t3).setTag("代驾"));

        MyTextView t4 = new MyTextView(mContext);
        t4.setOnClickListener(this);
        ViewGroup.LayoutParams params4 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        t4.setLayoutParams(params4);
        t4.setText("分时租赁");
        t4.setTag("分时租赁");
        addTab(newTab().setCustomView(t4).setTag("分时租赁"));

    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis-mLastTime<600) {

            return;
        }
        switch (tag) {
            case TYPE_DAIJIA:
                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onTabClick(TYPE_DAIJIA);
                }

                break;
            case TYPE_KUAICHE:
                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onTabClick(TYPE_KUAICHE);
                }
                break;
            case TYPE_FENSHIZULIN:
                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onTabClick(TYPE_FENSHIZULIN);
                }
                break;
            case TYPE_SHUNFENGCHE:
                if (mOnTabClickListener != null) {
                    mOnTabClickListener.onTabClick(TYPE_SHUNFENGCHE);
                }
                break;
        }
        mLastTime=currentTimeMillis;
    }

    /**
     * 自定义控件监听事件，因为点击触发动画的时候是不允许其他tab有点击行为的
     * 自带控件不能实现，tablayout的tab能够设置自定义View，所有这里自己设置Tab属性
     * @param onTabClickListener
     */
    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        mOnTabClickListener = onTabClickListener;
    }

    private OnTabClickListener mOnTabClickListener;

    public interface OnTabClickListener {
        void onTabClick(String tab);
    }
}
