package io.ac.starter.util;

import okhttp3.*;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-05-06
 **/
public final class HttpClient {
    private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded");

    private static OkHttpClient getOkHttpClient(boolean retry) {

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        if (retry) {
            httpBuilder.addInterceptor(new RetryInterceptor());
        }
        return httpBuilder.build();
    }


    public static void httpPostAsyn(String url, String param,boolean retry, Callback callback) {

        OkHttpClient okHttpClient = getOkHttpClient(retry);
        RequestBody body = RequestBody.create(FORM, param);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }


    public static String httpPostSyn(String url,String param,boolean retry) throws IOException {

        OkHttpClient okHttpClient = getOkHttpClient(retry);
        RequestBody body = RequestBody.create(FORM, param);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }



    public static void httpGetAsyn(String url,boolean retry,Callback callback) {

        OkHttpClient okHttpClient = getOkHttpClient(retry);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static String httpGetSyn(String url,boolean retry) throws IOException {

        OkHttpClient okHttpClient = getOkHttpClient(retry);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    private HttpClient() { }

    public static class RetryInterceptor implements Interceptor {

        private static final int MAX_RETRY_COUNT = 2;
        private int retryCount;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryCount < MAX_RETRY_COUNT) {
                retryCount++;
                response = chain.proceed(request);
            }
            return response;
        }
    }


}

