package com.zhicheng.bean.json;

import java.util.List;

/**
 * Created by Donson on 2017/2/20.
 */

public class FormNodeRequest {


    /**
     * iq : {"namespace":"FormNodeResponse","query":{"id":"36C9CF7D-390C-9F4F-BCA3-21F3EE377566","requestType":"0","chukouID":"1780","nodes":[],"errorCode":"0","errorMessage":""}}
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
         * query : {"id":"36C9CF7D-390C-9F4F-BCA3-21F3EE377566","requestType":"0","chukouID":"1780","nodes":[],"errorCode":"0","errorMessage":""}
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
             * requestType : 0
             * chukouID : 1780
             * nodes : []
             * errorCode : 0
             * errorMessage :
             */

            private String id;
            private String requestType;
            private String chukouID;
            private String errorCode;
            private String errorMessage;
            private List<?> nodes;

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

            public List<?> getNodes() {
                return nodes;
            }

            public void setNodes(List<?> nodes) {
                this.nodes = nodes;
            }
        }
    }
}
