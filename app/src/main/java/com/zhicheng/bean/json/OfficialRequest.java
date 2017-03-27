package com.zhicheng.bean.json;

/**
 * Created by Donson on 2017/2/15.
 */

public class OfficialRequest {

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
             * orderBy :
             * page : 1
             * perPageNums : 10
             * orderType :
             * requestType : 0
             */

            private String orderBy;
            private String page;
            private String perPageNums;
            private String orderType;
            private String requestType;
            private String status;
            private String isReadJian;

            public String getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(String orderBy) {
                this.orderBy = orderBy;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getPerPageNums() {
                return perPageNums;
            }

            public void setPerPageNums(String perPageNums) {
                this.perPageNums = perPageNums;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getRequestType() {
                return requestType;
            }

            public void setRequestType(String requestType) {
                this.requestType = requestType;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIsReadJian() {
                return isReadJian;
            }

            public void setIsReadJian(String isReadJian) {
                this.isReadJian = isReadJian;
            }
        }
    }
}
