package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialQueyResponse {

    /**
     * iq : {"namespace":"PersonMsgResponse","query":{"preMsgcon":{"preMsgs":[{"DOMICILE":"nxncmc","ID":"4b67265a-bd12-4941-9b3a-7bd135fed5a0","Name":"bcnc"}],"allnum":1},"errorCode":"0","errorMessage":"成功"}}
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
         * namespace : PersonMsgResponse
         * query : {"preMsgcon":{"preMsgs":[{"DOMICILE":"nxncmc","ID":"4b67265a-bd12-4941-9b3a-7bd135fed5a0","Name":"bcnc"}],"allnum":1},"errorCode":"0","errorMessage":"成功"}
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
             * preMsgcon : {"preMsgs":[{"DOMICILE":"nxncmc","ID":"4b67265a-bd12-4941-9b3a-7bd135fed5a0","Name":"bcnc"}],"allnum":1}
             * errorCode : 0
             * errorMessage : 成功
             */

            private PreMsgconBean preMsgcon;
            private String errorCode;
            private String errorMessage;

            public PreMsgconBean getPreMsgcon() {
                return preMsgcon;
            }

            public void setPreMsgcon(PreMsgconBean preMsgcon) {
                this.preMsgcon = preMsgcon;
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

            public static class PreMsgconBean {
                /**
                 * preMsgs : [{"DOMICILE":"nxncmc","ID":"4b67265a-bd12-4941-9b3a-7bd135fed5a0","Name":"bcnc"}]
                 * allnum : 1
                 */

                private int allnum;
                private List<PreMsgsBean> preMsgs;

                public int getAllnum() {
                    return allnum;
                }

                public void setAllnum(int allnum) {
                    this.allnum = allnum;
                }

                public List<PreMsgsBean> getPreMsgs() {
                    return preMsgs;
                }

                public void setPreMsgs(List<PreMsgsBean> preMsgs) {
                    this.preMsgs = preMsgs;
                }

                public static class PreMsgsBean {
                    /**
                     * DOMICILE : nxncmc
                     * ID : 4b67265a-bd12-4941-9b3a-7bd135fed5a0
                     * Name : bcnc
                     */

                    private String DOMICILE;
                    private String ID;
                    private String NAME;
                    private String REPORTUSER;

                    public String getDOMICILE() {
                        return DOMICILE;
                    }

                    public void setDOMICILE(String DOMICILE) {
                        this.DOMICILE = DOMICILE;
                    }

                    public String getID() {
                        return ID;
                    }

                    public void setID(String ID) {
                        this.ID = ID;
                    }

                    public String getNAME() {
                        return NAME;
                    }

                    public void setNAME(String NAME) {
                        this.NAME = NAME;
                    }

                    public String getREPORTUSER() {
                        return REPORTUSER;
                    }

                    public void setREPORTUSER(String REPORTUSER) {
                        this.REPORTUSER = REPORTUSER;
                    }
                }
            }
        }
    }
}
