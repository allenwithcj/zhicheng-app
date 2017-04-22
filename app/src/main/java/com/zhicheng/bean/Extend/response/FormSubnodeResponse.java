package com.zhicheng.bean.Extend.response;

import java.util.List;

/**
 * Created by Donson on 2017/4/21.
 */

public class FormSubnodeResponse {

    /**
     * iq : {"namespace":"FormSubnodeResponse","query":{"id":"1643","type":"3","items":[{"key":"13095","value":"信息审核","description":"雉城街道/党政人大办"}],"errorCode":"0","errorMessage":""}}
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
         * namespace : FormSubnodeResponse
         * query : {"id":"1643","type":"3","items":[{"key":"13095","value":"信息审核","description":"雉城街道/党政人大办"}],"errorCode":"0","errorMessage":""}
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
             * id : 1643
             * type : 3
             * items : [{"key":"13095","value":"信息审核","description":"雉城街道/党政人大办"}]
             * errorCode : 0
             * errorMessage :
             */

            private String id;
            private String type;
            private String errorCode;
            private String errorMessage;
            private List<ItemsBean> items;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
                 * key : 13095
                 * value : 信息审核
                 * description : 雉城街道/党政人大办
                 */

                private String key;
                private String value;
                private String description;

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

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }
    }
}
