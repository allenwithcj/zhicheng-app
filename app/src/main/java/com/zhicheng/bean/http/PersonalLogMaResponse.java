package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/2.
 */

public class PersonalLogMaResponse {


    /**
     * iq : {"namespace":"PersonalLogMaResponse","query":{"prelogcon":{"prelogs":[{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"481.95K","name":"magazine-unlock-05-2.3.529-bigpicture_05_32.jpg","id":"/v8AMgA4ADcANAA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA4","type":"1"}],"cd01":"2017-03-18 13:26:02.0","cd02":"检查检查","cd00":"3632b667-9f88-4157-a76f-1cb377f21b3a"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"15.01K","name":"IMG_1963056045_clip.jpg","id":"/v8AMgA4ADcANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA2","type":"1"}],"cd01":"2017-03-18 13:21:28.0","cd02":"测试版","cd00":"8d8088e5-34c6-4f86-800f-2615f6acf842"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"3.02M","name":"IMG20170310165136.jpg","id":"/v8AMgA4ADcANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA1","type":"1"}],"cd01":"2017-03-18 13:20:54.0","cd02":"工作测试","cd00":"b873d4ca-f0b6-4dde-b2ab-1030a8b7e03d"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"7.59K","name":"pic_uc_1488970963898.jpg","id":"/v8AMgA4ADYANwA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADYANwA1","type":"1"}],"cd01":"2017-03-17 17:30:22.0","cd02":"xbnxn","cd00":"d1c945b8-71b9-49ce-a0d5-bad775d366ee"}],"allnum":4},"errorCode":"0","errorMessage":"成功"}}
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
         * namespace : PersonalLogMaResponse
         * query : {"prelogcon":{"prelogs":[{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"481.95K","name":"magazine-unlock-05-2.3.529-bigpicture_05_32.jpg","id":"/v8AMgA4ADcANAA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA4","type":"1"}],"cd01":"2017-03-18 13:26:02.0","cd02":"检查检查","cd00":"3632b667-9f88-4157-a76f-1cb377f21b3a"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"15.01K","name":"IMG_1963056045_clip.jpg","id":"/v8AMgA4ADcANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA2","type":"1"}],"cd01":"2017-03-18 13:21:28.0","cd02":"测试版","cd00":"8d8088e5-34c6-4f86-800f-2615f6acf842"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"3.02M","name":"IMG20170310165136.jpg","id":"/v8AMgA4ADcANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA1","type":"1"}],"cd01":"2017-03-18 13:20:54.0","cd02":"工作测试","cd00":"b873d4ca-f0b6-4dde-b2ab-1030a8b7e03d"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"7.59K","name":"pic_uc_1488970963898.jpg","id":"/v8AMgA4ADYANwA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADYANwA1","type":"1"}],"cd01":"2017-03-17 17:30:22.0","cd02":"xbnxn","cd00":"d1c945b8-71b9-49ce-a0d5-bad775d366ee"}],"allnum":4},"errorCode":"0","errorMessage":"成功"}
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
             * prelogcon : {"prelogs":[{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"481.95K","name":"magazine-unlock-05-2.3.529-bigpicture_05_32.jpg","id":"/v8AMgA4ADcANAA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA4","type":"1"}],"cd01":"2017-03-18 13:26:02.0","cd02":"检查检查","cd00":"3632b667-9f88-4157-a76f-1cb377f21b3a"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"15.01K","name":"IMG_1963056045_clip.jpg","id":"/v8AMgA4ADcANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA2","type":"1"}],"cd01":"2017-03-18 13:21:28.0","cd02":"测试版","cd00":"8d8088e5-34c6-4f86-800f-2615f6acf842"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"3.02M","name":"IMG20170310165136.jpg","id":"/v8AMgA4ADcANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA1","type":"1"}],"cd01":"2017-03-18 13:20:54.0","cd02":"工作测试","cd00":"b873d4ca-f0b6-4dde-b2ab-1030a8b7e03d"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"7.59K","name":"pic_uc_1488970963898.jpg","id":"/v8AMgA4ADYANwA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADYANwA1","type":"1"}],"cd01":"2017-03-17 17:30:22.0","cd02":"xbnxn","cd00":"d1c945b8-71b9-49ce-a0d5-bad775d366ee"}],"allnum":4}
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
                 * prelogs : [{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"481.95K","name":"magazine-unlock-05-2.3.529-bigpicture_05_32.jpg","id":"/v8AMgA4ADcANAA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA4","type":"1"}],"cd01":"2017-03-18 13:26:02.0","cd02":"检查检查","cd00":"3632b667-9f88-4157-a76f-1cb377f21b3a"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"15.01K","name":"IMG_1963056045_clip.jpg","id":"/v8AMgA4ADcANAA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA2","type":"1"}],"cd01":"2017-03-18 13:21:28.0","cd02":"测试版","cd00":"8d8088e5-34c6-4f86-800f-2615f6acf842"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"3.02M","name":"IMG20170310165136.jpg","id":"/v8AMgA4ADcANAA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA1","type":"1"}],"cd01":"2017-03-18 13:20:54.0","cd02":"工作测试","cd00":"b873d4ca-f0b6-4dde-b2ab-1030a8b7e03d"},{"cd05":"钮店湾南片-第一网格","cd03":"李军军","cd04":[{"size":"7.59K","name":"pic_uc_1488970963898.jpg","id":"/v8AMgA4ADYANwA1","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADYANwA1","type":"1"}],"cd01":"2017-03-17 17:30:22.0","cd02":"xbnxn","cd00":"d1c945b8-71b9-49ce-a0d5-bad775d366ee"}]
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
                     * cd05 : 钮店湾南片-第一网格
                     * cd03 : 李军军
                     * cd04 : [{"size":"481.95K","name":"magazine-unlock-05-2.3.529-bigpicture_05_32.jpg","id":"/v8AMgA4ADcANAA4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA4","type":"1"}]
                     * cd01 : 2017-03-18 13:26:02.0
                     * cd02 : 检查检查
                     * cd00 : 3632b667-9f88-4157-a76f-1cb377f21b3a
                     */

                    private String cd05;
                    private String cd03;
                    private String cd01;
                    private String cd02;
                    private String cd00;
                    private List<Cd04Bean> cd04;

                    public String getCd05() {
                        return cd05;
                    }

                    public void setCd05(String cd05) {
                        this.cd05 = cd05;
                    }

                    public String getCd03() {
                        return cd03;
                    }

                    public void setCd03(String cd03) {
                        this.cd03 = cd03;
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

                    public String getCd00() {
                        return cd00;
                    }

                    public void setCd00(String cd00) {
                        this.cd00 = cd00;
                    }

                    public List<Cd04Bean> getCd04() {
                        return cd04;
                    }

                    public void setCd04(List<Cd04Bean> cd04) {
                        this.cd04 = cd04;
                    }

                    public static class Cd04Bean {
                        /**
                         * size : 481.95K
                         * name : magazine-unlock-05-2.3.529-bigpicture_05_32.jpg
                         * id : /v8AMgA4ADcANAA4
                         * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcANAA4
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
