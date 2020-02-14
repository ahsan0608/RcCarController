package com.example.ahsan.workingdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
/**
 * Created by ahsan on 8/5/2017.
 */

public class SplashActivity extends Activity{
    Button Rma;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
/*        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        Rma = (Button) findViewById(R.id.rma);
        Rma.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Rma.getBackground().setAlpha(100);
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent =new Intent(SplashActivity.this,DeviceList.class);
                        startActivity(intent);
                        Rma.getBackground().setAlpha(255);

                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
