package cc.travel.com.cctravel.CcHttp;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by GW00070468 on 2017/10/26.
 */

public class CcPostImg<T> implements CcRequesTask<T> {
    private Headers mHeaders;
    private Request mRequest;
    private OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    public void exucute(CcCallBack<T> ccCallBack) {
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("-- onFailure", call.toString());
                Log.i("-- onFailure ", "IOException"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().toString();
                Log.i("-- onResponse", string);

            }
        });
    }

    @Override
    public void add(CcRequest ccRequest) {

        tranCcReq2OkReq(ccRequest);
    }

    @Override
    public void add(Class cls) {

    }

    @Override
    public void tranCcReq2OkReq(CcRequest ccRequest) {
        Map<String, String> params = ccRequest.mParams;

        String file = params.get("file");
        File fileUp = new File(file);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), fileUp);
        builder.setType(MultipartBody.FORM)
                .addFormDataPart("file", fileUp.getName(), fileBody);
        MultipartBody multipartBody = builder.build();

        Headers.Builder ccRequestBuilder = new Headers.Builder();
        Map<String, String> headers = ccRequest.mHeaders;
        Set<Map.Entry<String, String>> headerSet = headers.entrySet();
        Iterator<Map.Entry<String, String>> headerIterator = headerSet.iterator();

        if (headerIterator.hasNext()) {
            Map.Entry<String, String> next = headerIterator.next();
            ccRequestBuilder.add(next.getKey(), next.getValue());
        }
        this.mHeaders = ccRequestBuilder.build();
        mRequest = new Request.Builder().url(ccRequest.url)
                .headers(mHeaders)
                .post(multipartBody).build();


    }
}
