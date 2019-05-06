package com.fengz.personal.fourweeks.http.Intercept;

import android.os.Build;

import com.fengz.personal.fourweeks.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：网络请求头Intercept
 */
public class HeaderIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.header("platform", "Android")
                .addHeader("api-version", BuildConfig.VERSION_NAME);
        return chain.proceed(builder.build());
    }
}
