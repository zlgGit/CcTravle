package cc.travel.com.cctravel.MyRequests;

import android.os.Environment;
import java.io.File;
import cc.travel.com.cctravel.CcHttp.CcCallBack;
import cc.travel.com.cctravel.CcHttp.CcHttp;
import cc.travel.com.cctravel.beans.BaseBean;

/**
 * Created by GW00070468 on 2017/10/26.
 */

public class PostHttp extends CcHttp<BaseBean> {

    public void postImgT() {

        File file = Environment.getExternalStorageDirectory();
        File file1 = new File(file, "test.png");
        postImg().url("http://10.51.23.15:8080/test/upImage")
                .addParams("file", file1.getAbsolutePath())
                .exucute(new CcCallBack<BaseBean>() {
                    @Override
                    public void onFailure(String error) {

                    }

                    @Override
                    public void onSuccess(BaseBean baseBean) {

                    }
                });

    }
}
