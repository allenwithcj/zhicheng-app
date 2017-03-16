package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialWorkDynamicList {


    /**
     * iq : {"namespace":"PersonalDynamicResponse","query":{"prelogcon":{"prelogs":[{"LOCATION":"雉城街道办事处","IMG":"23729999-2424-5238-A4D3-A7D15FB21DDE","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"工作动态","DEPT":"雉城指挥中心","ID":"27B76788-C10A-217E-FB10-B6CA3122A5B4"},{"LOCATION":"府前社区","IMG":"A6730003-5594-5365-8D9A-A1E44A3117DF","DATETIME":"2017-03-15 00:00:00.0","USERID":"张烈纯","COUNT":"\u201c三小的孩子太棒了！为你们喝彩!\u201d \r\n\r\n\u201c一件件神奇的艺术品，真是独具匠心啊！赞一个！\u201d \r\n\r\n\u2026\u2026 \r\n\r\n3月14日下午，长兴三小校园内，一场\u201c碎纸变变变小卖场\u201d商品淘宝义卖活动正在热烈开展，活动现场由学生们制作一幅精美的纸浆画作品引来学生、老师和家长的围观、称赞。该校校长文宏章介绍，这是该校学生综合实践活动《以\u201c纸\u201d为媒，独具\u201c浆\u201d心》成果的展示之一，也是该校学生践行学校\u201c雅正\u201d文化的具体体现。","DEPT":"雉城指挥中心","ID":"6BDD1C21-FDF5-ED16-B892-6226D40174A7"},{"LOCATION":null,"IMG":"C8630001-4674-58EF-878D-A79997343BEF","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"惺惺惜惺惺想寻","DEPT":"雉城指挥中心","ID":"46CEEC39-868B-7F0E-59CD-A5DB3F4A82D5"},{"LOCATION":null,"IMG":"95330000-6A04-57A2-899A-D2500F3B21ED","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":null,"DEPT":"雉城指挥中心","ID":"7787E3FD-CA49-6279-B493-90A7EF944E60"}],"allnum":4},"errorCode":"0","errorMessage":"成功"}}
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
         * query : {"prelogcon":{"prelogs":[{"LOCATION":"雉城街道办事处","IMG":"23729999-2424-5238-A4D3-A7D15FB21DDE","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"工作动态","DEPT":"雉城指挥中心","ID":"27B76788-C10A-217E-FB10-B6CA3122A5B4"},{"LOCATION":"府前社区","IMG":"A6730003-5594-5365-8D9A-A1E44A3117DF","DATETIME":"2017-03-15 00:00:00.0","USERID":"张烈纯","COUNT":"\u201c三小的孩子太棒了！为你们喝彩!\u201d \r\n\r\n\u201c一件件神奇的艺术品，真是独具匠心啊！赞一个！\u201d \r\n\r\n\u2026\u2026 \r\n\r\n3月14日下午，长兴三小校园内，一场\u201c碎纸变变变小卖场\u201d商品淘宝义卖活动正在热烈开展，活动现场由学生们制作一幅精美的纸浆画作品引来学生、老师和家长的围观、称赞。该校校长文宏章介绍，这是该校学生综合实践活动《以\u201c纸\u201d为媒，独具\u201c浆\u201d心》成果的展示之一，也是该校学生践行学校\u201c雅正\u201d文化的具体体现。","DEPT":"雉城指挥中心","ID":"6BDD1C21-FDF5-ED16-B892-6226D40174A7"},{"LOCATION":null,"IMG":"C8630001-4674-58EF-878D-A79997343BEF","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"惺惺惜惺惺想寻","DEPT":"雉城指挥中心","ID":"46CEEC39-868B-7F0E-59CD-A5DB3F4A82D5"},{"LOCATION":null,"IMG":"95330000-6A04-57A2-899A-D2500F3B21ED","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":null,"DEPT":"雉城指挥中心","ID":"7787E3FD-CA49-6279-B493-90A7EF944E60"}],"allnum":4},"errorCode":"0","errorMessage":"成功"}
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
             * prelogcon : {"prelogs":[{"LOCATION":"雉城街道办事处","IMG":"23729999-2424-5238-A4D3-A7D15FB21DDE","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"工作动态","DEPT":"雉城指挥中心","ID":"27B76788-C10A-217E-FB10-B6CA3122A5B4"},{"LOCATION":"府前社区","IMG":"A6730003-5594-5365-8D9A-A1E44A3117DF","DATETIME":"2017-03-15 00:00:00.0","USERID":"张烈纯","COUNT":"\u201c三小的孩子太棒了！为你们喝彩!\u201d \r\n\r\n\u201c一件件神奇的艺术品，真是独具匠心啊！赞一个！\u201d \r\n\r\n\u2026\u2026 \r\n\r\n3月14日下午，长兴三小校园内，一场\u201c碎纸变变变小卖场\u201d商品淘宝义卖活动正在热烈开展，活动现场由学生们制作一幅精美的纸浆画作品引来学生、老师和家长的围观、称赞。该校校长文宏章介绍，这是该校学生综合实践活动《以\u201c纸\u201d为媒，独具\u201c浆\u201d心》成果的展示之一，也是该校学生践行学校\u201c雅正\u201d文化的具体体现。","DEPT":"雉城指挥中心","ID":"6BDD1C21-FDF5-ED16-B892-6226D40174A7"},{"LOCATION":null,"IMG":"C8630001-4674-58EF-878D-A79997343BEF","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"惺惺惜惺惺想寻","DEPT":"雉城指挥中心","ID":"46CEEC39-868B-7F0E-59CD-A5DB3F4A82D5"},{"LOCATION":null,"IMG":"95330000-6A04-57A2-899A-D2500F3B21ED","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":null,"DEPT":"雉城指挥中心","ID":"7787E3FD-CA49-6279-B493-90A7EF944E60"}],"allnum":4}
             * errorCode : 0
             * errorMessage : 成功
             */

            private PrelogconBean prelogcon;
            private String errorCode;
            private String errorMessage;

            public PrelogconBean getPrelogcon() {
                return prelogcon;
            }

            public void setPrelogcon(PrelogconBean prelogcon) {
                this.prelogcon = prelogcon;
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

            public static class PrelogconBean {
                /**
                 * prelogs : [{"LOCATION":"雉城街道办事处","IMG":"23729999-2424-5238-A4D3-A7D15FB21DDE","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"工作动态","DEPT":"雉城指挥中心","ID":"27B76788-C10A-217E-FB10-B6CA3122A5B4"},{"LOCATION":"府前社区","IMG":"A6730003-5594-5365-8D9A-A1E44A3117DF","DATETIME":"2017-03-15 00:00:00.0","USERID":"张烈纯","COUNT":"\u201c三小的孩子太棒了！为你们喝彩!\u201d \r\n\r\n\u201c一件件神奇的艺术品，真是独具匠心啊！赞一个！\u201d \r\n\r\n\u2026\u2026 \r\n\r\n3月14日下午，长兴三小校园内，一场\u201c碎纸变变变小卖场\u201d商品淘宝义卖活动正在热烈开展，活动现场由学生们制作一幅精美的纸浆画作品引来学生、老师和家长的围观、称赞。该校校长文宏章介绍，这是该校学生综合实践活动《以\u201c纸\u201d为媒，独具\u201c浆\u201d心》成果的展示之一，也是该校学生践行学校\u201c雅正\u201d文化的具体体现。","DEPT":"雉城指挥中心","ID":"6BDD1C21-FDF5-ED16-B892-6226D40174A7"},{"LOCATION":null,"IMG":"C8630001-4674-58EF-878D-A79997343BEF","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":"惺惺惜惺惺想寻","DEPT":"雉城指挥中心","ID":"46CEEC39-868B-7F0E-59CD-A5DB3F4A82D5"},{"LOCATION":null,"IMG":"95330000-6A04-57A2-899A-D2500F3B21ED","DATETIME":"2017-03-15 00:00:00.0","USERID":"曹正","COUNT":null,"DEPT":"雉城指挥中心","ID":"7787E3FD-CA49-6279-B493-90A7EF944E60"}]
                 * allnum : 4
                 */

                private int allnum;
                private List<PrelogsBean> prelogs;

                public int getAllnum() {
                    return allnum;
                }

                public void setAllnum(int allnum) {
                    this.allnum = allnum;
                }

                public List<PrelogsBean> getPrelogs() {
                    return prelogs;
                }

                public void setPrelogs(List<PrelogsBean> prelogs) {
                    this.prelogs = prelogs;
                }

                public static class PrelogsBean {
                    /**
                     * LOCATION : 雉城街道办事处
                     * IMG : 23729999-2424-5238-A4D3-A7D15FB21DDE
                     * DATETIME : 2017-03-15 00:00:00.0
                     * USERID : 曹正
                     * COUNT : 工作动态
                     * DEPT : 雉城指挥中心
                     * ID : 27B76788-C10A-217E-FB10-B6CA3122A5B4
                     */

                    private String LOCATION;
                    private String IMG;
                    private String DATETIME;
                    private String USERID;
                    private String COUNT;
                    private String DEPT;
                    private String ID;

                    public String getLOCATION() {
                        return LOCATION;
                    }

                    public void setLOCATION(String LOCATION) {
                        this.LOCATION = LOCATION;
                    }

                    public String getIMG() {
                        return IMG;
                    }

                    public void setIMG(String IMG) {
                        this.IMG = IMG;
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
                }
            }
        }
    }
}
