package cc.travel.com.cctravel.CcHttp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by GW00070468 on 2017/8/14.
 */

public class CcHttp<T> implements ParamsTask<CcHttp<T>> {

    protected String url;
    protected Map<String, String> params = new HashMap<>();
    protected Map<String, String> headers = new HashMap<>();
    protected Object tag;
    protected Class<T> cls;
    protected CcRequest mCcRequest;

    protected CcRequesTask<T> mHttpTask;

    public CcHttp() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();

        ParameterizedType superclass = null;
        if (genericSuperclass instanceof ParameterizedType) {
            superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        }

        if (superclass != null && superclass.getActualTypeArguments().length != 0) {
            cls = (Class<T>) superclass.getActualTypeArguments()[0];
        } else {

        }

    }

    public CcHttp<T> get() {

        mHttpTask = new CcGetJson<T>();
        return this;
    }

    public CcHttp<T> post() {

        mHttpTask = new CcGetJson<T>();
        return this;
    }

    public CcHttp<T> postForm() {

        mHttpTask = new CcPostForm<T>();
        return this;
    }
    public CcHttp<T> postImg()
    {
        mHttpTask = new CcPostImg<>();
        return this;
    }


    @Override
    public CcHttp<T> url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public CcHttp<T> addParams(String key, String value) {
        params.put(key, value);
        return this;
    }

    @Override
    public CcHttp<T> addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    @Override
    public CcHttp<T> tag(String tag) {
        this.tag = tag;
        return this;
    }


    public void exucute(CcCallBack<T> ccCallBack) {
        mCcRequest = new CcRequest.Builder()
                .url(this.url)
                .tag(this.tag)
                .params(this.params)
                .headers(this.headers).build();
        mHttpTask.add(mCcRequest);
        mHttpTask.add(this.cls);
        mHttpTask.exucute(ccCallBack);
    }


}
