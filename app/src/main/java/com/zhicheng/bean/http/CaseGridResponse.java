package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by lwl on 2017/4/12.
 */

public class CaseGridResponse {


    /**
     * iq : {"query":{"errorMessage":"成功","errorCode":0,"items":[{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区","namestr":"雉城街道/米行弄社区","pId":"158","id":"77"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第一网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第一网格）","pId":"77","id":"78"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第二网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第二网格）","pId":"77","id":"79"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第三网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第三网格）","pId":"77","id":"80"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第四网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第四网格）","pId":"77","id":"81"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第五网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第五网格）","pId":"77","id":"82"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第六网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第六网格）","pId":"77","id":"83"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第七网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第七网格）","pId":"77","id":"84"}]},"namespace":"CaseGridResponse"}
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
         * query : {"errorMessage":"成功","errorCode":0,"items":[{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区","namestr":"雉城街道/米行弄社区","pId":"158","id":"77"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第一网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第一网格）","pId":"77","id":"78"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第二网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第二网格）","pId":"77","id":"79"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第三网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第三网格）","pId":"77","id":"80"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第四网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第四网格）","pId":"77","id":"81"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第五网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第五网格）","pId":"77","id":"82"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第六网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第六网格）","pId":"77","id":"83"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第七网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第七网格）","pId":"77","id":"84"}]}
         * namespace : CaseGridResponse
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
             * errorMessage : 成功
             * errorCode : 0
             * items : [{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区","namestr":"雉城街道/米行弄社区","pId":"158","id":"77"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第一网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第一网格）","pId":"77","id":"78"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第二网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第二网格）","pId":"77","id":"79"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第三网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第三网格）","pId":"77","id":"80"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第四网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第四网格）","pId":"77","id":"81"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第五网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第五网格）","pId":"77","id":"82"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第六网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第六网格）","pId":"77","id":"83"},{"secondname":"米行弄社区","firstname":"雉城街道","thirdname":"米行弄社区（第七网格）","namestr":"雉城街道/米行弄社区/米行弄社区（第七网格）","pId":"77","id":"84"}]
             */

            private String errorMessage;
            private int errorCode;
            private List<ItemsBean> items;

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

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * secondname : 米行弄社区
                 * firstname : 雉城街道
                 * thirdname : 米行弄社区
                 * namestr : 雉城街道/米行弄社区
                 * pId : 158
                 * id : 77
                 */

                private String secondname;
                private String firstname;
                private String thirdname;
                private String namestr;
                private String pId;
                private String id;

                public String getSecondname() {
                    return secondname;
                }

                public void setSecondname(String secondname) {
                    this.secondname = secondname;
                }

                public String getFirstname() {
                    return firstname;
                }

                public void setFirstname(String firstname) {
                    this.firstname = firstname;
                }

                public String getThirdname() {
                    return thirdname;
                }

                public void setThirdname(String thirdname) {
                    this.thirdname = thirdname;
                }

                public String getNamestr() {
                    return namestr;
                }

                public void setNamestr(String namestr) {
                    this.namestr = namestr;
                }

                public String getPId() {
                    return pId;
                }

                public void setPId(String pId) {
                    this.pId = pId;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }
}
