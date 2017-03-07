package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialQueryDetailRequest {
    /**
     * iq : {"query":{"id":"57572","requestType":"1"},"model":"0","namespace":"CollaborationDetailsRequest"}
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
         * query : {"id":"57572","requestType":"1"}
         * model : 0
         * namespace : CollaborationDetailsRequest
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

        public static class QueryBean {
            /**
             * id : 57572
             * requestType : 1
             */

            private String type;
            private String ID;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }
        }
    }
}
