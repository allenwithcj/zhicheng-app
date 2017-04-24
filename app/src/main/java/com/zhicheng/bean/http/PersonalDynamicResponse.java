package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by lwl on 2017/4/12.
 */

public class PersonalDynamicResponse {

    /**
     * iq : {"namespace":"PersonalDynamicResponse","query":{"preMsg":{"LOCATION":"中国湖北省武汉市洪山区软件园中路","IMG":[{"size":"85.59K","name":"1491447337741.jpg","id":"/v8AMwAwADIANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA4","type":"1"},{"size":"109.64K","name":"1491447337859.jpg","id":"/v8AMwAwADIANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA5","type":"1"},{"size":"100.91K","name":"1491447337946.jpg","id":"/v8AMwAwADIAOAAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAw","type":"1"},{"size":"41.57K","name":"1491447338000.jpg","id":"/v8AMwAwADIAOAAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAx","type":"1"},{"size":"58.39K","name":"1491447338032.jpg","id":"/v8AMwAwADIAOAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAy","type":"1"}],"DATETIME":"2017-04-06 10:55:48.0","USERID":"7541","COUNT":"来呀，快活呀","DEPT":"2547","ID":"1335c909-8911-4e29-9f93-363db073f0fb"},"errorCode":"0","errorMessage":"成功"}}
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
         * namespace : PersonalDynamicResponse
         * query : {"preMsg":{"LOCATION":"中国湖北省武汉市洪山区软件园中路","IMG":[{"size":"85.59K","name":"1491447337741.jpg","id":"/v8AMwAwADIANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA4","type":"1"},{"size":"109.64K","name":"1491447337859.jpg","id":"/v8AMwAwADIANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA5","type":"1"},{"size":"100.91K","name":"1491447337946.jpg","id":"/v8AMwAwADIAOAAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAw","type":"1"},{"size":"41.57K","name":"1491447338000.jpg","id":"/v8AMwAwADIAOAAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAx","type":"1"},{"size":"58.39K","name":"1491447338032.jpg","id":"/v8AMwAwADIAOAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAy","type":"1"}],"DATETIME":"2017-04-06 10:55:48.0","USERID":"7541","COUNT":"来呀，快活呀","DEPT":"2547","ID":"1335c909-8911-4e29-9f93-363db073f0fb"},"errorCode":"0","errorMessage":"成功"}
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
             * preMsg : {"LOCATION":"中国湖北省武汉市洪山区软件园中路","IMG":[{"size":"85.59K","name":"1491447337741.jpg","id":"/v8AMwAwADIANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA4","type":"1"},{"size":"109.64K","name":"1491447337859.jpg","id":"/v8AMwAwADIANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA5","type":"1"},{"size":"100.91K","name":"1491447337946.jpg","id":"/v8AMwAwADIAOAAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAw","type":"1"},{"size":"41.57K","name":"1491447338000.jpg","id":"/v8AMwAwADIAOAAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAx","type":"1"},{"size":"58.39K","name":"1491447338032.jpg","id":"/v8AMwAwADIAOAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAy","type":"1"}],"DATETIME":"2017-04-06 10:55:48.0","USERID":"7541","COUNT":"来呀，快活呀","DEPT":"2547","ID":"1335c909-8911-4e29-9f93-363db073f0fb"}
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
                 * LOCATION : 中国湖北省武汉市洪山区软件园中路
                 * IMG : [{"size":"85.59K","name":"1491447337741.jpg","id":"/v8AMwAwADIANwA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA4","type":"1"},{"size":"109.64K","name":"1491447337859.jpg","id":"/v8AMwAwADIANwA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA5","type":"1"},{"size":"100.91K","name":"1491447337946.jpg","id":"/v8AMwAwADIAOAAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAw","type":"1"},{"size":"41.57K","name":"1491447338000.jpg","id":"/v8AMwAwADIAOAAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAx","type":"1"},{"size":"58.39K","name":"1491447338032.jpg","id":"/v8AMwAwADIAOAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIAOAAy","type":"1"}]
                 * DATETIME : 2017-04-06 10:55:48.0
                 * USERID : 7541
                 * COUNT : 来呀，快活呀
                 * DEPT : 2547
                 * ID : 1335c909-8911-4e29-9f93-363db073f0fb
                 */

                private String LOCATION;
                private String DATETIME;
                private String USERID;
                private String COUNT;
                private String DEPT;
                private String ID;
                private List<IMGBean> IMG;

                public String getLOCATION() {
                    return LOCATION;
                }

                public void setLOCATION(String LOCATION) {
                    this.LOCATION = LOCATION;
                }

                public String getDATETIME() {
                    return DATETIME;
                }

                public void setDATETIME(String DATETIME) {
                    this.DATETIME = DATETIME;
                }

                public String getUSERID() {
                    return USERID;
                }

                public void setUSERID(String USERID) {
                    this.USERID = USERID;
                }

                public String getCOUNT() {
                    return COUNT;
                }

                public void setCOUNT(String COUNT) {
                    this.COUNT = COUNT;
                }

                public String getDEPT() {
                    return DEPT;
                }

                public void setDEPT(String DEPT) {
                    this.DEPT = DEPT;
                }

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public List<IMGBean> getIMG() {
                    return IMG;
                }

                public void setIMG(List<IMGBean> IMG) {
                    this.IMG = IMG;
                }

                public static class IMGBean {
                    /**
                     * size : 85.59K
                     * name : 1491447337741.jpg
                     * id : /v8AMwAwADIANwA4
                     * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMwAwADIANwA4
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
