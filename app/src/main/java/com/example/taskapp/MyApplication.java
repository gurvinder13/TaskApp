package com.example.taskapp;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.example.taskapp.network.BaseRetrofitHandler;

public class MyApplication extends MultiDexApplication {
    private static final String TAG = "MyApplication";
    private static Context context;
    private static MyApplication myApplication;

    private static synchronized MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        context = this;
        BaseRetrofitHandler.getInstance().setupRetrofitAndOkHttp();
    }
}
