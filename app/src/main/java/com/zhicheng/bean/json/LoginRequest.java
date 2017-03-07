package com.zhicheng.bean.json;

import java.io.Serializable;

/**
 * Created by Donson on 2017/2/14.
 */

public class LoginRequest implements Serializable{

    /**
     * iq : {"namespace":"LoginRequest","model":3,"mobileVersion":"6.0.1","version":"1.4.0","resolution":"1080,1776","query":{"name":"liuting","password":"MTIz\n","token":"d07967277557452fa866fdbcf45afb4d","languageType":"0","deviceId":"02:00:00:00:00:00"}}
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
         * namespace : LoginRequest
         * model : 3
         * mobileVersion : 6.0.1
         * version : 1.4.0
         * resolution : 1080,1776
         * query : {"name":"liuting","password":"MTIz\n","token":"d07967277557452fa866fdbcf45afb4d","languageType":"0","deviceId":"02:00:00:00:00:00"}
         */

        private String namespace;
        private int model;
        private String mobileVersion;
        private String version;
        private String resolution;
        private QueryBean query;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public int getModel() {
            return model;
        }

        public void setModel(int model) {
            this.model = model;
        }

        public String getMobileVersion() {
            return mobileVersion;
        }

        public void setMobileVersion(String mobileVersion) {
            this.mobileVersion = mobileVersion;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public static class QueryBean {
            /**
             * name : liuting
             * password : MTIz

             * token : d07967277557452fa866fdbcf45afb4d
             * languageType : 0
             * deviceId : 02:00:00:00:00:00
             */

            private String name;
            private String password;
            private String token;
            private String languageType;
            private String deviceId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getLanguageType() {
                return languageType;
            }

            public void setLanguageType(String languageType) {
                this.languageType = languageType;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }
        }
    }
}
