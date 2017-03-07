package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/2/20.
 */

public class IneedResponse {

    /**
     * iq : {"namespace":"FormExportResponse","query":{"id":"36C9CF7D-390C-9F4F-BCA3-21F3EE377566","requestType":"3","type":"","tableName":"","tableId":"","items":[{"key":"1780","value":"事件上报受理员"}],"errorCode":"0","errorMessage":""}}
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
         * query : {"id":"36C9CF7D-390C-9F4F-BCA3-21F3EE377566","requestType":"3","type":"","tableName":"","tableId":"","items":[{"key":"1780","value":"事件上报受理员"}],"errorCode":"0","errorMessage":""}
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
             * id : 36C9CF7D-390C-9F4F-BCA3-21F3EE377566
             * requestType : 3
             * type :
             * tableName :
             * tableId :
             * items : [{"key":"1780","value":"事件上报受理员"}]
             * errorCode : 0
             * errorMessage :
             */

            private String id;
            private String requestType;
            private String chukouID;
            private String type;
            private String tableName;
            private String tableId;
            private String errorCode;
            private String errorMessage;
            private List<ItemsBean> items;
            private List<NodesBean> nodes;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRequestType() {
                return requestType;
            }

            public String getChukouID() {
                return chukouID;
            }

            public void setChukouID(String chukouID) {
                this.chukouID = chukouID;
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

            public List<NodesBean> getNodes() {
                return nodes;
            }

            public void setNodes(List<NodesBean> nodes) {
                this.nodes = nodes;
            }


            public static class ItemsBean {
                /**
                 * key : 1780
                 * value : 事件上报受理员
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

            public static class NodesBean {
                /**
                 * GUID : 0C825E39-5D15-9340-89DF-E39E12820907
                 * id : 1631
                 * type : 0
                 * name : 镇街受理员立案
                 * figureID : 12551
                 * figureName : 镇街受理员
                 * figureType : 3
                 * isSendPost : 1
                 */

                private String GUID;
                private String id;
                private String type;
                private String name;
                private String figureID;
                private String figureName;
                private String figureType;
                private String isSendPost;

                public String getGUID() {
                    return GUID;
                }

                public void setGUID(String GUID) {
                    this.GUID = GUID;
                }

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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getFigureID() {
                    return figureID;
                }

                public void setFigureID(String figureID) {
                    this.figureID = figureID;
                }

                public String getFigureName() {
                    return figureName;
                }

                public void setFigureName(String figureName) {
                    this.figureName = figureName;
                }

                public String getFigureType() {
                    return figureType;
                }

                public void setFigureType(String figureType) {
                    this.figureType = figureType;
                }

                public String getIsSendPost() {
                    return isSendPost;
                }

                public void setIsSendPost(String isSendPost) {
                    this.isSendPost = isSendPost;
                }
            }
        }
    }
}
