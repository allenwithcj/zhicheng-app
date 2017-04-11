package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/24.
 */

public class VersionResponse {


    /**
     * code : 0
     * message :
     * data : {"appKey":"7b149f5540320416194b20130b7c16f0","userKey":"d29e50b3572adc5662edfdc175022dc7","appType":"2","appIsLastest":"2","appFileSize":"22254834","appName":"智慧雉城","appVersion":"1.0","appVersionNo":"1","appBuildVersion":"1","appIdentifier":"com.zhicheng","appIcon":"2b807e36d347758237d78ce8dec95fde","appDescription":"","appUpdateDescription":"2017年3月10日：修改事项处理图片显示数量","appScreenshots":"","appShortcutUrl":"T0GL","appCreated":"2017-03-13 13:22:19","appUpdated":"2017-03-13 13:22:19","appQRCodeURL":"http://static.pgyer.com/app/qrcodeHistory/32f74e96b482355675efc5ae5b01992cf19016f27b87e1163bf21a0ae964b006","appFollowed":"0","otherapps":[{"appKey":"3df79811d76d118f9f1fd7c591e307c7","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"13","appIdentifier":"com.zhicheng","appCreated":"2天前","appUpdateDescription":"发布时间：2017-3-24 11:46:34\n发布功能：修复我的爆料事项详情处理流程红点显示"},{"appKey":"b920fb3f93f1eb2e8a81213dd6a1f0bf","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"12","appIdentifier":"com.zhicheng","appCreated":"2017-03-23","appUpdateDescription":"版本发布事件：2017-3-23 22:30:25\n更新内容：已办事项模块中事项详情处理流程时间显示格式修改为具体到秒；"},{"appKey":"c2b1933fe8e2da3a2c12523113f28bce","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"11","appIdentifier":"com.zhicheng","appCreated":"2017-03-23","appUpdateDescription":"发布时间：2017-3-23 18:02:53\n更新内容：\n1、网格基础数据新增户主字段；\n2、工作日志增加权限，领导可以查看员工的日志，员工只能查看自己的；\n3、修改事件详情流程图显示节点；"},{"appKey":"dc8daa447294d977bce7eefdcae7b501","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"10","appIdentifier":"com.zhicheng","appCreated":"2017-03-22","appUpdateDescription":"发布时间：2017-3-22 14:58:01\n发布功能：修改android手机4.0左右版本闪退的异常"}],"otherAppsCount":"12","comments":[],"commentsCount":"0"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appKey : 7b149f5540320416194b20130b7c16f0
         * userKey : d29e50b3572adc5662edfdc175022dc7
         * appType : 2
         * appIsLastest : 2
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
         * appFollowed : 0
         * otherapps : [{"appKey":"3df79811d76d118f9f1fd7c591e307c7","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"13","appIdentifier":"com.zhicheng","appCreated":"2天前","appUpdateDescription":"发布时间：2017-3-24 11:46:34\n发布功能：修复我的爆料事项详情处理流程红点显示"},{"appKey":"b920fb3f93f1eb2e8a81213dd6a1f0bf","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"12","appIdentifier":"com.zhicheng","appCreated":"2017-03-23","appUpdateDescription":"版本发布事件：2017-3-23 22:30:25\n更新内容：已办事项模块中事项详情处理流程时间显示格式修改为具体到秒；"},{"appKey":"c2b1933fe8e2da3a2c12523113f28bce","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"11","appIdentifier":"com.zhicheng","appCreated":"2017-03-23","appUpdateDescription":"发布时间：2017-3-23 18:02:53\n更新内容：\n1、网格基础数据新增户主字段；\n2、工作日志增加权限，领导可以查看员工的日志，员工只能查看自己的；\n3、修改事件详情流程图显示节点；"},{"appKey":"dc8daa447294d977bce7eefdcae7b501","userKey":"d29e50b3572adc5662edfdc175022dc7","appName":"智慧雉城","appVersion":"1.0","appBuildVersion":"10","appIdentifier":"com.zhicheng","appCreated":"2017-03-22","appUpdateDescription":"发布时间：2017-3-22 14:58:01\n发布功能：修改android手机4.0左右版本闪退的异常"}]
         * otherAppsCount : 12
         * comments : []
         * commentsCount : 0
         */

        private String appKey;
        private String userKey;
        private String appType;
        private String appIsLastest;
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
        private String appFollowed;
        private String otherAppsCount;
        private String commentsCount;
        private List<OtherappsBean> otherapps;
        private List<?> comments;

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
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

        public String getAppFollowed() {
            return appFollowed;
        }

        public void setAppFollowed(String appFollowed) {
            this.appFollowed = appFollowed;
        }

        public String getOtherAppsCount() {
            return otherAppsCount;
        }

        public void setOtherAppsCount(String otherAppsCount) {
            this.otherAppsCount = otherAppsCount;
        }

        public String getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(String commentsCount) {
            this.commentsCount = commentsCount;
        }

        public List<OtherappsBean> getOtherapps() {
            return otherapps;
        }

        public void setOtherapps(List<OtherappsBean> otherapps) {
            this.otherapps = otherapps;
        }

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public static class OtherappsBean {
            /**
             * appKey : 3df79811d76d118f9f1fd7c591e307c7
             * userKey : d29e50b3572adc5662edfdc175022dc7
             * appName : 智慧雉城
             * appVersion : 1.0
             * appBuildVersion : 13
             * appIdentifier : com.zhicheng
             * appCreated : 2天前
             * appUpdateDescription : 发布时间：2017-3-24 11:46:34
             * 发布功能：修复我的爆料事项详情处理流程红点显示
             */

            private String appKey;
            private String userKey;
            private String appName;
            private String appVersion;
            private String appBuildVersion;
            private String appIdentifier;
            private String appCreated;
            private String appUpdateDescription;

            public String getAppKey() {
                return appKey;
            }

            public void setAppKey(String appKey) {
                this.appKey = appKey;
            }

            public String getUserKey() {
                return userKey;
            }

            public void setUserKey(String userKey) {
                this.userKey = userKey;
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

            public String getAppCreated() {
                return appCreated;
            }

            public void setAppCreated(String appCreated) {
                this.appCreated = appCreated;
            }

            public String getAppUpdateDescription() {
                return appUpdateDescription;
            }

            public void setAppUpdateDescription(String appUpdateDescription) {
                this.appUpdateDescription = appUpdateDescription;
            }
        }
    }
}
