package com.example.net;

import android.util.Log;

import com.example.net.converter.CustomGsonConverterFactory;
import com.google.gson.Gson;
import com.wmc.sp.SPUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitFactory {

    /**
     * 单例
     */
    private static RetrofitFactory instance=new RetrofitFactory();
    private Retrofit retrofit;

    private RetrofitFactory(){
        initRetrofit();
    }

    public static RetrofitFactory getInstance(){
        return instance;
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.Baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    /**
     * 创建OKHttpClient
     * @return
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                //http拦截器
                .addNetworkInterceptor(createLogInterceptor())
                //添加token头信息
                .addInterceptor(createTokenInterceptor())
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        return build;
    }

    /**
     * 添加token头信息
     * @return
     */
    private Interceptor createTokenInterceptor() {
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("date", "Sat, 12 Dec 2020 03")
                        .addHeader("server", "Apache-Coyote/1.1")
                        .addHeader("transfer-encoding", "chunked")
                        .addHeader("content-type", "application/json")
                        .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjMifQ.xKPoMpjMrGHF2iDgqhXrvyypo8HGEZtqDcnND2tQyPo")
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }


    /**
     * http拦截器
     * @return
     */
    private Interceptor createLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     *    Retrofit
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }

}
