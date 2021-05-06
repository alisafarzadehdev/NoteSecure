package com.alisafarzadeh.notetask;

import android.app.Application;
import android.content.Context;

public class G extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        super.onCreate();
    }
}
