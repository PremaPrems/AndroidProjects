package com.example.prema.prema.Util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

/**
 * Created by prema on 28/8/17.
 */
public class preference {
    SharedPreferences sharedPreferences;
    public preference(Activity activity) {
       sharedPreferences = activity.getPreferences(activity.MODE_PRIVATE);

    }
    public void setSearch(String search) {
        sharedPreferences.edit().putString("search",search).commit();

    }
    public String getSearch() {
        return sharedPreferences.getString("search","Gowthami");
    }

}
