package com.example.facebookloginauthentication.ui;

import android.app.Application;

import com.appizona.yehiahd.fastsave.FastSave;

public class MyApp extends Application{

        @Override
        public void onCreate() {
            super.onCreate();
            FastSave.init(getApplicationContext());
        }
}
