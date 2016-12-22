package com.jikexueyuan.superflashlight;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jikexueyuan.superflashlight.widget.HideTextView;

/**
 * Created by Mr.Z on 2016/2/27 0027.
 */
public class ColorLight extends Bulb implements ColorPickerDialog.OnColorChangedListener {
    protected int mCurrentColorLight = Color.RED;
    protected HideTextView mHideTextViewColorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHideTextViewColorLight = (HideTextView) findViewById(R.id.text_hide_color_light);
    }

    @Override
    public void colorChanged(int color) {
        mUIColorLight.setBackgroundColor(color);
        mCurrentColorLight = color;
    }

    public void onClick_showColorPicker(View view) {
        new ColorPickerDialog(this, this, Color.RED).show();
    }
}
