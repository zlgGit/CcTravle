package cc.travel.com.cctravel.CcHttp;

/**
 * Created by GW00070468 on 2017/8/15.
 */

public interface CcRequesTask<T>  {

    void exucute(CcCallBack<T> ccCallBack);
    void add(CcRequest ccRequest);
    void add(Class cls);
    void tranCcReq2OkReq(CcRequest ccRequest);

}
