package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/1/15.
 */

public class OfficialWorkDynamicList {


    /**
     * iq : {"namespace":"PersonalDynamicResponse","query":{"prelogcon":{"prelogs":[{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"288.40K","name":"Balance(magazine)-02-2.3.005-bigpicture_02_1.jpg","id":"/v8AMgA4ADUANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA3","type":"1"}],"DATETIME":"2017-03-16 17:57:35.0","USERID":"宋莹","COUNT":"来吧，说点什么","DEPT":"凤凰小区-第四网格","ID":"42654448-bd64-48db-b4c9-d0750629be90"},{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"885.46K","name":"Picture_03_Eiffel.jpg","id":"/v8AMgA4ADUANgA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA2","type":"1"}],"DATETIME":"2017-03-16 17:55:13.0","USERID":"宋莹","COUNT":"好的时候就会让你有Tom","DEPT":"凤凰小区-第四网格","ID":"cb822f73-fea8-4b2d-b2bb-6fbb9d740c0c"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"51.65K","name":"0081d0b0-e817-4f13-b2e8-4c7f318bee07.jpg","id":"/v8AMgA4ADUANgA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA0","type":"1"}],"DATETIME":"2017-03-16 17:26:31.0","USERID":"宋莹","COUNT":"有个职业生涯小朋友小清新","DEPT":"凤凰小区-第四网格","ID":"39de3fd0-2582-4fef-a413-f60c868989be"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"225.03K","name":"IMG_20170225_154306.jpg","id":"/v8AMgA4ADUANgAz","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAz","type":"1"}],"DATETIME":"2017-03-16 17:23:28.0","USERID":"宋莹","COUNT":"来吧来吧来吧","DEPT":"凤凰小区-第四网格","ID":"52a6894e-95a4-40f4-a779-dfdab1333d7e"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"226.14K","name":"IMG_20170225_154332.jpg","id":"/v8AMgA4ADUANgAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAy","type":"1"}],"DATETIME":"2017-03-16 17:18:41.0","USERID":"宋莹","COUNT":"默默的说话题材","DEPT":"凤凰小区-第四网格","ID":"133dfd0a-d9a1-4130-aa52-b194b2c7e250"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"323.88K","name":"Balance(magazine)-03-2.3.005-bigpicture_03_1.jpg","id":"/v8AMgA4ADUANgAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAx","type":"1"}],"DATETIME":"2017-03-16 17:14:43.0","USERID":"宋莹","COUNT":"我是刘建军，你打我啊","DEPT":"凤凰小区-第四网格","ID":"f8856fdc-2a18-477c-bb89-393aaeb3bd29"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"2.24M","name":"IMG_-1568577897.jpg","id":"/v8AMgA4ADUANgAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAw","type":"1"}],"DATETIME":"2017-03-16 17:02:29.0","USERID":"宋莹","COUNT":"测试","DEPT":"凤凰小区-第四网格","ID":"34a2fd01-ab56-4aed-b16a-0d538324c2f1"}],"allnum":7},"errorCode":"0","errorMessage":"成功"}}
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
         * query : {"prelogcon":{"prelogs":[{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"288.40K","name":"Balance(magazine)-02-2.3.005-bigpicture_02_1.jpg","id":"/v8AMgA4ADUANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA3","type":"1"}],"DATETIME":"2017-03-16 17:57:35.0","USERID":"宋莹","COUNT":"来吧，说点什么","DEPT":"凤凰小区-第四网格","ID":"42654448-bd64-48db-b4c9-d0750629be90"},{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"885.46K","name":"Picture_03_Eiffel.jpg","id":"/v8AMgA4ADUANgA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA2","type":"1"}],"DATETIME":"2017-03-16 17:55:13.0","USERID":"宋莹","COUNT":"好的时候就会让你有Tom","DEPT":"凤凰小区-第四网格","ID":"cb822f73-fea8-4b2d-b2bb-6fbb9d740c0c"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"51.65K","name":"0081d0b0-e817-4f13-b2e8-4c7f318bee07.jpg","id":"/v8AMgA4ADUANgA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA0","type":"1"}],"DATETIME":"2017-03-16 17:26:31.0","USERID":"宋莹","COUNT":"有个职业生涯小朋友小清新","DEPT":"凤凰小区-第四网格","ID":"39de3fd0-2582-4fef-a413-f60c868989be"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"225.03K","name":"IMG_20170225_154306.jpg","id":"/v8AMgA4ADUANgAz","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAz","type":"1"}],"DATETIME":"2017-03-16 17:23:28.0","USERID":"宋莹","COUNT":"来吧来吧来吧","DEPT":"凤凰小区-第四网格","ID":"52a6894e-95a4-40f4-a779-dfdab1333d7e"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"226.14K","name":"IMG_20170225_154332.jpg","id":"/v8AMgA4ADUANgAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAy","type":"1"}],"DATETIME":"2017-03-16 17:18:41.0","USERID":"宋莹","COUNT":"默默的说话题材","DEPT":"凤凰小区-第四网格","ID":"133dfd0a-d9a1-4130-aa52-b194b2c7e250"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"323.88K","name":"Balance(magazine)-03-2.3.005-bigpicture_03_1.jpg","id":"/v8AMgA4ADUANgAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAx","type":"1"}],"DATETIME":"2017-03-16 17:14:43.0","USERID":"宋莹","COUNT":"我是刘建军，你打我啊","DEPT":"凤凰小区-第四网格","ID":"f8856fdc-2a18-477c-bb89-393aaeb3bd29"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"2.24M","name":"IMG_-1568577897.jpg","id":"/v8AMgA4ADUANgAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAw","type":"1"}],"DATETIME":"2017-03-16 17:02:29.0","USERID":"宋莹","COUNT":"测试","DEPT":"凤凰小区-第四网格","ID":"34a2fd01-ab56-4aed-b16a-0d538324c2f1"}],"allnum":7},"errorCode":"0","errorMessage":"成功"}
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
             * prelogcon : {"prelogs":[{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"288.40K","name":"Balance(magazine)-02-2.3.005-bigpicture_02_1.jpg","id":"/v8AMgA4ADUANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA3","type":"1"}],"DATETIME":"2017-03-16 17:57:35.0","USERID":"宋莹","COUNT":"来吧，说点什么","DEPT":"凤凰小区-第四网格","ID":"42654448-bd64-48db-b4c9-d0750629be90"},{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"885.46K","name":"Picture_03_Eiffel.jpg","id":"/v8AMgA4ADUANgA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA2","type":"1"}],"DATETIME":"2017-03-16 17:55:13.0","USERID":"宋莹","COUNT":"好的时候就会让你有Tom","DEPT":"凤凰小区-第四网格","ID":"cb822f73-fea8-4b2d-b2bb-6fbb9d740c0c"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"51.65K","name":"0081d0b0-e817-4f13-b2e8-4c7f318bee07.jpg","id":"/v8AMgA4ADUANgA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA0","type":"1"}],"DATETIME":"2017-03-16 17:26:31.0","USERID":"宋莹","COUNT":"有个职业生涯小朋友小清新","DEPT":"凤凰小区-第四网格","ID":"39de3fd0-2582-4fef-a413-f60c868989be"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"225.03K","name":"IMG_20170225_154306.jpg","id":"/v8AMgA4ADUANgAz","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAz","type":"1"}],"DATETIME":"2017-03-16 17:23:28.0","USERID":"宋莹","COUNT":"来吧来吧来吧","DEPT":"凤凰小区-第四网格","ID":"52a6894e-95a4-40f4-a779-dfdab1333d7e"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"226.14K","name":"IMG_20170225_154332.jpg","id":"/v8AMgA4ADUANgAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAy","type":"1"}],"DATETIME":"2017-03-16 17:18:41.0","USERID":"宋莹","COUNT":"默默的说话题材","DEPT":"凤凰小区-第四网格","ID":"133dfd0a-d9a1-4130-aa52-b194b2c7e250"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"323.88K","name":"Balance(magazine)-03-2.3.005-bigpicture_03_1.jpg","id":"/v8AMgA4ADUANgAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAx","type":"1"}],"DATETIME":"2017-03-16 17:14:43.0","USERID":"宋莹","COUNT":"我是刘建军，你打我啊","DEPT":"凤凰小区-第四网格","ID":"f8856fdc-2a18-477c-bb89-393aaeb3bd29"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"2.24M","name":"IMG_-1568577897.jpg","id":"/v8AMgA4ADUANgAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAw","type":"1"}],"DATETIME":"2017-03-16 17:02:29.0","USERID":"宋莹","COUNT":"测试","DEPT":"凤凰小区-第四网格","ID":"34a2fd01-ab56-4aed-b16a-0d538324c2f1"}],"allnum":7}
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
                 * prelogs : [{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"288.40K","name":"Balance(magazine)-02-2.3.005-bigpicture_02_1.jpg","id":"/v8AMgA4ADUANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA3","type":"1"}],"DATETIME":"2017-03-16 17:57:35.0","USERID":"宋莹","COUNT":"来吧，说点什么","DEPT":"凤凰小区-第四网格","ID":"42654448-bd64-48db-b4c9-d0750629be90"},{"LOCATION":"中国浙江省湖州市长兴县林荫路","IMG":[{"size":"885.46K","name":"Picture_03_Eiffel.jpg","id":"/v8AMgA4ADUANgA2","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA2","type":"1"}],"DATETIME":"2017-03-16 17:55:13.0","USERID":"宋莹","COUNT":"好的时候就会让你有Tom","DEPT":"凤凰小区-第四网格","ID":"cb822f73-fea8-4b2d-b2bb-6fbb9d740c0c"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"51.65K","name":"0081d0b0-e817-4f13-b2e8-4c7f318bee07.jpg","id":"/v8AMgA4ADUANgA0","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA0","type":"1"}],"DATETIME":"2017-03-16 17:26:31.0","USERID":"宋莹","COUNT":"有个职业生涯小朋友小清新","DEPT":"凤凰小区-第四网格","ID":"39de3fd0-2582-4fef-a413-f60c868989be"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"225.03K","name":"IMG_20170225_154306.jpg","id":"/v8AMgA4ADUANgAz","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAz","type":"1"}],"DATETIME":"2017-03-16 17:23:28.0","USERID":"宋莹","COUNT":"来吧来吧来吧","DEPT":"凤凰小区-第四网格","ID":"52a6894e-95a4-40f4-a779-dfdab1333d7e"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"226.14K","name":"IMG_20170225_154332.jpg","id":"/v8AMgA4ADUANgAy","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAy","type":"1"}],"DATETIME":"2017-03-16 17:18:41.0","USERID":"宋莹","COUNT":"默默的说话题材","DEPT":"凤凰小区-第四网格","ID":"133dfd0a-d9a1-4130-aa52-b194b2c7e250"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"323.88K","name":"Balance(magazine)-03-2.3.005-bigpicture_03_1.jpg","id":"/v8AMgA4ADUANgAx","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAx","type":"1"}],"DATETIME":"2017-03-16 17:14:43.0","USERID":"宋莹","COUNT":"我是刘建军，你打我啊","DEPT":"凤凰小区-第四网格","ID":"f8856fdc-2a18-477c-bb89-393aaeb3bd29"},{"LOCATION":"你猜我在哪里","IMG":[{"size":"2.24M","name":"IMG_-1568577897.jpg","id":"/v8AMgA4ADUANgAw","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgAw","type":"1"}],"DATETIME":"2017-03-16 17:02:29.0","USERID":"宋莹","COUNT":"测试","DEPT":"凤凰小区-第四网格","ID":"34a2fd01-ab56-4aed-b16a-0d538324c2f1"}]
                 * allnum : 7
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
                     * LOCATION : 中国浙江省湖州市长兴县林荫路
                     * IMG : [{"size":"288.40K","name":"Balance(magazine)-02-2.3.005-bigpicture_02_1.jpg","id":"/v8AMgA4ADUANgA3","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA3","type":"1"}]
                     * DATETIME : 2017-03-16 17:57:35.0
                     * USERID : 宋莹
                     * COUNT : 来吧，说点什么
                     * DEPT : 凤凰小区-第四网格
                     * ID : 42654448-bd64-48db-b4c9-d0750629be90
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
                         * size : 288.40K
                         * name : Balance(magazine)-02-2.3.005-bigpicture_02_1.jpg
                         * id : /v8AMgA4ADUANgA3
                         * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADUANgA3
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
