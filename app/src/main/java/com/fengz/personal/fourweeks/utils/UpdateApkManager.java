package com.fengz.personal.fourweeks.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.fengz.personal.fourweeks.business1.model.entity.CheckVersionBean;
import com.fengz.personal.fourweeks.business1.modules.PermissionProxyActivity.InstallPermissionActivity;
import com.fengz.personal.fourweeks.utils.LogUtils;
import com.fengz.personal.fourweeks.utils.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * 创建时间：2019/3/12
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：应用内升级Manager
 */
public class UpdateApkManager {

    private static final String TAG = "UpdateApkManager";
    // 升级包命名
    private static final String APK_NAME = "NewFeng.apk";

    private WeakReference<Activity> weakReference;
    private DownloadManager mDownloadManager;
    private DownloadChangeObserver mDownLoadChangeObserver;
    private DownloadReceiver mDownloadReceiver;
    private long mReqId;
    private OnUpdateListener mUpdateListener;

    public UpdateApkManager(Activity activity) {
        weakReference = new WeakReference<>(activity);
        mDownloadManager = (DownloadManager) weakReference.get().getSystemService(Context.DOWNLOAD_SERVICE);
        mDownLoadChangeObserver = new DownloadChangeObserver(new Handler());
        mDownloadReceiver = new DownloadReceiver();

        register();
    }

    public void setUpdateListener(OnUpdateListener mUpdateListener) {
        this.mUpdateListener = mUpdateListener;
    }

    public void downloadApk(CheckVersionBean updateInfo) {
        String appUrl = pretreatmentUrl(updateInfo.getUpUrl());
        DownloadManager.Request request;
        try {
            request = new DownloadManager.Request(Uri.parse(appUrl));
            request.setTitle("应用升级");
            request.setDescription("版本升级");
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, APK_NAME);
            mReqId = mDownloadManager.enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 预处理apkUrl，以及文件下载
     */
    private String pretreatmentUrl(String originalUrl) {
        if (originalUrl == null || originalUrl.isEmpty()) {
            LogUtils.error(TAG, "升级包地址为null");
            return null;
        }
        // 去掉首尾空格
        originalUrl = originalUrl.trim();
        if (!originalUrl.startsWith("http")) {
            // 添加Http信息
            LogUtils.error(TAG, "地址非http打头");
            originalUrl = "http://" + originalUrl;
        }
        // 获取系统Download文件夹绝对路径
        File directory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File apkFile = new File(directory, APK_NAME);
        if (apkFile.exists()) {
            // 删除之前的更新包
            apkFile.delete();
        }
        return originalUrl;
    }

    /**
     * 取消下载
     */
    public void cancel() {
        mDownloadManager.remove(mReqId);
    }

    /**
     * 注册广播
     */
    private void register() {
        //设置监听Uri.parse("content://downloads/my_downloads")
        weakReference.get().getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), true,
                mDownLoadChangeObserver);
        // 注册广播，监听APK是否下载完成
        weakReference.get().registerReceiver(mDownloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    /**
     * 资源回收
     */
    public void ondestroy() {
        weakReference.get().getContentResolver().unregisterContentObserver(mDownLoadChangeObserver);
        weakReference.get().unregisterReceiver(mDownloadReceiver);
    }

    /**
     * 更新下载进度
     */
    private void updateView() {
        int[] bytesAndStatus = new int[]{0, 0, 0};
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(mReqId);
        Cursor c = null;
        try {
            c = mDownloadManager.query(query);
            if (c != null && c.moveToFirst()) {
                //已经下载的字节数
                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //总需下载的字节数
                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //状态所在的列索引
                bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        if (mUpdateListener != null) {
            mUpdateListener.update(bytesAndStatus[0], bytesAndStatus[1]);
        }
        LogUtils.info(TAG, "升级包下载进度：" + bytesAndStatus[0] + "/" + bytesAndStatus[1]);
    }

    /**
     * 监听下载进度
     */
    class DownloadChangeObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            updateView();
        }
    }

    /**
     * 创建时间：2019/3/12
     * 版   本：v1.0.0
     * 作   者：fengzhen
     * <p>
     * 功能描述：接收安装广播
     */
    class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            // 兼容Android 8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //检测没有安装未知来源应用的权限
                if (!context.getPackageManager().canRequestPackageInstalls()) {
                    InstallPermissionActivity.requestInstallPermission(context, new InstallPermissionActivity.InstallPermissionListener() {
                        @Override
                        public void permissionGranted() {
                            installApk(context, intent);
                        }

                        @Override
                        public void permissionDenied() {
                            ToastUtils.show("未授权，无法安装应用");
                        }
                    });
                } else {
                    installApk(context, intent);
                }
            } else {
                installApk(context, intent);
            }

        }
    }

    /**
     * 安装apk
     */
    private void installApk(Context context, Intent intent) {
        long completeDownLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

        Uri uri;
        Intent intentInstall = new Intent();
        intentInstall.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentInstall.setAction(Intent.ACTION_VIEW);

        if (completeDownLoadId == mReqId) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // 6.0以下
                uri = mDownloadManager.getUriForDownloadedFile(completeDownLoadId);
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) { // 6.0 - 7.0
                File apkFile = queryDownloadedApk(context, completeDownLoadId);
                uri = Uri.fromFile(apkFile);
            } else { // Android 7.0 以上
                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                File apkFile = new File(directory, APK_NAME);
                if (!apkFile.exists()) {
                    ToastUtils.show("安装包被删除，请重新下载");
                }
                uri = FileProvider.getUriForFile(weakReference.get(),
                        "com.fengz.personal.fourweeks.fileprovider",
                        apkFile);
                intentInstall.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }

            // 安装应用
            intentInstall.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(intentInstall);
        }
    }

    /**
     * 通过downLoadId查询下载的apk，解决6.0以后安装的问题
     */
    private static File queryDownloadedApk(Context context, long downloadId) {
        File targetApkFile = null;
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }

    public interface OnUpdateListener {
        void update(int currentByte, int totalByte);
    }
}
