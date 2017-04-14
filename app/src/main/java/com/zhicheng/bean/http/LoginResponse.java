package com.zhicheng.bean.http;

/**
 * Created by Donson on 2017/2/14.
 */

public class LoginResponse {


    /**
     * iq : {"namespace":"LoginResponse","query":{"userID":"7541","userName":"宋莹","department":"大西门社区第五网格","userPost":"网格员","departmentID":"2547","headUrl":"/UserUploadFile/photo/photo.png","logoUrl":"/login/applyTheme/images/portal/login/20170227174325.png","feVersion":"65","errorCode":"0","errorMessage":"登陆成功","accessToken":"3488a41fe076abfb1ff4d71e9a7d6148","imid":"","imtoken":""}}
     */

    private IqBean iq;

    public IqBean getIq() {
        return iq;
    }

    public void setIq(IqBean iq) {
        this.iq = iq;
    }

    public static class IqBean {
        /**
         * namespace : LoginResponse
         * query : {"userID":"7541","userName":"宋莹","department":"大西门社区第五网格","userPost":"网格员","departmentID":"2547","headUrl":"/UserUploadFile/photo/photo.png","logoUrl":"/login/applyTheme/images/portal/login/20170227174325.png","feVersion":"65","errorCode":"0","errorMessage":"登陆成功","accessToken":"3488a41fe076abfb1ff4d71e9a7d6148","imid":"","imtoken":""}
         */

        private String namespace;
        private QueryBean query;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public static class QueryBean {
            /**
             * userID : 7541
             * userName : 宋莹
             * department : 大西门社区第五网格
             * userPost : 网格员
             * departmentID : 2547
             * headUrl : /UserUploadFile/photo/photo.png
             * logoUrl : /login/applyTheme/images/portal/login/20170227174325.png
             * feVersion : 65
             * errorCode : 0
             * errorMessage : 登陆成功
             * accessToken : 3488a41fe076abfb1ff4d71e9a7d6148
             * imid :
             * imtoken :
             */

            private String userID;
            private String userName;
            private String department;
            private String userPost;
            private String departmentID;
            private String headUrl;
            private String logoUrl;
            private String feVersion;
            private String errorCode;
            private String errorMessage;
            private String accessToken;
            private String imid;
            private String imtoken;

            public String getUserID() {
                return userID;
            }

            public void setUserID(String userID) {
                this.userID = userID;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public String getUserPost() {
                return userPost;
            }

            public void setUserPost(String userPost) {
                this.userPost = userPost;
            }

            public String getDepartmentID() {
                return departmentID;
            }

            public void setDepartmentID(String departmentID) {
                this.departmentID = departmentID;
            }

            public String getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(String headUrl) {
                this.headUrl = headUrl;
            }

            public String getLogoUrl() {
                return logoUrl;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public String getFeVersion() {
                return feVersion;
            }

            public void setFeVersion(String feVersion) {
                this.feVersion = feVersion;
            }

            public String getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(String errorCode) {
                this.errorCode = errorCode;
            }

            public String getErrorMessage() {
                return errorMessage;
            }

            public void setErrorMessage(String errorMessage) {
                this.errorMessage = errorMessage;
            }

            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public String getImid() {
                return imid;
            }

            public void setImid(String imid) {
                this.imid = imid;
            }

            public String getImtoken() {
                return imtoken;
            }

            public void setImtoken(String imtoken) {
                this.imtoken = imtoken;
            }
        }
    }
}
