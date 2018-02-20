package com.giant.jamie.webapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private String urlString = "your url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebAPITask webAPITask = new WebAPITask("POST");

                try {

                    webAPITask.execute(urlString);

                }catch (Exception e){

                    Log.i(TAG, e.toString());
                }
            }
        });

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebAPITask webAPITask = new WebAPITask("GET");

                try {

                    webAPITask.execute(urlString);

                }catch (Exception e){

                    Log.i(TAG, e.toString());
                }

            }
        });

    }
}
