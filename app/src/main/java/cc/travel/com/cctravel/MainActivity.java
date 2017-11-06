package cc.travel.com.cctravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.ola_ev.cctravlel.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.travel.com.cctravel.MyRequests.MyPostForM;
import cc.travel.com.cctravel.MyRequests.PostHttp;

public class MainActivity extends AppCompatActivity implements MyTabLayout.OnTabClickListener {


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

    @BindView(R.id.location)
    ImageView mLocation;
    @BindView(R.id.tltle_link)
    TransLationView mTltleLink;
    @BindView(R.id.bottom_level)
    FrameLayout mBottomLevel;
    @BindView(R.id.user_action)
    TransBottomView mUserAction;
    @BindView(R.id.user_action2)
    FrameLayout mUserAction2;
    private AMap mMap;
    private String mCurrentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);
        mMap = mMapView.getMap();
        mTablayout.setOnTabClickListener(this);
        mCurrentTab = MyTabLayout.TYPE_KUAICHE;
        MyPostForM myPostForM = new MyPostForM();
        myPostForM.login("root","root");
        try {
            new Thread().join();
            SharedPreferences sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();




        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_access);

        int width = bitmap.getWidth();

        Log.i("--直接","直接 width"+width);

        decodeResorce(getResources(),R.drawable.user_access);

    }

    @OnClick({R.id.user_profile, R.id.chat_access})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.chat_access:
//                File file = Environment.getExternalStorageDirectory();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chatmsg);
//                File file1 = new File(file, "test.png");
//                try {
//                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
//                    boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
//                    boolean compress2 = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
//                    fileOutputStream.flush();
//                    fileOutputStream.close();
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                PostHttp postHttp = new PostHttp();
                postHttp.postImgT();


                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


    @Override
    public void onTabClick(String tab) {
        Log.i("--", "tab click" + tab);
        switch (tab) {
            case MyTabLayout.TYPE_DAIJIA:
                mTltleLink.addView(MyTabLayout.TYPE_DAIJIA);
                mUserAction.addView(MyTabLayout.TYPE_DAIJIA);
                break;
            case MyTabLayout.TYPE_FENSHIZULIN:
                mTltleLink.addView(MyTabLayout.TYPE_FENSHIZULIN);
                mUserAction.addView(MyTabLayout.TYPE_FENSHIZULIN);
                break;
            case MyTabLayout.TYPE_KUAICHE:
                mTltleLink.addView(MyTabLayout.TYPE_KUAICHE);
                mUserAction.addView(MyTabLayout.TYPE_KUAICHE);
                break;
            case MyTabLayout.TYPE_SHUNFENGCHE:
                mTltleLink.addView(MyTabLayout.TYPE_SHUNFENGCHE);
                mUserAction.addView(MyTabLayout.TYPE_SHUNFENGCHE);
                break;
        }
        mCurrentTab = tab;
    }
    private Bitmap decodeResorce(Resources resources,int id)
    {
        TypedValue typedValue = new TypedValue();
        resources.openRawResource(id,typedValue);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity=typedValue.density;

        Log.i("---","density"+typedValue.density);

        Bitmap bitmap = BitmapFactory.decodeResource(resources, id);
        int width = bitmap.getWidth();
        Log.i("---","width"+width);
        return bitmap;

    }
}
