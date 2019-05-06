package com.fengz.personal.fourweeks.http.exception;

import com.fengz.personal.fourweeks.http.ResponseShuck;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * 创建时间：2018/11/5
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：网络异常信息转换工厂
 */
public class ErrExceptionFactory {

    private ErrExceptionFactory() {
    }

    public static String create(Throwable exception) {
        String message = null;
        if (exception instanceof JsonSyntaxException) {
            message = "数据返回格式异常";
        } else if (exception instanceof HttpException) {
            message = "网络异常";
        } else if (exception instanceof UnknownHostException
                || exception instanceof ConnectException
                || exception instanceof SocketTimeoutException) {
            message = "服务器连接异常";
        } else if (exception instanceof ApiException) {
            return exception.getMessage();
        } else {
            message = "未知异常信息";
        }
        return message;
    }

    public static ApiException create(ResponseShuck response) {
        String message = null;

        switch (response.getCode()) {
//            case 0: // 正常返回数据
            default:
                message = response.getMsg();
                break;
        }
        return new ApiException(message);
    }
}
