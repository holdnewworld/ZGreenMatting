package com.zgreenmatting.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

import com.zgreenmatting.BaseActivity;
import com.zgreenmatting.R;
import com.zgreenmatting.utils.PhoneUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void preInitView() {
        if (PermissionChecker.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CAMERA , Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            startActivity();
        }
    }

    @Override
    protected void preInitData() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e("main:", PhoneUtil.getDevicesID(mContext));
            Log.e("main:", PhoneUtil.getBrand());
            Log.e("main:", PhoneUtil.getSystemVersion());
            Log.e("main:", PhoneUtil.getVersionName(mContext));
            Log.e("main:", PhoneUtil.getVersionCode(mContext));
            startActivity();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void startActivity() {
        startActivity(new Intent(this, CameraActivity.class));
        finish();
    }
}
