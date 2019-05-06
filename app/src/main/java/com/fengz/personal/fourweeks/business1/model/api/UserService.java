package com.fengz.personal.fourweeks.business1.model.api;

import com.fengz.personal.fourweeks.business1.model.entity.CheckVersionBean;
import com.fengz.personal.fourweeks.business1.model.entity.JokeModule;
import com.fengz.personal.fourweeks.business1.model.entity.UserModel;
import com.fengz.personal.fourweeks.http.ResponseShuck;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 创建时间：2018/11/6
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：用户模块接口
 */
public interface UserService {

    /**
     * 创建时间：2018/11/6
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：用户注册，这里只是完成功能，并没有实际意义
     */
    @GET("/createUser")
    Observable<ResponseShuck<UserModel>> register(
            @Query("key") String key,
            @Query("phone") String phone,
            @Query("passwd") String password
    );

    /**
     * 创建时间：2018/11/6
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：登录，这里只是完成功能，并没有实际意义
     */
    @GET("/login")
    Observable<ResponseShuck<UserModel>> login(
            @Query("key") String key,
            @Query("phone") String phone,
            @Query("passwd") String password
    );

    /**
     * 创建时间：2019/1/22
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：获取段子列表
     */
    @GET("/satinApi")
    Observable<ResponseShuck<List<JokeModule>>> getJokes(
            @Query("type") int type,
            @Query("page") int page
    );

    /**
     * 创建时间：2019/3/12
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：检测是否需要升级
     */
    @Headers("url:xiaok")
    @GET("solitaire/sys/canUpSys")
    Observable<ResponseShuck<CheckVersionBean>> checkUpload();
}
