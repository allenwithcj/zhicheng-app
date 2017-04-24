package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by lwl on 2017/4/14.
 */

public class ExperienceDetailResponse {


    /**
     * iq : {"namespace":"ExperienceResponse","query":{"preMsg":{"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","DEPARTMENT":"高阳桥社区第一网格","ID":"47","CONTENT":"4435435453535435","ANNEX":[{"size":"110.41K","name":"不能浪费.jpg","id":"/v8AMwAwADMANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA4","type":"1"},{"size":"155.40K","name":"流动书屋.jpg","id":"/v8AMwAwADMANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA5","type":"1"}],"POSTED_BY":"吴全超"},"errorCode":"0","errorMessage":"成功"}}
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
         * namespace : ExperienceResponse
         * query : {"preMsg":{"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","DEPARTMENT":"高阳桥社区第一网格","ID":"47","CONTENT":"4435435453535435","ANNEX":[{"size":"110.41K","name":"不能浪费.jpg","id":"/v8AMwAwADMANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA4","type":"1"},{"size":"155.40K","name":"流动书屋.jpg","id":"/v8AMwAwADMANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA5","type":"1"}],"POSTED_BY":"吴全超"},"errorCode":"0","errorMessage":"成功"}
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
             * preMsg : {"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","DEPARTMENT":"高阳桥社区第一网格","ID":"47","CONTENT":"4435435453535435","ANNEX":[{"size":"110.41K","name":"不能浪费.jpg","id":"/v8AMwAwADMANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA4","type":"1"},{"size":"155.40K","name":"流动书屋.jpg","id":"/v8AMwAwADMANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA5","type":"1"}],"POSTED_BY":"吴全超"}
             * errorCode : 0
             * errorMessage : 成功
             */

            private PreMsgBean preMsg;
            private String errorCode;
            private String errorMessage;

            public PreMsgBean getPreMsg() {
                return preMsg;
            }

            public void setPreMsg(PreMsgBean preMsg) {
                this.preMsg = preMsg;
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

            public static class PreMsgBean {
                /**
                 * POSTED_TIME : 2017-04-13 17:45:35.0
                 * TITLE_NAME : 45353454353
                 * DEPARTMENT : 高阳桥社区第一网格
                 * ID : 47
                 * CONTENT : 4435435453535435
                 * ANNEX : [{"size":"110.41K","name":"不能浪费.jpg","id":"/v8AMwAwADMANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA4","type":"1"},{"size":"155.40K","name":"流动书屋.jpg","id":"/v8AMwAwADMANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA5","type":"1"}]
                 * POSTED_BY : 吴全超
                 */

                private String POSTED_TIME;
                private String TITLE_NAME;
                private String DEPARTMENT;
                private String ID;
                private String CONTENT;
                private String POSTED_BY;
                private List<ANNEXBean> ANNEX;

                public String getPOSTED_TIME() {
                    return POSTED_TIME;
                }

                public void setPOSTED_TIME(String POSTED_TIME) {
                    this.POSTED_TIME = POSTED_TIME;
                }

                public String getTITLE_NAME() {
                    return TITLE_NAME;
                }

                public void setTITLE_NAME(String TITLE_NAME) {
                    this.TITLE_NAME = TITLE_NAME;
                }

                public String getDEPARTMENT() {
                    return DEPARTMENT;
                }

                public void setDEPARTMENT(String DEPARTMENT) {
                    this.DEPARTMENT = DEPARTMENT;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public String getCONTENT() {
                    return CONTENT;
                }

                public void setCONTENT(String CONTENT) {
                    this.CONTENT = CONTENT;
                }

                public String getPOSTED_BY() {
                    return POSTED_BY;
                }

                public void setPOSTED_BY(String POSTED_BY) {
                    this.POSTED_BY = POSTED_BY;
                }

                public List<ANNEXBean> getANNEX() {
                    return ANNEX;
                }

                public void setANNEX(List<ANNEXBean> ANNEX) {
                    this.ANNEX = ANNEX;
                }

                public static class ANNEXBean {
                    /**
                     * size : 110.41K
                     * name : 不能浪费.jpg
                     * id : /v8AMwAwADMANwA4
                     * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADMANwA4
                     * type : 1
                     */

                    private String size;
                    private String name;
                    private String id;
                    private String href;
                    private String type;

                    public String getSize() {
                        return size;
                    }

                    public void setSize(String size) {
                        this.size = size;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }
                }
            }
        }
    }
}
