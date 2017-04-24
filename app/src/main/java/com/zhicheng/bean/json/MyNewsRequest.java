package com.zhicheng.bean.json;

/**
 * Created by lwl on 2017/4/19.
 */

public class MyNewsRequest {
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
            private String id;
            private int dealType;
            private String suggestion;
            private int isTrace;
            private int isWait;
            private int isReturnCurrentNode;


            public int getRequestType() {
                return requestType;
            }

            public void setRequestType(int requestType) {
                this.requestType = requestType;
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
        }
    }
}
