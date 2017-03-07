package com.zhicheng.bean.json;

/**
 * Created by Donson on 2017/2/17.
 */

public class OfficialDetailRequest {

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
        private String model;
        private String namespace;

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
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

            private String id;
            private String requestType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRequestType() {
                return requestType;
            }

            public void setRequestType(String requestType) {
                this.requestType = requestType;
            }
        }
    }
}
