package com.zhicheng.bean.json;

/**
 * Created by Donson on 2017/2/20.
 */

public class FormExportRequest {

    /**
     * iq : {"namespace":"FormExportRequest","query":{"requestType":3,"id":"AA4F2310-C7CF-6346-99BB-ED88CD3AF3EF"}}
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
         * namespace : FormExportRequest
         * query : {"requestType":3,"id":"AA4F2310-C7CF-6346-99BB-ED88CD3AF3EF"}
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
             * requestType : 3
             * id : AA4F2310-C7CF-6346-99BB-ED88CD3AF3EF
             */

            private int requestType;
            private String id;

            public int getRequestType() {
                return requestType;
            }

            public void setRequestType(int requestType) {
                this.requestType = requestType;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
