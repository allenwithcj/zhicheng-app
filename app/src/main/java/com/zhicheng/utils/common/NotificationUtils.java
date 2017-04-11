package com.zhicheng.utils.common;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

/**
 * Created by hp on 2017/3/8.
 */

public class NotificationUtils {

    private static int NOTIFICATION_ID;
    private NotificationManager nm;
    private Notification notification;
    private NotificationCompat.Builder cBuilder;
    private Notification.Builder nBuilder;
    private Context mContext;
    int requestCode = (int) SystemClock.uptimeMillis();

    public NotificationUtils(Context context, int ID) {
        this.NOTIFICATION_ID = ID;
        mContext = context;
        // 获取系统服务来初始化对象
        nm = (NotificationManager) mContext.getSystemService(Activity.NOTIFICATION_SERVICE);
        cBuilder = new NotificationCompat.Builder(mContext);
    }

    /**
     * 设置在顶部通知栏中的各种信息
     *
     * @param smallIcon
     * @param ticker
     */
    private void setCompatBuilder(int smallIcon, String ticker,
                                  String title, String msg) {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        cBuilder.setSmallIcon(smallIcon);// 设置顶部状态栏的小图标
        cBuilder.setTicker(ticker);// 在顶部状态栏中的提示信息
        bigTextStyle.setBigContentTitle(title);// 设置通知中心的标题
        bigTextStyle.bigText(msg);// 设置通知中心中的内容
        cBuilder.setStyle(bigTextStyle);
        cBuilder.setWhen(System.currentTimeMillis());

        /*
         * 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失,
         * 不设置的话点击消息后也不清除，但可以滑动删除
         */
        cBuilder.setAutoCancel(false);
        // 将Ongoing设为true 那么notification将不能滑动删除
        cBuilder.setOngoing(true);
        /*
         * 从Android4.1开始，可以通过以下方法，设置notification的优先级，
         * 优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
         */
        cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        /*
         * Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
         * Notification.DEFAULT_SOUND：系统默认铃声。
         * Notification.DEFAULT_VIBRATE：系统默认震动。
         * Notification.DEFAULT_LIGHTS：系统默认闪光。
         * notifyBuilder.setDefaults(Notification.DEFAULT_ALL);
         */
        cBuilder.setDefaults(Notification.DEFAULT_ALL);
    }


    /**
     * 普通的通知
     *
     * @param smallIcon
     * @param ticker
     * @param title
     * @param msg
     */
    public void normal_notification(int smallIcon,
                                    String ticker, String title, String msg) {
        setCompatBuilder(smallIcon, ticker, title, msg);
        sent();
    }


    /**
     * 发送通知
     */
    private void sent() {
        notification = cBuilder.build();
        // 发送该通知
        nm.notify(NOTIFICATION_ID, notification);
    }

    /**
     * 根据id清除通知
     */
    public void clear() {
        // 取消通知
        nm.cancelAll();

    }
}
