package com.example.user.ebooks;

import android.app.Application;

import io.realm.Realm;

public class BookApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
