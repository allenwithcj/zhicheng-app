package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/14.
 */

public class AddressBookRequest {

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
            /**
             * page : 1
             * perPageNums : 10
             * orderBy :
             * orderType :
             * searchKey :
             * parentItemType : 0
             * dataSourceType : 1
             * filterType : 0
             * parentItemID :
             * isCurrentDept : 0
             * isRequestAllData : 1
             */

            private int page;
            private int perPageNums;
            private String orderBy;
            private String orderType;
            private String searchKey;
            private int parentItemType;
            private int dataSourceType;
            private int filterType;
            private String parentItemID;
            private String isCurrentDept;
            private String isRequestAllData;


            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPerPageNums() {
                return perPageNums;
            }

            public void setPerPageNums(int perPageNums) {
                this.perPageNums = perPageNums;
            }

            public String getOrderBy() {
                return orderBy;
            }

            public void setOrderBy(String orderBy) {
                this.orderBy = orderBy;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(String searchKey) {
                this.searchKey = searchKey;
            }

            public int getParentItemType() {
                return parentItemType;
            }

            public void setParentItemType(int parentItemType) {
                this.parentItemType = parentItemType;
            }

            public int getDataSourceType() {
                return dataSourceType;
            }

            public void setDataSourceType(int dataSourceType) {
                this.dataSourceType = dataSourceType;
            }

            public int getFilterType() {
                return filterType;
            }

            public void setFilterType(int filterType) {
                this.filterType = filterType;
            }

            public String getParentItemID() {
                return parentItemID;
            }

            public void setParentItemID(String parentItemID) {
                this.parentItemID = parentItemID;
            }

            public String getIsCurrentDept() {
                return isCurrentDept;
            }

            public void setIsCurrentDept(String isCurrentDept) {
                this.isCurrentDept = isCurrentDept;
            }

            public String getIsRequestAllData() {
                return isRequestAllData;
            }

            public void setIsRequestAllData(String isRequestAllData) {
                this.isRequestAllData = isRequestAllData;
            }
        }
    }
}
