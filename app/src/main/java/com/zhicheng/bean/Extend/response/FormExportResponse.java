package com.zhicheng.bean.Extend.response;

import java.util.List;

/**
 * Created by Donson on 2017/4/21.
 */

public class FormExportResponse {

    /**
     * iq : {"namespace":"FormExportResponse","query":{"id":"4DEBC22E-4A2C-B745-B62F-AF7DF15B2DDC","requestType":"3","type":"","tableName":"","tableId":"","items":[{"key":"1803","value":"提交街道审核"}],"errorCode":"0","errorMessage":""}}
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
         * namespace : FormExportResponse
         * query : {"id":"4DEBC22E-4A2C-B745-B62F-AF7DF15B2DDC","requestType":"3","type":"","tableName":"","tableId":"","items":[{"key":"1803","value":"提交街道审核"}],"errorCode":"0","errorMessage":""}
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
             * id : 4DEBC22E-4A2C-B745-B62F-AF7DF15B2DDC
             * requestType : 3
             * type :
             * tableName :
             * tableId :
             * items : [{"key":"1803","value":"提交街道审核"}]
             * errorCode : 0
             * errorMessage :
             */

            private String id;
            private String requestType;
            private String type;
            private String tableName;
            private String tableId;
            private String errorCode;
            private String errorMessage;
            private List<ItemsBean> items;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTableName() {
                return tableName;
            }

            public void setTableName(String tableName) {
                this.tableName = tableName;
            }

            public String getTableId() {
                return tableId;
            }

            public void setTableId(String tableId) {
                this.tableId = tableId;
            }

            public String getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(String errorCode) {
                this.errorCode = errorCode;
            }

            public String getErrorMessage() {
                return errorMessage;
            }

            public void setErrorMessage(String errorMessage) {
                this.errorMessage = errorMessage;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * key : 1803
                 * value : 提交街道审核
                 */

                private String key;
                private String value;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
