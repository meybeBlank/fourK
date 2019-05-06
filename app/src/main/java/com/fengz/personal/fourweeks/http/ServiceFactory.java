package com.fengz.personal.fourweeks.http;


import com.fengz.personal.fourweeks.BuildConfig;
import com.fengz.personal.fourweeks.http.Intercept.HeaderIntercept;
import com.fengz.personal.fourweeks.http.Intercept.MultipleUrlIntercept;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：网络Service工厂
 */
public class ServiceFactory {

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10;

    private final Retrofit mRetrofit;

    private ServiceFactory() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderIntercept())
                .addInterceptor(new MultipleUrlIntercept());

        /**
         * debug打印接口日志
         */
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        String url = BuildConfig.DEBUG ? BuildConfig.DEBUG_URL : BuildConfig.RELEASE_URL;

        mRetrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    public static ServiceFactory getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static ServiceFactory instance = new ServiceFactory();
    }

    public <T> T getService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }
}
