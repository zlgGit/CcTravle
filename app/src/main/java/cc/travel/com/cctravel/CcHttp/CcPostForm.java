package cc.travel.com.cctravel.CcHttp;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GW00070468 on 2017/10/25.
 */

public class CcPostForm<T> implements ParamsTask<CcPostForm<T>>,CcRequesTask<T> {

    private Request mRequest;
    private Headers mHeaders;
    private OkHttpClient mOkHttpClient=new OkHttpClient();
    private CcCallBack mCcCallBack;
    private Class<T> cls;
    private CcRequest mCcRequest;
    @Override
    public CcPostForm<T> url(String url) {
        return this;
    }

    @Override
    public CcPostForm<T> addParams(String key, String value) {
        return this;
    }

    @Override
    public CcPostForm<T> addHeader(String key, String value) {
        return this;
    }

    @Override
    public CcPostForm<T> tag(String tag) {
        return this;
    }

    @Override
    public void exucute(final CcCallBack<T> ccCallBack) {
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                String content = new String();


                Observable.just(content)
                        .subscribeOn(Schedulers.io())
                        .map(new Function<String, Object>() {

                            @Override
                            public Object apply(@NonNull String s) throws Exception {
                                Gson gson = new Gson();
                                Object t = gson.fromJson(s, cls);
                                return t;
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                ccCallBack.onSuccess(((T) o));
                            }
                        });

            }
        });

    }

    @Override
    public void add(CcRequest ccRequest) {

        this.mCcRequest=ccRequest;
        tranCcReq2OkReq(ccRequest);

    }


    public void tranCcReq2OkReq(CcRequest ccRequest) {

        Map<String, String> params = ccRequest.mParams;
        FormBody.Builder forMbuilder = new FormBody.Builder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            forMbuilder.add(next.getKey(),next.getValue());

        }
        Headers.Builder builder = new Headers.Builder();
        Map<String, String> headers = mCcRequest.mHeaders;
        Set<Map.Entry<String, String>> headerSet = headers.entrySet();
        Iterator<Map.Entry<String, String>> headerIterator = headerSet.iterator();

        if (headerIterator.hasNext()) {
            Map.Entry<String, String> next = headerIterator.next();
            builder.add(next.getKey(), next.getValue());
        }
        mHeaders = builder.build();
        mRequest = new Request.Builder().url(ccRequest.url)
                .headers(mHeaders)
                .post(forMbuilder.build()).build();
    }

    @Override
    public void add(Class cls) {
        this.cls=cls;
    }
}
