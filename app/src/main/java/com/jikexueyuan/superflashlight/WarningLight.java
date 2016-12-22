package com.jikexueyuan.superflashlight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;

/**
 * Created by Mr.Z on 2016/2/26 0026.
 */
public class WarningLight extends FlashLight {

    protected boolean warningLightFlicker; //true 闪烁 false 停止闪烁
    protected boolean warningLightState; //true on-off  false off-on

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    class WarningLightThread extends Thread {
        @Override
        public void run() {
            warningLightFlicker = true;
            while (warningLightFlicker) {
                try {
                    Thread.sleep(300);
                    mWarningHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Handler mWarningHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (warningLightState) {
                mImageViewWarningLight1.setImageResource(R.drawable.warning_light_on);
                mImageViewWarningLight2.setImageResource(R.drawable.warning_light_off);
                warningLightState = false;
            } else {
                mImageViewWarningLight1.setImageResource(R.drawable.warning_light_off);
                mImageViewWarningLight2.setImageResource(R.drawable.warning_light_on);
                warningLightState = true;
            }
        }
    };
}
