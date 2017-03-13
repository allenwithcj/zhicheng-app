package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/2.
 */

public class PersonalLogMaResponse {
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

            private Prelogcon prelogcon;
            private String errorMessage;
            private int errorCode;

            public Prelogcon getPrelogcon() {
                return prelogcon;
            }

            public void setPrelogcon(Prelogcon prelogcon) {
                this.prelogcon = prelogcon;
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

            public static class Prelogcon {

                private String allnum;
                private List<Prelog> prelogs;

                public String getAllnum() {
                    return allnum;
                }

                public void setAllnum(String allnum) {
                    this.allnum = allnum;
                }

                public List<Prelog> getPrelogs() {
                    return prelogs;
                }

                public void setPrelogs(List<Prelog> prelogs) {
                    this.prelogs = prelogs;
                }

                public static class Prelog {
                    public String cd00;
                    public String cd01;
                    public String cd02;
                    public String cd03;
                    public String cd05;

                    public String getCd00() {
                        return cd00;
                    }

                    public void setCd00(String cd00) {
                        this.cd00 = cd00;
                    }

                    public String getCd01() {
                        return cd01;
                    }

                    public void setCd01(String cd01) {
                        this.cd01 = cd01;
                    }

                    public String getCd02() {
                        return cd02;
                    }

                    public void setCd02(String cd02) {
                        this.cd02 = cd02;
                    }

                    public String getCd03() {
                        return cd03;
                    }

                    public void setCd03(String cd03) {
                        this.cd03 = cd03;
                    }

                    public String getCd05() {
                        return cd05;
                    }

                    public void setCd05(String cd05) {
                        this.cd05 = cd05;
                    }
                }
            }
        }
    }
}
