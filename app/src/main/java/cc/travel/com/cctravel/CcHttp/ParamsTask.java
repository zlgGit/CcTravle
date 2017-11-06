package cc.travel.com.cctravel.CcHttp;

/**
 * Created by GW00070468 on 2017/8/15.
 */

public interface ParamsTask<V> {

    V url(String url);
    V addParams(String key, String value);
    V addHeader(String key, String value);
    V tag(String tag);
}
