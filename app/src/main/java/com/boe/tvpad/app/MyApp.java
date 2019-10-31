package com.boe.tvpad.app;

import android.app.Application;
import android.content.Context;

import com.boe.tvpad.utils.ToastMgr;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;


public class MyApp extends Application {
    private static MyApp application;
    @Override
    public void onCreate() {
        super.onCreate();
        ToastMgr.init(this);
        // 必须在调用任何统计SDK接口之前调用初始化函数
        UMConfigure.init(this, "5db8f2370cafb26c0e0008e1", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setCatchUncaughtExceptions(true);
        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true);
    }

    public static MyApp getInstance() {
        return application;
    }
}

