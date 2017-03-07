package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by IBM on 2017/3/2.
 */

public class NoticeResponse {
    /**
     * iq : {"namespace":"ListResponse","query":{"requestType":"6","totalNums":1,"table":{"name":"PUB_NOTICE","displayName":"公告表","tableSchema":[{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"content","displayName":"content","type":"0","primaryKey":"0","description":"content"},{"name":"badge","displayName":"readed","type":"0","primaryKey":"0","description":"readed"},{"name":"category","displayName":"category","type":"0","primaryKey":"0","description":"category"},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"}],"tableRows":[[{"name":"id","value":"118"},{"name":"content","value":"ces"},{"name":"badge","value":"1"},{"name":"category","value":"行政"},{"name":"title","value":"3ssdsadsa"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-03-01 18:38"}]]},"errorCode":"0","errorMessage":""}}
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
         * namespace : ListResponse
         * query : {"requestType":"6","totalNums":1,"table":{"name":"PUB_NOTICE","displayName":"公告表","tableSchema":[{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"content","displayName":"content","type":"0","primaryKey":"0","description":"content"},{"name":"badge","displayName":"readed","type":"0","primaryKey":"0","description":"readed"},{"name":"category","displayName":"category","type":"0","primaryKey":"0","description":"category"},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"}],"tableRows":[[{"name":"id","value":"118"},{"name":"content","value":"ces"},{"name":"badge","value":"1"},{"name":"category","value":"行政"},{"name":"title","value":"3ssdsadsa"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-03-01 18:38"}]]},"errorCode":"0","errorMessage":""}
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
             * requestType : 6
             * totalNums : 1
             * table : {"name":"PUB_NOTICE","displayName":"公告表","tableSchema":[{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"content","displayName":"content","type":"0","primaryKey":"0","description":"content"},{"name":"badge","displayName":"readed","type":"0","primaryKey":"0","description":"readed"},{"name":"category","displayName":"category","type":"0","primaryKey":"0","description":"category"},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"}],"tableRows":[[{"name":"id","value":"118"},{"name":"content","value":"ces"},{"name":"badge","value":"1"},{"name":"category","value":"行政"},{"name":"title","value":"3ssdsadsa"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-03-01 18:38"}]]}
             * errorCode : 0
             * errorMessage :
             */

            private String requestType;
            private int totalNums;
            private TableBean table;
            private String errorCode;
            private String errorMessage;

            public String getRequestType() {
                return requestType;
            }

            public void setRequestType(String requestType) {
                this.requestType = requestType;
            }

            public int getTotalNums() {
                return totalNums;
            }

            public void setTotalNums(int totalNums) {
                this.totalNums = totalNums;
            }

            public TableBean getTable() {
                return table;
            }

            public void setTable(TableBean table) {
                this.table = table;
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

            public static class TableBean {
                /**
                 * name : PUB_NOTICE
                 * displayName : 公告表
                 * tableSchema : [{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},
                 * {"name":"content","displayName":"content","type":"0","primaryKey":"0","description":"content"},
                 * {"name":"badge","displayName":"readed","type":"0","primaryKey":"0","description":"readed"},
                 * {"name":"category","displayName":"category","type":"0","primaryKey":"0","description":"category"},
                 * {"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},
                 * {"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},
                 * {"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"}]
                 * tableRows : [[
                 * {"name":"id","value":"118"}, 0
                 * {"name":"content","value":"ces"},1
                 * {"name":"badge","value":"1"},2
                 * {"name":"category","value":"行政"},3
                 * {"name":"title","value":"3ssdsadsa"},4
                 * {"name":"sendUser","value":"叶思阳"},5
                 * {"name":"sendTime","value":"2017-03-01 18:38"}]]6
                 */

                private String name;
                private String displayName;
                private List<TableSchemaBean> tableSchema;
                private List<List<TableRowsBean>> tableRows;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDisplayName() {
                    return displayName;
                }

                public void setDisplayName(String displayName) {
                    this.displayName = displayName;
                }

                public List<TableSchemaBean> getTableSchema() {
                    return tableSchema;
                }

                public void setTableSchema(List<TableSchemaBean> tableSchema) {
                    this.tableSchema = tableSchema;
                }

                public List<List<TableRowsBean>> getTableRows() {
                    return tableRows;
                }

                public void setTableRows(List<List<TableRowsBean>> tableRows) {
                    this.tableRows = tableRows;
                }

                public static class TableSchemaBean {
                    /**
                     * name : id
                     * displayName :
                     * type : 0
                     * primaryKey : 1
                     * description :
                     */

                    private String name;
                    private String displayName;
                    private String type;
                    private String primaryKey;
                    private String description;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getDisplayName() {
                        return displayName;
                    }

                    public void setDisplayName(String displayName) {
                        this.displayName = displayName;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getPrimaryKey() {
                        return primaryKey;
                    }

                    public void setPrimaryKey(String primaryKey) {
                        this.primaryKey = primaryKey;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }
                }

                public static class TableRowsBean {
                    /**
                     * name : id
                     * value : 118
                     */

                    private String name;
                    private String value;

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
                }
            }
        }
    }
}
