package com.zhicheng.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.Html;

import com.zhicheng.R;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.activity.OfficialBaseGrid;
import com.zhicheng.utils.common.NotificationUtils;

/**
 * Created by hp on 2017/3/7.
 */

public class LocationUpReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constant.ALARM_ACTION)) {
            OfficialBaseGrid.getInstance().openGps();
            if (!Constant.LATITUDE.equals("") && !Constant.LONGITUDE.equals("")) {
                StringBuffer sb = new StringBuffer();
                sb.append("经纬度定时上传中,不使用时记得关闭定位上传功能! ").append("\n");
                sb.append("经度:" + Constant.LATITUDE + " ").append("\n");
                sb.append("纬度:" + Constant.LONGITUDE);
                new NotificationUtils(context, 1).normal_notification(R.mipmap.ic_launcher, "定位上传中···", "定位上传",
                        Html.fromHtml(sb.toString()).toString());
                Intent mIntent = new Intent(context, LocationUpService.class);
                context.startService(mIntent);
            } else {
                new NotificationUtils(context, 1).normal_notification(R.mipmap.ic_launcher, "定位上传中···", "定位上传",
                        "获取经纬度失败,正努力定位获取位置中···");
            }
        }
    }
}
