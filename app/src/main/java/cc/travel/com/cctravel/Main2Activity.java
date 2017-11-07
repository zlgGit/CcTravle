package cc.travel.com.cctravel;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amap.api.maps.MapView;
import com.example.ola_ev.cctravlel.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.travel.com.cctravel.views.MyTestView;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.user_profile)
    ImageView mUserProfile;
    @BindView(R.id.chat_access)
    ImageView mChatAccess;
    @BindView(R.id.title_first_levle)
    RelativeLayout mTitleFirstLevle;
    @BindView(R.id.tablayout)
    MyTabLayout mTablayout;
    @BindView(R.id.title_levle)
    LinearLayout mTitleLevle;
    @BindView(R.id.mytest)
    MyTestView mMytest;
    @BindView(R.id.bottom)
    RelativeLayout mButtom;
    @BindView(R.id.buttom_in)
    RelativeLayout mButtomIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);

        mMytest.setOnTopClickListener(new MyTestView.OnTopClickListener() {

            @Override
            public void onTopOpen() {
                ViewPropertyAnimatorCompat animate = ViewCompat.animate(mButtom);
                animate.translationY(0).setDuration(300).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
            }

            @Override
            public void onTopClose() {
                Log.i("---", "top close");


                ViewPropertyAnimatorCompat animate = ViewCompat.animate(mButtom);
                animate.translationY(mButtomIn.getHeight()).setDuration(300).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
            }
        });
        mButtomIn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}
