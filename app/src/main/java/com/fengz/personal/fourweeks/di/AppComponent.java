package com.fengz.personal.fourweeks.di;

import com.fengz.personal.fourweeks.base.MyApplication;
import com.fengz.personal.fourweeks.di.providers.ApplicationModule;
import com.fengz.personal.fourweeks.di.providers.ConfigModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * 创建时间：2019/1/17
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：DaggerAndroid Component
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ConfigModule.class,
        Business1Module.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
        abstract Builder configModule(ConfigModule configModule);

        /**
         * 基础引用模块
         * @param instance
         */
        @Override
        public void seedInstance(MyApplication instance) {
            configModule(new ConfigModule(instance));
        }
    }
}
