package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/15.
 */

public class LocationUploadRequest {
    private IqBean iq;

    public IqBean getIq() {
        return iq;
    }

    public void setIq(IqBean iq) {
        this.iq = iq;
    }

    public static class IqBean {
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
            private String type;
            private String User;
            private String zlng;
            private String zlat;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUser() {
                return User;
            }

            public void setUser(String user) {
                User = user;
            }

            public String getZlng() {
                return zlng;
            }

            public void setZlng(String zlng) {
                this.zlng = zlng;
            }

            public String getZlat() {
                return zlat;
            }

            public void setZlat(String zlat) {
                this.zlat = zlat;
            }
        }
     }
    }
