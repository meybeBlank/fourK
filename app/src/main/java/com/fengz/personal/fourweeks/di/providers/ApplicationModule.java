package com.fengz.personal.fourweeks.di.providers;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 创建时间：2019/1/16
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：DaggerAndroid 基础Injection模块
 */
@Module(includes = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class})
public class ApplicationModule {
//    @Singleton
//    @Provides
//    Context provideApplicationContext(Application application) {
//        return application;
//    }
}
