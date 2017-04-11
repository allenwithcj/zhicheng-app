package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/17.
 */

public class NewsDetailsRequest {

    /**
     * iq : {"namespace":"AddressBookRequest","query":{"page":"1","perPageNums":"10","orderBy":"","orderType":"","searchKey":"","parentItemType":0,"dataSourceType":1,"filterType":0,"parentItemID":"","isCurrentDept":"0","isRequestAllData":"1"}}
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
         * namespace : AddressBookRequest
         * query : {"page":"1","perPageNums":"10","orderBy":"","orderType":"","searchKey":"","parentItemType":0,"dataSourceType":1,"filterType":0,"parentItemID":"","isCurrentDept":"0","isRequestAllData":"1"}
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
            private String id;
            private String msgId;
            private String requestType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMsgId() {
                return msgId;
            }

            public void setMsgId(String msgId) {
                this.msgId = msgId;
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
