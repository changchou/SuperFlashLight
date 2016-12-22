package com.jikexueyuan.superflashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jikexueyuan.superflashlight.widget.HideTextView;

/**
 * Created by Mr.Z on 2016/2/27 0027.
 */
public class PoliceLight extends ColorLight {

    protected boolean mPoliceState;
    protected HideTextView mHideTextViewPoliceLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHideTextViewPoliceLight = (HideTextView) findViewById(R.id.text_hide_police_light);

    }

    class PoliceThread extends Thread {
        @Override
        public void run() {
            super.run();
            mPoliceState = true;
            while (mPoliceState) {
                try {
                    mHandler.sendEmptyMessage(Color.BLUE);
                    sleep(100);
                    mHandler.sendEmptyMessage(Color.BLACK);
                    sleep(100);
                    mHandler.sendEmptyMessage(Color.RED);
                    sleep(100);
                    mHandler.sendEmptyMessage(Color.BLACK);
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int color = msg.what;
            mUIPoliceLight.setBackgroundColor(color);
        }
    };
}
