package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/1/4.
 */

public class OfficialResponse {

    /**
     * iq : {"namespace":"ListResponse","query":{"requestType":"0","totalNums":18,"table":{"name":"VIEW_DAIBAN","displayName":"待办事项视图","tableSchema":[{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"},{"name":"remarks","displayName":"事件描述","type":"0","primaryKey":"0","description":"事件描述"}],"tableRows":[[{"name":"id","value":"202"},{"name":"title","value":"《案件办理-YPT20170223022》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"003"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"200"},{"name":"title","value":"《案件办理-YPT20170223021》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"002"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"198"},{"name":"title","value":"《案件办理-YPT20170223020》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"001"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"196"},{"name":"title","value":"《案件办理-YPT20170223019》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"21"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"186"},{"name":"title","value":"《案件办理-YPT20170223016》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"grrwrgewrgewgrewgr"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"184"},{"name":"title","value":"《案件办理-YPT20170223015》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"dvvwerewgew"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"182"},{"name":"title","value":"《案件办理-YPT20170223014》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"fqwefqwefqw"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"178"},{"name":"title","value":"《案件办理-YPT20170223012》"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"1"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"176"},{"name":"title","value":"《案件办理-YPT20170223011》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"ceshiceshiceshi"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"174"},{"name":"title","value":"《案件办理-YPT20170223010》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"cs1233133"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}]]},"errorCode":"0","errorMessage":""}}
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
         * query : {"requestType":"0","totalNums":18,"table":{"name":"VIEW_DAIBAN","displayName":"待办事项视图","tableSchema":[{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"},{"name":"remarks","displayName":"事件描述","type":"0","primaryKey":"0","description":"事件描述"}],"tableRows":[[{"name":"id","value":"202"},{"name":"title","value":"《案件办理-YPT20170223022》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"003"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"200"},{"name":"title","value":"《案件办理-YPT20170223021》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"002"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"198"},{"name":"title","value":"《案件办理-YPT20170223020》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"001"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"196"},{"name":"title","value":"《案件办理-YPT20170223019》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"21"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"186"},{"name":"title","value":"《案件办理-YPT20170223016》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"grrwrgewrgewgrewgr"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"184"},{"name":"title","value":"《案件办理-YPT20170223015》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"dvvwerewgew"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"182"},{"name":"title","value":"《案件办理-YPT20170223014》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"fqwefqwefqw"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"178"},{"name":"title","value":"《案件办理-YPT20170223012》"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"1"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"176"},{"name":"title","value":"《案件办理-YPT20170223011》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"ceshiceshiceshi"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"174"},{"name":"title","value":"《案件办理-YPT20170223010》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"cs1233133"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}]]},"errorCode":"0","errorMessage":""}
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
             * totalNums : 18
             * table : {"name":"VIEW_DAIBAN","displayName":"待办事项视图","tableSchema":[{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"},{"name":"remarks","displayName":"事件描述","type":"0","primaryKey":"0","description":"事件描述"}],"tableRows":[[{"name":"id","value":"202"},{"name":"title","value":"《案件办理-YPT20170223022》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"003"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"200"},{"name":"title","value":"《案件办理-YPT20170223021》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"002"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"198"},{"name":"title","value":"《案件办理-YPT20170223020》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"001"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"196"},{"name":"title","value":"《案件办理-YPT20170223019》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"21"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"186"},{"name":"title","value":"《案件办理-YPT20170223016》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"grrwrgewrgewgrewgr"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"184"},{"name":"title","value":"《案件办理-YPT20170223015》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"dvvwerewgew"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"182"},{"name":"title","value":"《案件办理-YPT20170223014》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"fqwefqwefqw"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"178"},{"name":"title","value":"《案件办理-YPT20170223012》"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"1"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"176"},{"name":"title","value":"《案件办理-YPT20170223011》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"ceshiceshiceshi"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"174"},{"name":"title","value":"《案件办理-YPT20170223010》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"cs1233133"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}]]}
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
                 * name : VIEW_DAIBAN
                 * displayName : 待办事项视图
                 * tableSchema : [{"name":"id","displayName":"","type":"0","primaryKey":"1","description":""},{"name":"title","displayName":"标题","type":"0","primaryKey":"0","description":"标题"},{"name":"sendUser","displayName":"送办人","type":"0","primaryKey":"0","description":"送办人"},{"name":"sendTime","displayName":"发起时间","type":"0","primaryKey":"0","description":"发起时间"},{"name":"remarks","displayName":"事件描述","type":"0","primaryKey":"0","description":"事件描述"}]
                 * tableRows : [[{"name":"id","value":"202"},{"name":"title","value":"《案件办理-YPT20170223022》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"003"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"200"},{"name":"title","value":"《案件办理-YPT20170223021》"},{"name":"sendUser","value":"卢子谦"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"002"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"198"},{"name":"title","value":"《案件办理-YPT20170223020》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"001"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"196"},{"name":"title","value":"《案件办理-YPT20170223019》"},{"name":"sendUser","value":"戴康传"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"21"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"186"},{"name":"title","value":"《案件办理-YPT20170223016》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"grrwrgewrgewgrewgr"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"184"},{"name":"title","value":"《案件办理-YPT20170223015》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"dvvwerewgew"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"182"},{"name":"title","value":"《案件办理-YPT20170223014》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"fqwefqwefqw"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"178"},{"name":"title","value":"《案件办理-YPT20170223012》"},{"name":"sendUser","value":"叶思阳"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"1"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"176"},{"name":"title","value":"《案件办理-YPT20170223011》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"ceshiceshiceshi"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}],[{"name":"id","value":"174"},{"name":"title","value":"《案件办理-YPT20170223010》"},{"name":"sendUser","value":"曹文伟"},{"name":"sendTime","value":"2017-02-23"},{"name":"remarks","value":"cs1233133"},{"name":"sendUserImg","value":"/UserUploadFile/photo/photo.png"}]]
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
                     * value : 202
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
