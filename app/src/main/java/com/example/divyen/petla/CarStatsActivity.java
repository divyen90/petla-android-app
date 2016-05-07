package com.example.divyen.petla;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class CarStatsActivity extends ActionBarActivity {

    boolean timer;

    long startTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_stats);
        timer = true;
        startTimerThread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void startTimerThread() {


        final long startTime = System.currentTimeMillis();
        Runnable runnable = new Runnable() {
            Handler handler = new Handler();
            public void run() {

                while (timer) {
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable(){
                        public void run() {
                            TextView timerId = (TextView) findViewById(R.id.timer);
                            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

                            long seconds = elapsedTime%60;

                            timerId.setText(elapsedTime/60+"m "+seconds+"s ");
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }
}
