package com.zhicheng.bean.json;

/**
 * Created by Donson on 2017/2/17.
 */

public class UpFileRequest {

    /**
     * iq : {"query":{"attachmentGUID":"F05902F3-EDF7-4BCF-BE01-B594FFAA0239"},"model":"0","namespace":"AttachmentUpdateRequest"}
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
         * query : {"attachmentGUID":"F05902F3-EDF7-4BCF-BE01-B594FFAA0239"}
         * model : 0
         * namespace : AttachmentUpdateRequest
         */

        private QueryBean query;
        private String model;
        private String namespace;

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public static class QueryBean {
            /**
             * attachmentGUID : F05902F3-EDF7-4BCF-BE01-B594FFAA0239
             */

            private String attachmentGUID;

            public String getAttachmentGUID() {
                return attachmentGUID;
            }

            public void setAttachmentGUID(String attachmentGUID) {
                this.attachmentGUID = attachmentGUID;
            }
        }
    }
}
