package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/2.
 */

public class PersonalLogMaRequest {

    /**
     * iq : {"query":{"orderBy":"","page":"1","perPageNums":"10","orderType":"","requestType":"0"},"model":"0","namespace":"ListRequest"}
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
         * query : {"orderBy":"","page":"1","perPageNums":"10","orderType":"","requestType":"0"}
         * model : 0
         * namespace : ListRequest
         */

        private QueryBean query;
        private String namespace;

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public static class QueryBean{
            private String type;
            private String con;
            private String id;
            private int page;
            private String attguid;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCon() {
                return con;
            }

            public void setCon(String con) {
                this.con = con;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public String getAttguid() {
                return attguid;
            }

            public void setAttguid(String attguid) {
                this.attguid = attguid;
            }
        }

    }
}
