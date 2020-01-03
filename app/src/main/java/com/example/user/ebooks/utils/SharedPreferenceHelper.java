package com.example.user.ebooks.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private static SharedPreferenceHelper currentInstance;
    private SharedPreferences sharedPreferences;
private SharedPreferenceHelper(Context context){
    sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
}
   public static SharedPreferenceHelper getCurrentInstance(Context context) {
        if (currentInstance == null) {
            currentInstance = new SharedPreferenceHelper(context);
        }
        return currentInstance;
    }

    private void saveBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void saveStringText(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "The user is not found");
    }

    public void setIsPremium(boolean isPremium){
        saveBoolean("isPremium",isPremium);
    }
    public boolean getIsPremium(){
        return getBoolean("isPremium");
    }
}

