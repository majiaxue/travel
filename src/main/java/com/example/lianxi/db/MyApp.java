package com.example.lianxi.db;

import android.app.Application;

import com.example.lianxi.dao.DbBeanDao;

public class MyApp extends Application{
    private static MyApp myApp;

    public static MyApp getMyApp() {
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp=this;
    }
}
