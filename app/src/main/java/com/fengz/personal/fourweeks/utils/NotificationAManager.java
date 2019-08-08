package com.fengz.personal.fourweeks.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.fengz.personal.fourweeks.R;
import com.fengz.personal.fourweeks.base.MyApplication;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 创建时间：2019/8/8
 * 版   本：v1.0.0
 * 作   者：fengzhen
 * <p>
 * 功能描述：通知管理
 * 创建通知渠道方便 8.0以上用户针对性管理通知
 */
public class NotificationAManager {

    /**
     * 任务通知
     */
    private static final String CHANNEL_TASK_ID = "channel_task_id";
    private static final String CHANNEL_TASK_NAME = "任务通知";
    private final NotificationManager notificationMg;

    public static NotificationAManager get(){
        return InstanceHolder.instance;
    }

    private static class InstanceHolder{
        private static NotificationAManager instance = new NotificationAManager();
    }

    private NotificationAManager() {
        notificationMg = (NotificationManager) MyApplication.getContext().getSystemService(NOTIFICATION_SERVICE);
    }

    public void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 创建任务通知渠道
            createNotificationChannel(CHANNEL_TASK_ID, CHANNEL_TASK_NAME, NotificationManager.IMPORTANCE_HIGH);
        }
    }

    /**
     * 发送任务通知
     */
    public void sendTaskNotification(Context context,String title,String content){
        checkChannel(context);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_TASK_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_four)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_four))
                .setAutoCancel(true)
                .setNumber(1)
                .build();
        notificationMg.notify(1,notification);
    }

    /**
     * 检测渠道是否关闭
     */
    public void checkChannel(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationMg.getNotificationChannel(CHANNEL_TASK_ID);
            // 渠道关闭
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE,context.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                context.startActivity(intent);
                ToastUtils.show("请打开消息通知");
            }
        }
    }

    /**
     * 创建通知渠道 8.0及以上版本才有此api
     * @param channelId 渠道id
     * @param channelName 渠道name 展示在用户管理通知位置
     * @param importance 渠道的重要等级
     */
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        // 允许出现角标
        channel.setShowBadge(true);
        notificationMg.createNotificationChannel(channel);
    }
}
