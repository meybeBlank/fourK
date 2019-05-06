package com.fengz.personal.fourweeks.di.providers;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 创建时间：2019/1/17
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：基础配置注入类
 */
@Module(includes = {ServiceModule.class})
public class ConfigModule {

    private Application application;

    public ConfigModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return this.application;
    }
}
