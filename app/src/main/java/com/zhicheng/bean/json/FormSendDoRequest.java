package com.zhicheng.bean.json;

import java.util.List;

/**
 * Created by Donson on 2017/2/20.
 */

public class FormSendDoRequest {

    /**
     * iq : {"namespace":"FormSendDoRequest","query":{"requestType":0,"term":"4","termUnit":"工作日","id":"227074","dealType":0,"type":1,"attGUID":"4520EB94-D872-0541-9E93-E76CBA19BD9E","suggestion":"经核实，情况属实。(来自Android)","isTrace":0,"isWait":0,"isReturnCurrentNode":0,"nodes":[{"GUID":"FFB022AD-19EF-8943-BDFC-FC56C3C8B80A","id":"1031","type":0,"name":"镇街受理员立案","value":"Y1916","figureID":"","figureName":"","figureType":"","isDefaultNode":false}]}}
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
         * namespace : FormSendDoRequest
         * query : {"requestType":0,"term":"4","termUnit":"工作日","id":"227074","dealType":0,"type":1,"attGUID":"4520EB94-D872-0541-9E93-E76CBA19BD9E","suggestion":"经核实，情况属实。(来自Android)","isTrace":0,"isWait":0,"isReturnCurrentNode":0,"nodes":[{"GUID":"FFB022AD-19EF-8943-BDFC-FC56C3C8B80A","id":"1031","type":0,"name":"镇街受理员立案","value":"Y1916","figureID":"","figureName":"","figureType":"","isDefaultNode":false}]}
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
             * requestType : 0
             * term : 4
             * termUnit : 工作日
             * id : 227074
             * dealType : 0
             * type : 1
             * attGUID : 4520EB94-D872-0541-9E93-E76CBA19BD9E
             * suggestion : 经核实，情况属实。(来自Android)
             * isTrace : 0
             * isWait : 0
             * isReturnCurrentNode : 0
             * nodes : [{"GUID":"FFB022AD-19EF-8943-BDFC-FC56C3C8B80A","id":"1031","type":0,"name":"镇街受理员立案","value":"Y1916","figureID":"","figureName":"","figureType":"","isDefaultNode":false}]
             */

            private int requestType;
            private String term;
            private String termUnit;
            private String id;
            private int dealType;
            private int type;
            private String attGUID;
            private String suggestion;
            private int isTrace;
            private int isWait;
            private int isReturnCurrentNode;
            private List<NodesBean> nodes;

            public int getRequestType() {
                return requestType;
            }

            public void setRequestType(int requestType) {
                this.requestType = requestType;
            }

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
            }

            public String getTermUnit() {
                return termUnit;
            }

            public void setTermUnit(String termUnit) {
                this.termUnit = termUnit;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getDealType() {
                return dealType;
            }

            public void setDealType(int dealType) {
                this.dealType = dealType;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAttGUID() {
                return attGUID;
            }

            public void setAttGUID(String attGUID) {
                this.attGUID = attGUID;
            }

            public String getSuggestion() {
                return suggestion;
            }

            public void setSuggestion(String suggestion) {
                this.suggestion = suggestion;
            }

            public int getIsTrace() {
                return isTrace;
            }

            public void setIsTrace(int isTrace) {
                this.isTrace = isTrace;
            }

            public int getIsWait() {
                return isWait;
            }

            public void setIsWait(int isWait) {
                this.isWait = isWait;
            }

            public int getIsReturnCurrentNode() {
                return isReturnCurrentNode;
            }

            public void setIsReturnCurrentNode(int isReturnCurrentNode) {
                this.isReturnCurrentNode = isReturnCurrentNode;
            }

            public List<NodesBean> getNodes() {
                return nodes;
            }

            public void setNodes(List<NodesBean> nodes) {
                this.nodes = nodes;
            }

            public static class NodesBean {
                /**
                 * GUID : FFB022AD-19EF-8943-BDFC-FC56C3C8B80A
                 * id : 1031
                 * type : 0
                 * name : 镇街受理员立案
                 * value : Y1916
                 * figureID :
                 * figureName :
                 * figureType :
                 * isDefaultNode : false
                 */

                private String GUID;
                private String id;
                private int type;
                private String name;
                private String value;
                private String figureID;
                private String figureName;
                private String figureType;
                private boolean isDefaultNode;
                private String isSendPost;

                public String getIsSendPost() {
                    return isSendPost;
                }

                public void setIsSendPost(String isSendPost) {
                    this.isSendPost = isSendPost;
                }

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

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
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

                public boolean isIsDefaultNode() {
                    return isDefaultNode;
                }

                public void setIsDefaultNode(boolean isDefaultNode) {
                    this.isDefaultNode = isDefaultNode;
                }
            }
        }
    }
}
