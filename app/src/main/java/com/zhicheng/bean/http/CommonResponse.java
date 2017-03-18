package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/2/16.
 */

public class CommonResponse {

    /**
     * iq : {"query":{"data":{"caseReportTotal":3,"gridReportTotal":0,"cases":[{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANwA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA2","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANwA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA3","type":"1"}],"itemcon":"grrwrgewrgewgrewgr","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANAA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANAA5","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANQAw","type":"1"}],"itemcon":"fqwefqwefqwefq","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkAMQAw","type":"1"}],"itemcon":"了解","itemaddress":"fdasdfqwef","itemtime":""}],"weekDoneTotal":1,"daiBanTotal":0},"errorMessage":"成功","errorCode":0},"namespace":"NewsCaseTotalResponse"}
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
         * query : {"data":{"caseReportTotal":3,"gridReportTotal":0,"cases":[{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANwA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA2","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANwA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA3","type":"1"}],"itemcon":"grrwrgewrgewgrewgr","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANAA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANAA5","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANQAw","type":"1"}],"itemcon":"fqwefqwefqwefq","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkAMQAw","type":"1"}],"itemcon":"了解","itemaddress":"fdasdfqwef","itemtime":""}],"weekDoneTotal":1,"daiBanTotal":0},"errorMessage":"成功","errorCode":0}
         * namespace : NewsCaseTotalResponse
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
            /**
             * data : {"caseReportTotal":3,"gridReportTotal":0,"cases":[{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANwA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA2","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANwA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA3","type":"1"}],"itemcon":"grrwrgewrgewgrewgr","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANAA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANAA5","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANQAw","type":"1"}],"itemcon":"fqwefqwefqwefq","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkAMQAw","type":"1"}],"itemcon":"了解","itemaddress":"fdasdfqwef","itemtime":""}],"weekDoneTotal":1,"daiBanTotal":0}
             * errorMessage : 成功
             * errorCode : 0
             */

            private DataBean data;
            private String errorMessage;
            private int errorCode;

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getErrorMessage() {
                return errorMessage;
            }

            public void setErrorMessage(String errorMessage) {
                this.errorMessage = errorMessage;
            }

            public int getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(int errorCode) {
                this.errorCode = errorCode;
            }

            public static class DataBean {
                /**
                 * caseReportTotal : 3
                 * gridReportTotal : 0
                 * cases : [{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANwA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA2","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANwA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA3","type":"1"}],"itemcon":"grrwrgewrgewgrewgr","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANAA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANAA5","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANQAw","type":"1"}],"itemcon":"fqwefqwefqwefq","itemaddress":"","itemtime":""},{"attachments":[{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkAMQAw","type":"1"}],"itemcon":"了解","itemaddress":"fdasdfqwef","itemtime":""}]
                 * weekDoneTotal : 1
                 * daiBanTotal : 0
                 */

                private int caseReportTotal;
                private int noticeTotal;
                private int gridReportTotal;
                private int weekDoneTotal;
                private int daiBanTotal;
                private List<CasesBean> cases;

                public int getCaseReportTotal() {
                    return caseReportTotal;
                }

                public void setCaseReportTotal(int caseReportTotal) {
                    this.caseReportTotal = caseReportTotal;
                }

                public int getNoticeTotal() {
                    return noticeTotal;
                }

                public void setNoticeTotal(int noticeTotal) {
                    this.noticeTotal = noticeTotal;
                }

                public int getGridReportTotal() {
                    return gridReportTotal;
                }

                public void setGridReportTotal(int gridReportTotal) {
                    this.gridReportTotal = gridReportTotal;
                }

                public int getWeekDoneTotal() {
                    return weekDoneTotal;
                }

                public void setWeekDoneTotal(int weekDoneTotal) {
                    this.weekDoneTotal = weekDoneTotal;
                }

                public int getDaiBanTotal() {
                    return daiBanTotal;
                }

                public void setDaiBanTotal(int daiBanTotal) {
                    this.daiBanTotal = daiBanTotal;
                }

                public List<CasesBean> getCases() {
                    return cases;
                }

                public void setCases(List<CasesBean> cases) {
                    this.cases = cases;
                }

                public static class CasesBean {
                    /**
                     * attachments : [{"size":"7.24K","name":"IMG_1076319621.jpg","id":"/v8AMgA3ADkANwA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA2","type":"1"},{"size":"7.28K","name":"IMG_-1055677178.jpg","id":"/v8AMgA3ADkANwA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA3","type":"1"}]
                     * itemcon : grrwrgewrgewgrewgr
                     * itemaddress :
                     * itemtime :
                     */

                    private String itemcon;
                    private String itemaddress;
                    private String itemtime;
                    private List<AttachmentsBean> attachments;

                    public String getItemcon() {
                        return itemcon;
                    }

                    public void setItemcon(String itemcon) {
                        this.itemcon = itemcon;
                    }

                    public String getItemaddress() {
                        return itemaddress;
                    }

                    public void setItemaddress(String itemaddress) {
                        this.itemaddress = itemaddress;
                    }

                    public String getItemtime() {
                        return itemtime;
                    }

                    public void setItemtime(String itemtime) {
                        this.itemtime = itemtime;
                    }

                    public List<AttachmentsBean> getAttachments() {
                        return attachments;
                    }

                    public void setAttachments(List<AttachmentsBean> attachments) {
                        this.attachments = attachments;
                    }

                    public static class AttachmentsBean {
                        /**
                         * size : 7.24K
                         * name : IMG_1076319621.jpg
                         * id : /v8AMgA3ADkANwA2
                         * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA3ADkANwA2
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
}
