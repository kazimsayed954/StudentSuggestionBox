package com.kazim.studentsuggestionbox;

import android.app.Application;

public class GlobalClass extends Application {
    public static final String BASE_URL = "http://192.168.56.1/android_login_api/";
//    public static final String BASE_URL = "http:/localhost:8888/androidApp/api.php";

    private static GlobalClass singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static GlobalClass getInstance() {
        return singleton;
    }
}
