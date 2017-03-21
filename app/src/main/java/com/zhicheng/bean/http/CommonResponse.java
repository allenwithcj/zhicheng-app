package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/2/16.
 */

public class CommonResponse {


    /**
     * iq : {"query":{"data":{"caseReportTotal":1,"gridReportTotal":0,"cases":[{"attachments":[{"size":"5.82M","name":"IMG_20170321_085745.jpg","id":"/v8AMgA4ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAw","type":"1"},{"size":"4.37M","name":"IMG_20170321_085727.jpg","id":"/v8AMgA4ADkAMQAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAx","type":"1"}],"itemcon":"在金莲桥泥桥湾小区内，一辆汽车堵在垃圾桶旁边，致使保洁人员无法对垃圾进行收集","itemaddress":"中国浙江省湖州市长兴县金莲桥弄","itemtime":"2017-03-21 09:02:54.0"}],"weekDoneTotal":0,"noticeTotal":0,"daiBanTotal":0},"errorMessage":"成功","errorCode":0},"namespace":"NewsCaseTotalResponse"}
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
         * query : {"data":{"caseReportTotal":1,"gridReportTotal":0,"cases":[{"attachments":[{"size":"5.82M","name":"IMG_20170321_085745.jpg","id":"/v8AMgA4ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAw","type":"1"},{"size":"4.37M","name":"IMG_20170321_085727.jpg","id":"/v8AMgA4ADkAMQAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAx","type":"1"}],"itemcon":"在金莲桥泥桥湾小区内，一辆汽车堵在垃圾桶旁边，致使保洁人员无法对垃圾进行收集","itemaddress":"中国浙江省湖州市长兴县金莲桥弄","itemtime":"2017-03-21 09:02:54.0"}],"weekDoneTotal":0,"noticeTotal":0,"daiBanTotal":0},"errorMessage":"成功","errorCode":0}
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
             * data : {"caseReportTotal":1,"gridReportTotal":0,"cases":[{"attachments":[{"size":"5.82M","name":"IMG_20170321_085745.jpg","id":"/v8AMgA4ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAw","type":"1"},{"size":"4.37M","name":"IMG_20170321_085727.jpg","id":"/v8AMgA4ADkAMQAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAx","type":"1"}],"itemcon":"在金莲桥泥桥湾小区内，一辆汽车堵在垃圾桶旁边，致使保洁人员无法对垃圾进行收集","itemaddress":"中国浙江省湖州市长兴县金莲桥弄","itemtime":"2017-03-21 09:02:54.0"}],"weekDoneTotal":0,"noticeTotal":0,"daiBanTotal":0}
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
                 * caseReportTotal : 1
                 * gridReportTotal : 0
                 * cases : [{"attachments":[{"size":"5.82M","name":"IMG_20170321_085745.jpg","id":"/v8AMgA4ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAw","type":"1"},{"size":"4.37M","name":"IMG_20170321_085727.jpg","id":"/v8AMgA4ADkAMQAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAx","type":"1"}],"itemcon":"在金莲桥泥桥湾小区内，一辆汽车堵在垃圾桶旁边，致使保洁人员无法对垃圾进行收集","itemaddress":"中国浙江省湖州市长兴县金莲桥弄","itemtime":"2017-03-21 09:02:54.0"}]
                 * weekDoneTotal : 0
                 * noticeTotal : 0
                 * daiBanTotal : 0
                 */

                private int caseReportTotal;
                private int gridReportTotal;
                private int weekDoneTotal;
                private int noticeTotal;
                private int daiBanTotal;
                private List<CasesBean> cases;

                public int getCaseReportTotal() {
                    return caseReportTotal;
                }

                public void setCaseReportTotal(int caseReportTotal) {
                    this.caseReportTotal = caseReportTotal;
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

                public int getNoticeTotal() {
                    return noticeTotal;
                }

                public void setNoticeTotal(int noticeTotal) {
                    this.noticeTotal = noticeTotal;
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
                     * attachments : [{"size":"5.82M","name":"IMG_20170321_085745.jpg","id":"/v8AMgA4ADkAMQAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAw","type":"1"},{"size":"4.37M","name":"IMG_20170321_085727.jpg","id":"/v8AMgA4ADkAMQAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAx","type":"1"}]
                     * itemcon : 在金莲桥泥桥湾小区内，一辆汽车堵在垃圾桶旁边，致使保洁人员无法对垃圾进行收集
                     * itemaddress : 中国浙江省湖州市长兴县金莲桥弄
                     * itemtime : 2017-03-21 09:02:54.0
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
                         * size : 5.82M
                         * name : IMG_20170321_085745.jpg
                         * id : /v8AMgA4ADkAMQAw
                         * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAMQAw
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
