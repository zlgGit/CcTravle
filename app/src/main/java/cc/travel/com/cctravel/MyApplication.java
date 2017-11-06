package cc.travel.com.cctravel;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by GW00070468 on 2017/10/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
