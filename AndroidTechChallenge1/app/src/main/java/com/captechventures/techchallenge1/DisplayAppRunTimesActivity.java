package com.captechventures.techchallenge1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DisplayAppRunTimesActivity extends AppCompatActivity {

    public ListView listview;
    public ArrayAdapter<String> adapter;
    private static final String LOG = DisplayAppRunTimesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_app_run_times);
        Log.d("second", "opening next activity!");

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // get dateList from serialized bundle
        MainApplication.dateList = bundle.getStringArrayList("dateList");

        // get listview
        listview = (ListView) findViewById(R.id.listview);

        // set up adapter for listview
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainApplication.dateList);
        listview.setAdapter(adapter);

        Log.v(LOG, "list of dates: " + MainApplication.dateList.toString());
    }

}
