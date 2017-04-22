package com.zhicheng.bean.Extend.response;

import java.util.List;

/**
 * Created by Donson on 2017/4/21.
 */

public class FormNodeResponse {

    /**
     * iq : {"namespace":"FormNodeResponse","query":{"id":"4DEBC22E-4A2C-B745-B62F-AF7DF15B2DDC","requestType":"0","chukouID":"1803","nodes":[{"GUID":"839769A9-BB63-8A44-9A3D-1351A6D1C254","id":"1643","type":"0","name":"街道审核","figureID":"13095","figureName":"信息审核","figureType":"3","isSendPost":"0"}],"errorCode":"0","errorMessage":""}}
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
         * namespace : FormNodeResponse
         * query : {"id":"4DEBC22E-4A2C-B745-B62F-AF7DF15B2DDC","requestType":"0","chukouID":"1803","nodes":[{"GUID":"839769A9-BB63-8A44-9A3D-1351A6D1C254","id":"1643","type":"0","name":"街道审核","figureID":"13095","figureName":"信息审核","figureType":"3","isSendPost":"0"}],"errorCode":"0","errorMessage":""}
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
             * requestType : 0
             * chukouID : 1803
             * nodes : [{"GUID":"839769A9-BB63-8A44-9A3D-1351A6D1C254","id":"1643","type":"0","name":"街道审核","figureID":"13095","figureName":"信息审核","figureType":"3","isSendPost":"0"}]
             * errorCode : 0
             * errorMessage :
             */

            private String id;
            private String requestType;
            private String chukouID;
            private String errorCode;
            private String errorMessage;
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

            public void setRequestType(String requestType) {
                this.requestType = requestType;
            }

            public String getChukouID() {
                return chukouID;
            }

            public void setChukouID(String chukouID) {
                this.chukouID = chukouID;
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

            public List<NodesBean> getNodes() {
                return nodes;
            }

            public void setNodes(List<NodesBean> nodes) {
                this.nodes = nodes;
            }

            public static class NodesBean {
                /**
                 * GUID : 839769A9-BB63-8A44-9A3D-1351A6D1C254
                 * id : 1643
                 * type : 0
                 * name : 街道审核
                 * figureID : 13095
                 * figureName : 信息审核
                 * figureType : 3
                 * isSendPost : 0
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
