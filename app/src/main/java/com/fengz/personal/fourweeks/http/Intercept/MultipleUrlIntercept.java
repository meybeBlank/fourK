package com.fengz.personal.fourweeks.http.Intercept;

import com.fengz.personal.fourweeks.BuildConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：多个Url切换
 */
public class MultipleUrlIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl oldUrl = originalRequest.url();
        Request.Builder builder = originalRequest.newBuilder();
        List<String> urlnameList = originalRequest.headers("url");
        if (urlnameList != null && urlnameList.size() > 0) {
            builder.removeHeader("url");
            String urlname = urlnameList.get(0);
            HttpUrl baseURL=null;
            if ("xiaok".equals(urlname)) {
                String url = BuildConfig.DEBUG ? BuildConfig.DEBUG_URL_XK : BuildConfig.RELEASE_URL_XK;
                baseURL = HttpUrl.parse(url);
            }
            //重建新的HttpUrl，需要重新设置的url部分
            HttpUrl newHttpUrl = oldUrl.newBuilder()
                    .scheme(baseURL.scheme())
                    .host(baseURL.host())
                    .port(baseURL.port())
                    .build();
            //获取处理后的新newRequest
            Request newRequest = builder.url(newHttpUrl).build();
            return  chain.proceed(newRequest);
        }else{
            return chain.proceed(originalRequest);
        }
    }
}
