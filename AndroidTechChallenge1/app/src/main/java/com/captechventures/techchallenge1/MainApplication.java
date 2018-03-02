package com.captechventures.techchallenge1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mluansing on 9/8/17.
 */

public class MainApplication extends Application {

    public static ArrayList<String> dateList;
    public static final String TAG = "key";
    private static final String LOG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG, "TechChallenge1 App started!");

        // grab timestamp
        DateFormat df = new SimpleDateFormat("E, MMM dd, yyyy HH:mm:ss z");
        Date current_date = new Date();
        String now = df.format(current_date);

        // load tasks from preference
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = sharedPrefs.getString(TAG, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        dateList = gson.fromJson(json, type);

        // null handling
        if (null == dateList) {
            dateList = new ArrayList<>();
        }

        Log.v(LOG, "adding " + now + " to dateList");
        dateList.add(now);

    }

}
