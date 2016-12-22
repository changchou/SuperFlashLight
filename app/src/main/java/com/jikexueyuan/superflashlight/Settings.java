package com.jikexueyuan.superflashlight;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by Mr.Z on 2016/2/28 0028.
 */
public class Settings extends PoliceLight implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSeekBarWarningLight.setOnSeekBarChangeListener(this);
        mSeekBarPoliceLight.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()) {
            case R.id.seekBar_warningLight:
                mCurrentWarningLightTnterval = progress + 100;
                break;
            case R.id.seekBar_policeLight:
                mCurrentPoliceLightTnterval = progress + 50;
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private boolean shortcutInScreen() {
        Cursor cursor = getContentResolver().query(
                Uri.parse("content://com.cyanogenmod.trebuchet.settings/favortites"),
                null,
                "intent like ?",
                new String[]{"%component = com.jikexueyuan.superflashlight/.MainActivity%"},
                null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void onClick_addShortcut(View view) {

        if (!shortcutInScreen()) {
            Intent installShortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.drawable.light_icon);

            Intent flashlightIntent = new Intent();
            flashlightIntent.setClassName("com.jikexueyuan.superflashlight", "com.jikexueyuan.superflashlight.MainActivity");
            flashlightIntent.setAction(Intent.ACTION_MAIN);
            flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            installShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
            sendBroadcast(installShortcut);
            Toast.makeText(this, "已成功添加快捷方式到桌面！", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "快捷方式已存在！", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_removeShortcut(View view) {
        if (!shortcutInScreen()) {
            Intent uninstallShortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
            uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "超级手电筒");

            Intent flashlightIntent = new Intent();
            flashlightIntent.setClassName("com.jikexueyuan.superflashlight", "com.jikexueyuan.superflashlight.MainActivity");
            flashlightIntent.setAction(Intent.ACTION_MAIN);
            flashlightIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            uninstallShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, flashlightIntent);
            sendBroadcast(uninstallShortcut);
        }else {
            Toast.makeText(this, "没有快捷方式！", Toast.LENGTH_LONG).show();
        }
    }
}
