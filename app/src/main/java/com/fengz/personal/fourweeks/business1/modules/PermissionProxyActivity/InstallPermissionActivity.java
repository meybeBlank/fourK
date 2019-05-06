package com.fengz.personal.fourweeks.business1.modules.PermissionProxyActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.annotations.NonNull;

/**
 * 创建时间：2019/3/12
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：安装权限代理Activity
 */
public class InstallPermissionActivity extends AppCompatActivity {

    public static final int REQUEST_INSTALL_PACKAGES = 0x123;
    static InstallPermissionListener mListener;
    private AlertDialog mAlertDialog;

    /**
     * 请求应用内安装权限
     *
     * @param listener 监听权限
     */
    public static void requestInstallPermission(Context context, InstallPermissionListener listener) {
        mListener = listener;
        Intent intent = new Intent(context, InstallPermissionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 8.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, REQUEST_INSTALL_PACKAGES);
        } else {
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_INSTALL_PACKAGES:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mListener != null) {
                        mListener.permissionGranted();
                        finish();
                    }
                } else {
                    showTipDialog();
                }
                break;

        }
    }

    private void showTipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("应用内安装权限");
        builder.setMessage("为了正常进行应用内安装，请点击设置按钮，允许安装未知来源应用");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startInstallPermissionSettingActivity();
                mAlertDialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mListener != null) {
                    mListener.permissionDenied();
                }
                mAlertDialog.dismiss();
                finish();
            }
        });
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // 授权成功
            if (mListener != null) mListener.permissionGranted();
        } else {
            // 授权失败
            if (mListener != null) mListener.permissionDenied();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListener = null;
    }

    /**
     * 权限监听
     */
    public interface InstallPermissionListener {

        /**
         * 授权
         */
        void permissionGranted();

        /**
         * 拒绝权限
         */
        void permissionDenied();
    }
}
