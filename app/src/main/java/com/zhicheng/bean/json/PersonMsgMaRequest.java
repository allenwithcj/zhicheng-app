package com.zhicheng.bean.json;

/**
 * Created by lwl on 2017/4/16.
 */

public class PersonMsgMaRequest {

    /**
     * iq : {"namespace":"PersonMsgMaRequest","query":{"row":"10","type":"6","page":"0"}}
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
         * namespace : PersonMsgMaRequest
         * query : {"row":"10","type":"6","page":"0"}
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
             * row : 10
             * type : 6
             * page : 0
             */

            private String row;
            private String type;
            private String page;
            private String ID;

            public String getRow() {
                return row;
            }

            public void setRow(String row) {
                this.row = row;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

        }
    }
}
