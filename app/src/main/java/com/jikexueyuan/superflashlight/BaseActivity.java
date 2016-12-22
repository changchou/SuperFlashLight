package com.jikexueyuan.superflashlight;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;


public class BaseActivity extends Activity {

    protected ImageView mImageViewFlashLight, mImageViewFlashLightController, mImageViewWarningLight1, mImageViewWarningLight2,
            mImageViewBulb;
    protected EditText mEditTextMorseCode;
    protected Camera mCamera;
    protected Camera.Parameters mParameters;

    protected SeekBar mSeekBarWarningLight;
    protected SeekBar mSeekBarPoliceLight;
    protected Button mBtnAddShortcut;
    protected Button mBtnRemoveShortcut;

    protected FrameLayout mUIFlashlight;
    protected LinearLayout mUIMain;
    protected LinearLayout mUIWaringLight;
    protected LinearLayout mUIMorse;
    protected FrameLayout mUIBulb;
    protected FrameLayout mUIColorLight;
    protected FrameLayout mUIPoliceLight;
    protected LinearLayout mUISettings;

    protected enum UIType {
        UI_TYPE_MAIN, UI_TYPE_FLASHLIGHT, UI_TYPE_WRNINGLIGHT, UI_TYPE_MORSE, UI_TYPE_BULB, UI_TYPE_COLOR,
        UI_TYPE_POLICE, UI_TYPE_SETTINGS
    }

    protected int mCurrentWarningLightTnterval = 500;
    protected int mCurrentPoliceLightTnterval = 100;

    protected UIType mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
    protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;

    protected int mDefaultScreenBrightness;
    protected int mFinishCount = 0;

    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUIFlashlight = (FrameLayout) findViewById(R.id.frame_layout_flashlight);
        mUIMain = (LinearLayout) findViewById(R.id.linearLayout_main);
        mUIWaringLight = (LinearLayout) findViewById(R.id.linearLayout_warning_light);
        mUIMorse = (LinearLayout) findViewById(R.id.linearLayout_morse);
        mUIBulb = (FrameLayout) findViewById(R.id.frameLayout_bulb);
        mUIColorLight = (FrameLayout) findViewById(R.id.frameLayout_color_light);
        mUIPoliceLight = (FrameLayout) findViewById(R.id.frameLayout_police_light);
        mUISettings = (LinearLayout) findViewById(R.id.linearLayout_settings);

        mImageViewFlashLight = (ImageView) findViewById(R.id.image_flashlight);
        mImageViewFlashLightController = (ImageView) findViewById(R.id.image_flashlight_controller);
        mImageViewWarningLight1 = (ImageView) findViewById(R.id.image_warning_light1);
        mImageViewWarningLight2 = (ImageView) findViewById(R.id.image_warning_light2);
        mEditTextMorseCode = (EditText) findViewById(R.id.etMorseCode);
        mImageViewBulb = (ImageView) findViewById(R.id.image_bulb);
        mSeekBarWarningLight = (SeekBar) findViewById(R.id.seekBar_warningLight);
        mSeekBarPoliceLight = (SeekBar) findViewById(R.id.seekBar_policeLight);
        mBtnAddShortcut = (Button) findViewById(R.id.btn_add_shortcut);
        mBtnRemoveShortcut = (Button) findViewById(R.id.btn_remove_shortcut);

        mDefaultScreenBrightness = getScreenBrightness();

        mSharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        mSeekBarWarningLight.setProgress(mCurrentWarningLightTnterval - 100);
        mSeekBarPoliceLight.setProgress(mCurrentPoliceLightTnterval - 50);

        mCurrentWarningLightTnterval = mSharedPreferences.getInt("warning_light_interval", 200);
        mCurrentPoliceLightTnterval = mSharedPreferences.getInt("police_light_interval", 100);


    }

    protected void hideAllUI() {
        mUIMain.setVisibility(View.GONE);
        mUIFlashlight.setVisibility(View.GONE);
        mUIWaringLight.setVisibility(View.GONE);
        mUIMorse.setVisibility(View.GONE);
        mUIBulb.setVisibility(View.GONE);
        mUIColorLight.setVisibility(View.GONE);
        mUIPoliceLight.setVisibility(View.GONE);
        mUISettings.setVisibility(View.GONE);
    }

    protected void screenBrightness(float value) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = value;
        getWindow().setAttributes(layoutParams);
    }

    protected int getScreenBrightness() {
        int value = 0;
        try {
            value = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFinishCount = 0;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish() {
        mFinishCount++;
        if (mFinishCount == 1) {
            Toast.makeText(this, "再按一次退出！", Toast.LENGTH_LONG).show();
        } else if (mFinishCount == 2) {
            super.finish();
        }
    }
}
