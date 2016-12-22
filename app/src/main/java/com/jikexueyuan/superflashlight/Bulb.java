package com.jikexueyuan.superflashlight;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;

import com.jikexueyuan.superflashlight.widget.HideTextView;

/**
 * Created by Mr.Z on 2016/2/27 0027.
 */
public class Bulb extends Morse {
    protected boolean mBulbCrossFadeFlag;
    protected TransitionDrawable mDrawable;
    protected HideTextView mHideTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawable = (TransitionDrawable) mImageViewBulb.getDrawable();
        mHideTextView = (HideTextView) findViewById(R.id.text_hide_bulb);
    }


    public void onClick_bulb(View view) {
        if (!mBulbCrossFadeFlag) {
            mDrawable.startTransition(500);
            mBulbCrossFadeFlag = true;
            screenBrightness(1f);
        } else {
            mDrawable.reverseTransition(500);
            mBulbCrossFadeFlag = false;
            screenBrightness(0f);
        }
    }
}
