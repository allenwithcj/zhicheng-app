package com.zhicheng.bean.json;

/**
 * Created by Donson on 2017/2/22.
 */

public class CaseItemRequest {

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

            private String parentNodeId;
            private String searchKey;

            public String getParentNodeId() {
                return parentNodeId;
            }

            public void setParentNodeId(String parentNodeId) {
                this.parentNodeId = parentNodeId;
            }

            public String getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(String searchKey) {
                this.searchKey = searchKey;
            }
        }
    }
}
