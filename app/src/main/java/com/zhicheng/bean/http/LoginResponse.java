package com.zhicheng.bean.http;

/**
 * Created by Donson on 2017/2/14.
 */

public class LoginResponse{

    /**
     * iq : {"namespace":"LoginResponse","query":{"userID":"7494","userName":"叶思阳","department":"雉城指挥中心","userPost":"镇街受理员","headUrl":"/UserUploadFile/photo/photo.png&isMobile=true","logoUrl":"","feVersion":"65","errorCode":"0","errorMessage":"登陆成功","accessToken":"5b7610a6e5475410f76ca3d6fa6181f2","imid":"","imtoken":""}}
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
         * query : {"userID":"7494","userName":"叶思阳","department":"雉城指挥中心","userPost":"镇街受理员","headUrl":"/UserUploadFile/photo/photo.png&isMobile=true","logoUrl":"","feVersion":"65","errorCode":"0","errorMessage":"登陆成功","accessToken":"5b7610a6e5475410f76ca3d6fa6181f2","imid":"","imtoken":""}
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
             * userID : 7494
             * userName : 叶思阳
             * department : 雉城指挥中心
             * userPost : 镇街受理员
             * headUrl : /UserUploadFile/photo/photo.png&isMobile=true
             * logoUrl :
             * feVersion : 65
             * errorCode : 0
             * errorMessage : 登陆成功
             * accessToken : 5b7610a6e5475410f76ca3d6fa6181f2
             * imid :
             * imtoken :
             */

            private String userID;
            private String userName;
            private String department;
            private String userPost;
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
