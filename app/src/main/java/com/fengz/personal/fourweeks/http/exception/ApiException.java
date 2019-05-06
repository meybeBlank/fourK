package com.fengz.personal.fourweeks.http.exception;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：网络请求异常
 */
public class ApiException extends RuntimeException{

    public ApiException(String excetionMsg) {
        super(excetionMsg);
    }
}
