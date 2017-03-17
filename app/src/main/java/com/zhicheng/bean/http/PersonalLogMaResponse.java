package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/2.
 */

public class PersonalLogMaResponse {

    /**
     * iq : {"namespace":"PersonalLogMaResponse","query":{"prelogcon":{"prelogs":[{"cd05":"安监中心","cd03":"熊文文","cd04":[{"size":"18.69K","name":"pic_uc_1488970950034.jpg","id":"/v8AMgA4ADUANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANwAx","type":"1"}],"cd01":"2017-03-17 20:51:09.0","cd02":"我所有者权益","cd00":"638290f8-0719-4d16-9cbc-bd7c6fe73dcf"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:36.0","cd02":"nxnxnx","cd00":"76b1a449-6282-4fb0-8efd-f69ab2380661"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:34.0","cd02":"jxjxj","cd00":"c25b6f6d-4c83-41c0-a2ec-78e6cdb22032"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:32.0","cd02":"nxnxn","cd00":"73f6da89-1bf0-4577-8a3f-ef427b3f4730"}],"allnum":4},"errorCode":"0","errorMessage":"成功"}}
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
         * query : {"prelogcon":{"prelogs":[{"cd05":"安监中心","cd03":"熊文文","cd04":[{"size":"18.69K","name":"pic_uc_1488970950034.jpg","id":"/v8AMgA4ADUANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANwAx","type":"1"}],"cd01":"2017-03-17 20:51:09.0","cd02":"我所有者权益","cd00":"638290f8-0719-4d16-9cbc-bd7c6fe73dcf"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:36.0","cd02":"nxnxnx","cd00":"76b1a449-6282-4fb0-8efd-f69ab2380661"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:34.0","cd02":"jxjxj","cd00":"c25b6f6d-4c83-41c0-a2ec-78e6cdb22032"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:32.0","cd02":"nxnxn","cd00":"73f6da89-1bf0-4577-8a3f-ef427b3f4730"}],"allnum":4},"errorCode":"0","errorMessage":"成功"}
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
             * prelogcon : {"prelogs":[{"cd05":"安监中心","cd03":"熊文文","cd04":[{"size":"18.69K","name":"pic_uc_1488970950034.jpg","id":"/v8AMgA4ADUANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANwAx","type":"1"}],"cd01":"2017-03-17 20:51:09.0","cd02":"我所有者权益","cd00":"638290f8-0719-4d16-9cbc-bd7c6fe73dcf"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:36.0","cd02":"nxnxnx","cd00":"76b1a449-6282-4fb0-8efd-f69ab2380661"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:34.0","cd02":"jxjxj","cd00":"c25b6f6d-4c83-41c0-a2ec-78e6cdb22032"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:32.0","cd02":"nxnxn","cd00":"73f6da89-1bf0-4577-8a3f-ef427b3f4730"}],"allnum":4}
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
                 * prelogs : [{"cd05":"安监中心","cd03":"熊文文","cd04":[{"size":"18.69K","name":"pic_uc_1488970950034.jpg","id":"/v8AMgA4ADUANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANwAx","type":"1"}],"cd01":"2017-03-17 20:51:09.0","cd02":"我所有者权益","cd00":"638290f8-0719-4d16-9cbc-bd7c6fe73dcf"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:36.0","cd02":"nxnxnx","cd00":"76b1a449-6282-4fb0-8efd-f69ab2380661"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:34.0","cd02":"jxjxj","cd00":"c25b6f6d-4c83-41c0-a2ec-78e6cdb22032"},{"cd05":"安监中心","cd03":"熊文文","cd04":[],"cd01":"2017-03-13 14:46:32.0","cd02":"nxnxn","cd00":"73f6da89-1bf0-4577-8a3f-ef427b3f4730"}]
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
                     * cd05 : 安监中心
                     * cd03 : 熊文文
                     * cd04 : [{"size":"18.69K","name":"pic_uc_1488970950034.jpg","id":"/v8AMgA4ADUANwAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANwAx","type":"1"}]
                     * cd01 : 2017-03-17 20:51:09.0
                     * cd02 : 我所有者权益
                     * cd00 : 638290f8-0719-4d16-9cbc-bd7c6fe73dcf
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
                         * size : 18.69K
                         * name : pic_uc_1488970950034.jpg
                         * id : /v8AMgA4ADUANwAx
                         * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANwAx
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
