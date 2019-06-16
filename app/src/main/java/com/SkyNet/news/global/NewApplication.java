package com.SkyNet.news.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;

/**
 * 项目的Application
 * 这个东西是提供全局上下文的。 可以不用很理解
 */

public class NewApplication extends Application {
    private static Context context;
    private static int mainThreadId;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        context = getApplicationContext();
        // 获取当前主线程id
        mainThreadId = android.os.Process.myTid();
        handler = new Handler();
    }

    public static Context getContext() {
        return context;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Handler getHandler() {
        return handler;
    }
}
