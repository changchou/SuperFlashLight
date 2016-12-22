package com.jikexueyuan.superflashlight;

import android.graphics.Color;
import android.view.View;

public class MainActivity extends Settings {

    public void onClick_controller(View view) {
        hideAllUI();
        if (mCurrentUIType != UIType.UI_TYPE_MAIN) {
            mUIMain.setVisibility(View.VISIBLE);
            mCurrentUIType = UIType.UI_TYPE_MAIN;
//            closeFlashLight();
            warningLightFlicker = false;
            screenBrightness(mDefaultScreenBrightness / 255f);
            if (mBulbCrossFadeFlag) {
                mDrawable.reverseTransition(0);
                mBulbCrossFadeFlag = false;
            }
            mPoliceState = false;

            mSharedPreferences.edit().putInt("warning_light_interval", mCurrentWarningLightTnterval).
                    putInt("police_light_interval", mCurrentPoliceLightTnterval).commit();
        } else {
            switch (mLastUIType) {
                case UI_TYPE_FLASHLIGHT:
                    mUIFlashlight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
                    break;
                case UI_TYPE_WRNINGLIGHT:
                    mUIWaringLight.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_WRNINGLIGHT;
                    screenBrightness(1f);
                    new WarningLightThread().start();
                    break;
                case UI_TYPE_MORSE:
                    mUIMorse.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_MORSE;
                    break;
                case UI_TYPE_BULB:
                    mUIBulb.setVisibility(View.VISIBLE);
                    mHideTextView.hide();
                    mHideTextView.setTextColor(Color.BLACK);
                    mCurrentUIType = UIType.UI_TYPE_BULB;
                    screenBrightness(1f);
                    break;
                case UI_TYPE_COLOR:
                    mUIColorLight.setVisibility(View.VISIBLE);
                    screenBrightness(1f);
                    mCurrentUIType = UIType.UI_TYPE_COLOR;
                    mHideTextViewColorLight.setTextColor(Color.rgb(
                            255 - Color.red(mCurrentColorLight),
                            255 - Color.green(mCurrentColorLight),
                            255 - Color.blue(mCurrentColorLight)));
                    mHideTextViewColorLight.hide();
                    break;
                case UI_TYPE_POLICE:
                    mUIPoliceLight.setVisibility(View.VISIBLE);
                    screenBrightness(1f);
                    mCurrentUIType = UIType.UI_TYPE_POLICE;
                    mHideTextViewPoliceLight.hide();
                    new PoliceThread().start();
                    break;
                case UI_TYPE_SETTINGS:
                    mUISettings.setVisibility(View.VISIBLE);
                    mCurrentUIType = UIType.UI_TYPE_SETTINGS;
                    break;
            }
        }
    }

    public void onClick_ToFlashlight(View view) {
        hideAllUI();
        mUIFlashlight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
        mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
    }

    public void onClick_ToWarningLight(View view) {
        hideAllUI();
        mUIWaringLight.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_WRNINGLIGHT;
        mCurrentUIType = UIType.UI_TYPE_WRNINGLIGHT;
        screenBrightness(1f);
        new WarningLightThread().start();
    }

    public void onClick_ToMorse(View view) {
        hideAllUI();
        mUIMorse.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_MORSE;
        mCurrentUIType = UIType.UI_TYPE_MORSE;

    }

    public void onClick_ToBulb(View view) {
        hideAllUI();
        mUIBulb.setVisibility(View.VISIBLE);
        mHideTextView.hide();
        mHideTextView.setTextColor(Color.BLACK);
        mLastUIType = UIType.UI_TYPE_BULB;
        mCurrentUIType = UIType.UI_TYPE_BULB;

    }

    public void onClick_ToColor(View view) {
        hideAllUI();
        mUIColorLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mLastUIType = UIType.UI_TYPE_COLOR;
        mCurrentUIType = UIType.UI_TYPE_COLOR;


        mHideTextViewColorLight.setTextColor(Color.rgb(
                255 - Color.red(mCurrentColorLight),
                255 - Color.green(mCurrentColorLight),
                255 - Color.blue(mCurrentColorLight)));
        mHideTextViewColorLight.hide();

    }

    public void onClick_ToPolice(View view) {
        hideAllUI();
        mUIPoliceLight.setVisibility(View.VISIBLE);
        screenBrightness(1f);
        mLastUIType = UIType.UI_TYPE_POLICE;
        mCurrentUIType = UIType.UI_TYPE_POLICE;
        mHideTextViewPoliceLight.hide();
        new PoliceThread().start();
    }

    public void onClick_ToSettings(View view) {
        hideAllUI();
        mUISettings.setVisibility(View.VISIBLE);
        mLastUIType = UIType.UI_TYPE_SETTINGS;
        mCurrentUIType = UIType.UI_TYPE_SETTINGS;

    }
}
