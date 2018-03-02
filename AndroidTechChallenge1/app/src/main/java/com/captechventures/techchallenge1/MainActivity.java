package com.captechventures.techchallenge1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == MainApplication.dateList) {
            MainApplication.dateList = new ArrayList<>();
        }

        // save the task list to preference
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();

        String json = gson.toJson(MainApplication.dateList);

        editor.putString(MainApplication.TAG, json);
        editor.apply();
    }

    /**
     * Called when the user taps the button
     */
    public void buttonClick(View view) {
        // Do something in response to button
        Log.d(LOG, "Button was pressed!");

        // serialize the arraylist
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("dateList", MainApplication.dateList);

        // pass dateList to second activity via intent
        Intent intent = new Intent(this, DisplayAppRunTimesActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
