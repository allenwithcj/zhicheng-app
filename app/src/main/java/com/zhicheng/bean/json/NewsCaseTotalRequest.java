package com.zhicheng.bean.json;

/**
 * Created by lwl on 2017/4/15.
 */

public class NewsCaseTotalRequest {
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

        }
    }
}
