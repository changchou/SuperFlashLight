package com.jikexueyuan.superflashlight;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Mr.Z on 2016/2/25 0025.
 */
public class FlashLight extends BaseActivity {

    protected boolean lightOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lightOn = false;

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        ViewGroup.LayoutParams layoutParams = mImageViewFlashLightController.getLayoutParams();
        layoutParams.height = point.y * 3 / 4;
        layoutParams.width = point.x / 3;
        mImageViewFlashLightController.setLayoutParams(layoutParams);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeFlashLight();
    }

    public void onClick_flashlight(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            Toast.makeText(this, "当前设备没有闪光灯", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!lightOn) {
            openFlashLight();
        } else {
            closeFlashLight();
        }
    }

    //打开闪光灯
    protected void openFlashLight() {

        TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
        drawable.startTransition(200);
        lightOn = true;
        try {
            mCamera = Camera.open();
            int textureId = 0;
            mCamera.setPreviewTexture(new SurfaceTexture(textureId));
            mCamera.startPreview();

            mParameters = mCamera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(mParameters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭闪光灯
    protected void closeFlashLight() {
        TransitionDrawable drawable = (TransitionDrawable) mImageViewFlashLight.getDrawable();
        if (lightOn) {
            drawable.reverseTransition(200);
            lightOn = false;
            if (mCamera != null) {
                mParameters = mCamera.getParameters();
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(mParameters);
                mCamera.stopPreview();
                mCamera.release();
                mCamera = null;
            }
        }
    }

}
