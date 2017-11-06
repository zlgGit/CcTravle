package cc.travel.com.cctravel;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ola_ev.cctravlel.R;

/**
 * Created by GW00070468 on 2017/10/25.
 */

public class TransLationView extends FrameLayout {

    private Context mContext;

    public TransLationView(@NonNull Context context) {
        this(context, null);
    }

    public TransLationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransLationView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * 添加view展示动画，这里封装到了自定义View，
     * 用来解决添加动画操作，严格注意，主界面的tab切换速度一个要慢
     * 动画的执行速度。
     * @param type
     */
    public void addView(String type) {
        switch (type) {

            case MyTabLayout.TYPE_KUAICHE:
                View viewTitle1 = LayoutInflater.from(mContext).inflate(R.layout.title_link, null);
                addView(viewTitle1, 0, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                transOtherView();
                break;
            case MyTabLayout.TYPE_DAIJIA:
                View viewTitle2= LayoutInflater.from(mContext).inflate(R.layout.title_link, null);
                addView(viewTitle2, 0, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                transOtherView();
                break;
            case MyTabLayout.TYPE_SHUNFENGCHE:
                View viewTitle3= LayoutInflater.from(mContext).inflate(R.layout.title_link, null);
                addView(viewTitle3, 0, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                transOtherView();
                break;
            case MyTabLayout.TYPE_FENSHIZULIN:
                View viewTitle4= LayoutInflater.from(mContext).inflate(R.layout.title_link, null);
                addView(viewTitle4, 0, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                transOtherView();
                break;

        }
    }

    public void transOtherView() {
        int childCount = getChildCount();
        if (childCount > 1) {
            for (int i = 2; i < childCount; i++) {
                removeViewAt(i);
            }
            View childAtOne = getChildAt(1);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(childAtOne, "translationX", 0, childAtOne.getMeasuredWidth());
            translationY.setDuration(400);
            translationY.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    removeViewAt(1);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    removeViewAt(1);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            translationY.start();
        }

    }
}
