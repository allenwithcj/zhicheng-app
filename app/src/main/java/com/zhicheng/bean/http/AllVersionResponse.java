package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/24.
 */

public class AllVersionResponse {


    /**
     * code : 0
     * message :
     * data : [{"appKey":"7b149f5540320416194b20130b7c16f0","appType":"2","appIsLastest":"2","appFileName":"app-debug-zhicheng-310.apk","appFileSize":"22254834","appName":"智慧雉城","appVersion":"1.0","appVersionNo":"1","appBuildVersion":"1","appIdentifier":"com.zhicheng","appIcon":"2b807e36d347758237d78ce8dec95fde","appDescription":"","appUpdateDescription":"2017年3月10日：修改事项处理图片显示数量","appScreenshots":"","appShortcutUrl":"T0GL","appCreated":"2017-03-13 13:22:19","appUpdated":"2017-03-13 13:22:19","appQRCodeURL":"http://static.pgyer.com/app/qrcodeHistory/32f74e96b482355675efc5ae5b01992cf19016f27b87e1163bf21a0ae964b006"}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appKey : 7b149f5540320416194b20130b7c16f0
         * appType : 2
         * appIsLastest : 2
         * appFileName : app-debug-zhicheng-310.apk
         * appFileSize : 22254834
         * appName : 智慧雉城
         * appVersion : 1.0
         * appVersionNo : 1
         * appBuildVersion : 1
         * appIdentifier : com.zhicheng
         * appIcon : 2b807e36d347758237d78ce8dec95fde
         * appDescription :
         * appUpdateDescription : 2017年3月10日：修改事项处理图片显示数量
         * appScreenshots :
         * appShortcutUrl : T0GL
         * appCreated : 2017-03-13 13:22:19
         * appUpdated : 2017-03-13 13:22:19
         * appQRCodeURL : http://static.pgyer.com/app/qrcodeHistory/32f74e96b482355675efc5ae5b01992cf19016f27b87e1163bf21a0ae964b006
         */

        private String appKey;
        private String appType;
        private String appIsLastest;
        private String appFileName;
        private String appFileSize;
        private String appName;
        private String appVersion;
        private String appVersionNo;
        private String appBuildVersion;
        private String appIdentifier;
        private String appIcon;
        private String appDescription;
        private String appUpdateDescription;
        private String appScreenshots;
        private String appShortcutUrl;
        private String appCreated;
        private String appUpdated;
        private String appQRCodeURL;

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getAppType() {
            return appType;
        }

        public void setAppType(String appType) {
            this.appType = appType;
        }

        public String getAppIsLastest() {
            return appIsLastest;
        }

        public void setAppIsLastest(String appIsLastest) {
            this.appIsLastest = appIsLastest;
        }

        public String getAppFileName() {
            return appFileName;
        }

        public void setAppFileName(String appFileName) {
            this.appFileName = appFileName;
        }

        public String getAppFileSize() {
            return appFileSize;
        }

        public void setAppFileSize(String appFileSize) {
            this.appFileSize = appFileSize;
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getAppVersionNo() {
            return appVersionNo;
        }

        public void setAppVersionNo(String appVersionNo) {
            this.appVersionNo = appVersionNo;
        }

        public String getAppBuildVersion() {
            return appBuildVersion;
        }

        public void setAppBuildVersion(String appBuildVersion) {
            this.appBuildVersion = appBuildVersion;
        }

        public String getAppIdentifier() {
            return appIdentifier;
        }

        public void setAppIdentifier(String appIdentifier) {
            this.appIdentifier = appIdentifier;
        }

        public String getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(String appIcon) {
            this.appIcon = appIcon;
        }

        public String getAppDescription() {
            return appDescription;
        }

        public void setAppDescription(String appDescription) {
            this.appDescription = appDescription;
        }

        public String getAppUpdateDescription() {
            return appUpdateDescription;
        }

        public void setAppUpdateDescription(String appUpdateDescription) {
            this.appUpdateDescription = appUpdateDescription;
        }

        public String getAppScreenshots() {
            return appScreenshots;
        }

        public void setAppScreenshots(String appScreenshots) {
            this.appScreenshots = appScreenshots;
        }

        public String getAppShortcutUrl() {
            return appShortcutUrl;
        }

        public void setAppShortcutUrl(String appShortcutUrl) {
            this.appShortcutUrl = appShortcutUrl;
        }

        public String getAppCreated() {
            return appCreated;
        }

        public void setAppCreated(String appCreated) {
            this.appCreated = appCreated;
        }

        public String getAppUpdated() {
            return appUpdated;
        }

        public void setAppUpdated(String appUpdated) {
            this.appUpdated = appUpdated;
        }

        public String getAppQRCodeURL() {
            return appQRCodeURL;
        }

        public void setAppQRCodeURL(String appQRCodeURL) {
            this.appQRCodeURL = appQRCodeURL;
        }
    }
}
