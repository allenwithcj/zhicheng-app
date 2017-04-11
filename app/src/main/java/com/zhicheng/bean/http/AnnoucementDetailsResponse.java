package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/18.
 */

public class AnnoucementDetailsResponse {


    /**
     * iq : {"namespace":"AnnoucementDetailsResponse","query":{"id":"127","title":"战略计划","sendUser":"黄燕","sendUserID":"7677","sendUserImageHref":"/UserUploadFile/photo/photo.png","sendTime":"2017-03-17 21:35","content":"<p>战略计划<br/><\/p>","readNums":"3","totalNums":"195","attachments":[{"id":"/v8AMgA4ADcAMAA3","name":"2017年雉城街道社会治理攻坚战实施计划.docx","size":"16.64K","type":"4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcAMAA3"}],"annex":"40131885-8044-52B3-A625-6D0B50541F57","errorCode":"0","errorMessage":""}}
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
         * namespace : AnnoucementDetailsResponse
         * query : {"id":"127","title":"战略计划","sendUser":"黄燕","sendUserID":"7677","sendUserImageHref":"/UserUploadFile/photo/photo.png","sendTime":"2017-03-17 21:35","content":"<p>战略计划<br/><\/p>","readNums":"3","totalNums":"195","attachments":[{"id":"/v8AMgA4ADcAMAA3","name":"2017年雉城街道社会治理攻坚战实施计划.docx","size":"16.64K","type":"4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcAMAA3"}],"annex":"40131885-8044-52B3-A625-6D0B50541F57","errorCode":"0","errorMessage":""}
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
             * id : 127
             * title : 战略计划
             * sendUser : 黄燕
             * sendUserID : 7677
             * sendUserImageHref : /UserUploadFile/photo/photo.png
             * sendTime : 2017-03-17 21:35
             * content : <p>战略计划<br/></p>
             * readNums : 3
             * totalNums : 195
             * attachments : [{"id":"/v8AMgA4ADcAMAA3","name":"2017年雉城街道社会治理攻坚战实施计划.docx","size":"16.64K","type":"4","href":"/servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcAMAA3"}]
             * annex : 40131885-8044-52B3-A625-6D0B50541F57
             * errorCode : 0
             * errorMessage :
             */

            private String id;
            private String title;
            private String sendUser;
            private String sendUserID;
            private String sendUserImageHref;
            private String sendTime;
            private String content;
            private String readNums;
            private String totalNums;
            private String annex;
            private String errorCode;
            private String errorMessage;
            private List<AttachmentsBean> attachments;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSendUser() {
                return sendUser;
            }

            public void setSendUser(String sendUser) {
                this.sendUser = sendUser;
            }

            public String getSendUserID() {
                return sendUserID;
            }

            public void setSendUserID(String sendUserID) {
                this.sendUserID = sendUserID;
            }

            public String getSendUserImageHref() {
                return sendUserImageHref;
            }

            public void setSendUserImageHref(String sendUserImageHref) {
                this.sendUserImageHref = sendUserImageHref;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getReadNums() {
                return readNums;
            }

            public void setReadNums(String readNums) {
                this.readNums = readNums;
            }

            public String getTotalNums() {
                return totalNums;
            }

            public void setTotalNums(String totalNums) {
                this.totalNums = totalNums;
            }

            public String getAnnex() {
                return annex;
            }

            public void setAnnex(String annex) {
                this.annex = annex;
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

            public List<AttachmentsBean> getAttachments() {
                return attachments;
            }

            public void setAttachments(List<AttachmentsBean> attachments) {
                this.attachments = attachments;
            }

            public static class AttachmentsBean {
                /**
                 * id : /v8AMgA4ADcAMAA3
                 * name : 2017年雉城街道社会治理攻坚战实施计划.docx
                 * size : 16.64K
                 * type : 4
                 * href : /servlet/mobileAttachmentServlet?type=0&attachPK=%2Fv8AMgA4ADcAMAA3
                 */

                private String id;
                private String name;
                private String size;
                private String type;
                private String href;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
        }
    }
}
