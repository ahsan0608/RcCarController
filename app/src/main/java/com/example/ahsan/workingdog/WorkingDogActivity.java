package com.example.ahsan.workingdog;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
/**
 * Created by ahsan on 8/5/2017.
 */

public class WorkingDogActivity extends Activity {

    MediaPlayer dogBurking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workingdog);
        dogBurking = MediaPlayer.create(WorkingDogActivity.this,R.raw.dog);
        dogBurking.start();

        Thread timer = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent1 = new Intent(WorkingDogActivity.this,DeviceList.class);
                    startActivity(intent1);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dogBurking.release();
        finish();
    }
}
