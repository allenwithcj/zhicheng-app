package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/16.
 */

public class PersonalDynamicRequest {
    private IqBean iq;

    public IqBean getIq() {
        return iq;
    }

    public void setIq(IqBean iq) {
        this.iq = iq;
    }

    public static class IqBean {
        /**
         * query : {"orderBy":"","page":"1","perPageNums":"10","orderType":"","requestType":"0"}
         * model : 0
         * namespace : ListRequest
         */

        private QueryBean query;
        private String namespace;

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public static class QueryBean {
            private String type;
            private String id;
            private String cont;
            private String attguid;
            private String location;
            private int page;
            private int row;
            private String ID;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCont() {
                return cont;
            }

            public void setCont(String cont) {
                this.cont = cont;
            }

            public String getAttguid() {
                return attguid;
            }

            public void setAttguid(String attguid) {
                this.attguid = attguid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
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
