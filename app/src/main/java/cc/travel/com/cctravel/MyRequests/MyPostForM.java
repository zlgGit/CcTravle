package cc.travel.com.cctravel.MyRequests;

import cc.travel.com.cctravel.CcHttp.CcCallBack;
import cc.travel.com.cctravel.CcHttp.CcHttp;
import cc.travel.com.cctravel.beans.BaseBean;

/**
 * Created by GW00070468 on 2017/10/26.
 */

public class MyPostForM extends CcHttp {

    public void login(String username,String password)
    {
        CcHttp<BaseBean> objectCcHttp = new CcHttp<>();
        objectCcHttp.postForm()
                .url("http://10.51.9.14:8080/user/login")
                .addParams("username",username)
                .addParams("password",password)
                .exucute(new CcCallBack<BaseBean>() {
                    @Override
                    public void onFailure(String error) {

                    }

                    @Override
                    public void onSuccess(BaseBean o) {


                    }
                });

    }

}
