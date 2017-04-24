package com.zhicheng.bean.Extend.response;

import java.util.List;

/**
 * Created by Donson on 2017/4/22.
 */

public class PersonQueryResponse {

    /**
     * iq : {"query":{"errorMessage":"成功","errorCode":0,"personlistcon":{"prelogs":[{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"}],"allnum":1}},"namespace":"PersonQueryResponse"}
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
         * query : {"errorMessage":"成功","errorCode":0,"personlistcon":{"prelogs":[{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"}],"allnum":1}}
         * namespace : PersonQueryResponse
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
             * personlistcon : {"prelogs":[{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"}],"allnum":1}
             */

            private String errorMessage;
            private int errorCode;
            private PersonlistconBean personlistcon;

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

            public PersonlistconBean getPersonlistcon() {
                return personlistcon;
            }

            public void setPersonlistcon(PersonlistconBean personlistcon) {
                this.personlistcon = personlistcon;
            }

            public static class PersonlistconBean {
                /**
                 * prelogs : [{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"}]
                 * allnum : 1
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
                     * DOMICILE : 12431234123
                     * REPORTUSER : 7675
                     * ZZ_RESIDENCE : 123412
                     * HUZU : a53a8ace-100c-44c1-a2f6-38e0c01f1e97
                     * ID : eba3daf2-d752-4526-81a3-fe8879e1aacb
                     * NAME : 1234123412
                     */

                    private String DOMICILE;
                    private String REPORTUSER;
                    private String ZZ_RESIDENCE;
                    private String HUZU;
                    private String ID;
                    private String NAME;

                    public String getDOMICILE() {
                        return DOMICILE;
                    }

                    public void setDOMICILE(String DOMICILE) {
                        this.DOMICILE = DOMICILE;
                    }

                    public String getREPORTUSER() {
                        return REPORTUSER;
                    }

                    public void setREPORTUSER(String REPORTUSER) {
                        this.REPORTUSER = REPORTUSER;
                    }

                    public String getZZ_RESIDENCE() {
                        return ZZ_RESIDENCE;
                    }

                    public void setZZ_RESIDENCE(String ZZ_RESIDENCE) {
                        this.ZZ_RESIDENCE = ZZ_RESIDENCE;
                    }

                    public String getHUZU() {
                        return HUZU;
                    }

                    public void setHUZU(String HUZU) {
                        this.HUZU = HUZU;
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
                }
            }
        }
    }
}
