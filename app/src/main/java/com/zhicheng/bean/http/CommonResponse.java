package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/2/16.
 */

public class CommonResponse {


    /**
     * iq : {"query":{"data":{"caseReportTotal":1,"gridReportTotal":0,"cases":[{"attachments":[{"size":"83.46K","name":"(v8AMgA3ADYAMAA4ADg=)5.jpg","id":"/v8AMgA4ADkANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAx","type":"1"}],"wt00":"530","caseId":"379","caseNo":"YPT20170323010","itemcon":"易燃易爆物品","itemaddress":"中国浙江省湖州市长兴县林荫路","itemtime":"2017-03-23 14:40:04.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkAOAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA2","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkAOAA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA3","type":"1"}],"wt00":"536","caseId":"385","caseNo":"YPT20170323016","itemcon":"长时间没有产出带来的恐慌是蛮大的。","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 04:23:29.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAAy","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA0","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA3","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkANgA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA4","type":"1"},{"size":"36.32K","name":"image2.png","id":"/v8AMgA4ADkANgA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA5","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANwAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAw","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA1","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA2","type":"1"}],"wt00":"529","caseId":"378","caseNo":"YPT20170323009","itemcon":"1.首页 今日爆料 需要查看详情 后台需要详情 \r\n2..flowStatus返回的数据为空 －－－(处理流程)\r\n3.网格员自己上报自己处理案件不能结案 （App端）书记和领导不能结案（办理进度）\r\n4..传阅的事项不能出现在待办事项中 \r\n5.PC端（传阅不能搜索对象","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 02:40:26.0"}],"weekDoneTotal":0,"noticeTotal":0,"daiBanTotal":0},"errorMessage":"成功","errorCode":0},"namespace":"NewsCaseTotalResponse"}
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
         * query : {"data":{"caseReportTotal":1,"gridReportTotal":0,"cases":[{"attachments":[{"size":"83.46K","name":"(v8AMgA3ADYAMAA4ADg=)5.jpg","id":"/v8AMgA4ADkANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAx","type":"1"}],"wt00":"530","caseId":"379","caseNo":"YPT20170323010","itemcon":"易燃易爆物品","itemaddress":"中国浙江省湖州市长兴县林荫路","itemtime":"2017-03-23 14:40:04.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkAOAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA2","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkAOAA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA3","type":"1"}],"wt00":"536","caseId":"385","caseNo":"YPT20170323016","itemcon":"长时间没有产出带来的恐慌是蛮大的。","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 04:23:29.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAAy","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA0","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA3","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkANgA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA4","type":"1"},{"size":"36.32K","name":"image2.png","id":"/v8AMgA4ADkANgA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA5","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANwAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAw","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA1","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA2","type":"1"}],"wt00":"529","caseId":"378","caseNo":"YPT20170323009","itemcon":"1.首页 今日爆料 需要查看详情 后台需要详情 \r\n2..flowStatus返回的数据为空 －－－(处理流程)\r\n3.网格员自己上报自己处理案件不能结案 （App端）书记和领导不能结案（办理进度）\r\n4..传阅的事项不能出现在待办事项中 \r\n5.PC端（传阅不能搜索对象","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 02:40:26.0"}],"weekDoneTotal":0,"noticeTotal":0,"daiBanTotal":0},"errorMessage":"成功","errorCode":0}
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
             * data : {"caseReportTotal":1,"gridReportTotal":0,"cases":[{"attachments":[{"size":"83.46K","name":"(v8AMgA3ADYAMAA4ADg=)5.jpg","id":"/v8AMgA4ADkANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAx","type":"1"}],"wt00":"530","caseId":"379","caseNo":"YPT20170323010","itemcon":"易燃易爆物品","itemaddress":"中国浙江省湖州市长兴县林荫路","itemtime":"2017-03-23 14:40:04.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkAOAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA2","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkAOAA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA3","type":"1"}],"wt00":"536","caseId":"385","caseNo":"YPT20170323016","itemcon":"长时间没有产出带来的恐慌是蛮大的。","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 04:23:29.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAAy","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA0","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA3","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkANgA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA4","type":"1"},{"size":"36.32K","name":"image2.png","id":"/v8AMgA4ADkANgA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA5","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANwAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAw","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA1","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA2","type":"1"}],"wt00":"529","caseId":"378","caseNo":"YPT20170323009","itemcon":"1.首页 今日爆料 需要查看详情 后台需要详情 \r\n2..flowStatus返回的数据为空 －－－(处理流程)\r\n3.网格员自己上报自己处理案件不能结案 （App端）书记和领导不能结案（办理进度）\r\n4..传阅的事项不能出现在待办事项中 \r\n5.PC端（传阅不能搜索对象","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 02:40:26.0"}],"weekDoneTotal":0,"noticeTotal":0,"daiBanTotal":0}
             * errorMessage : 成功
             * errorCode : 0
             */

            private DataBean data;
            private String errorMessage;
            private int errorCode;
            private List<AttaItemsBean> attaItems;

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

            public List<AttaItemsBean> getAttaItems() {
                return attaItems;
            }

            public void setAttaItems(List<AttaItemsBean> attaItems) {
                this.attaItems = attaItems;
            }

            public static class AttaItemsBean {
                /**
                 * guid : AF4D7BA1-82D4-80AF-F52B-AAE2332E2541
                 * time :
                 * master_key : MzAzMDk=
                 */

                private String guid;
                private String time;
                private String master_key;

                public String getGuid() {
                    return guid;
                }

                public void setGuid(String guid) {
                    this.guid = guid;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getMaster_key() {
                    return master_key;
                }

                public void setMaster_key(String master_key) {
                    this.master_key = master_key;
                }
            }

            public static class DataBean {
                /**
                 * caseReportTotal : 1
                 * gridReportTotal : 0
                 * cases : [{"attachments":[{"size":"83.46K","name":"(v8AMgA3ADYAMAA4ADg=)5.jpg","id":"/v8AMgA4ADkANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAx","type":"1"}],"wt00":"530","caseId":"379","caseNo":"YPT20170323010","itemcon":"易燃易爆物品","itemaddress":"中国浙江省湖州市长兴县林荫路","itemtime":"2017-03-23 14:40:04.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkAOAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA2","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkAOAA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkAOAA3","type":"1"}],"wt00":"536","caseId":"385","caseNo":"YPT20170323016","itemcon":"长时间没有产出带来的恐慌是蛮大的。","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 04:23:29.0"},{"attachments":[{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAAy","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA0","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA3","type":"1"},{"size":"33.08K","name":"image1.png","id":"/v8AMgA4ADkANgA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA4","type":"1"},{"size":"36.32K","name":"image2.png","id":"/v8AMgA4ADkANgA5","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANgA5","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANwAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAw","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA1","type":"1"},{"size":"74.67K","name":"image0.png","id":"/v8AMgA4ADkANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANAA2","type":"1"}],"wt00":"529","caseId":"378","caseNo":"YPT20170323009","itemcon":"1.首页 今日爆料 需要查看详情 后台需要详情 \r\n2..flowStatus返回的数据为空 －－－(处理流程)\r\n3.网格员自己上报自己处理案件不能结案 （App端）书记和领导不能结案（办理进度）\r\n4..传阅的事项不能出现在待办事项中 \r\n5.PC端（传阅不能搜索对象","itemaddress":"湖北省仙桃市","itemtime":"2017-03-23 02:40:26.0"}]
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
                     * attachments : [{"size":"83.46K","name":"(v8AMgA3ADYAMAA4ADg=)5.jpg","id":"/v8AMgA4ADkANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAx","type":"1"}]
                     * wt00 : 530
                     * caseId : 379
                     * caseNo : YPT20170323010
                     * itemcon : 易燃易爆物品
                     * itemaddress : 中国浙江省湖州市长兴县林荫路
                     * itemtime : 2017-03-23 14:40:04.0
                     */

                    private String wt00;
                    private String caseId;
                    private String caseNo;
                    private String itemcon;
                    private String itemaddress;
                    private String itemtime;
                    private List<AttachmentsBean> attachments;

                    public String getWt00() {
                        return wt00;
                    }

                    public void setWt00(String wt00) {
                        this.wt00 = wt00;
                    }

                    public String getCaseId() {
                        return caseId;
                    }

                    public void setCaseId(String caseId) {
                        this.caseId = caseId;
                    }

                    public String getCaseNo() {
                        return caseNo;
                    }

                    public void setCaseNo(String caseNo) {
                        this.caseNo = caseNo;
                    }

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
                         * size : 83.46K
                         * name : (v8AMgA3ADYAMAA4ADg=)5.jpg
                         * id : /v8AMgA4ADkANwAx
                         * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADkANwAx
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
