package com.zhicheng.bean.http;

/**
 * Created by lwl on 2017/4/16.
 */

public class PersonMsgResponse {

    /**
     * iq : {"namespace":"PersonMsgResponse","query":{"preMsg":{"DOMICILE":"阿达","RELATION":"1","MARITAL_STATUS":"3","PHONE":"1231","REMARK1":"60-90岁老人","RENTNAME":null,"ZZ_RESIDENCE":"阿发","HOBBY":"乒乓球","HUZU":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","EDUCATION":"16","REMARK2":"吸毒人员","CARD_NUM":"142","OUTADDRESS":"十大","NAME":"刘爷","RENTPHONE":null,"POLITICAL_STATUS":"九三学社社员","GRIDNAME":"2334","SORT":"2","GENDER":"0","WORK":"as","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C"},"errorCode":"0","errorMessage":"成功"}}
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
         * query : {"preMsg":{"DOMICILE":"阿达","RELATION":"1","MARITAL_STATUS":"3","PHONE":"1231","REMARK1":"60-90岁老人","RENTNAME":null,"ZZ_RESIDENCE":"阿发","HOBBY":"乒乓球","HUZU":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","EDUCATION":"16","REMARK2":"吸毒人员","CARD_NUM":"142","OUTADDRESS":"十大","NAME":"刘爷","RENTPHONE":null,"POLITICAL_STATUS":"九三学社社员","GRIDNAME":"2334","SORT":"2","GENDER":"0","WORK":"as","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C"},"errorCode":"0","errorMessage":"成功"}
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
             * preMsg : {"DOMICILE":"阿达","RELATION":"1","MARITAL_STATUS":"3","PHONE":"1231","REMARK1":"60-90岁老人","RENTNAME":null,"ZZ_RESIDENCE":"阿发","HOBBY":"乒乓球","HUZU":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","EDUCATION":"16","REMARK2":"吸毒人员","CARD_NUM":"142","OUTADDRESS":"十大","NAME":"刘爷","RENTPHONE":null,"POLITICAL_STATUS":"九三学社社员","GRIDNAME":"2334","SORT":"2","GENDER":"0","WORK":"as","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C"}
             * errorCode : 0
             * errorMessage : 成功
             */

            private PreMsgBean preMsg;
            private String errorCode;
            private String errorMessage;

            public PreMsgBean getPreMsg() {
                return preMsg;
            }

            public void setPreMsg(PreMsgBean preMsg) {
                this.preMsg = preMsg;
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

            public static class PreMsgBean {
                /**
                 * DOMICILE : 阿达
                 * RELATION : 1
                 * MARITAL_STATUS : 3
                 * PHONE : 1231
                 * REMARK1 : 60-90岁老人
                 * RENTNAME : null
                 * ZZ_RESIDENCE : 阿发
                 * HOBBY : 乒乓球
                 * HUZU : 8606783E-91A7-B0A5-0A37-4B0DB39E564C
                 * EDUCATION : 16
                 * REMARK2 : 吸毒人员
                 * CARD_NUM : 142
                 * OUTADDRESS : 十大
                 * NAME : 刘爷
                 * RENTPHONE : null
                 * POLITICAL_STATUS : 九三学社社员
                 * GRIDNAME : 2334
                 * SORT : 2
                 * GENDER : 0
                 * WORK : as
                 * ID : 8606783E-91A7-B0A5-0A37-4B0DB39E564C
                 */

                private String DOMICILE;
                private String RELATION;
                private String MARITAL_STATUS;
                private String PHONE;
                private String REMARK1;
                private Object RENTNAME;
                private String ZZ_RESIDENCE;
                private String HOBBY;
                private String HUZU;
                private String EDUCATION;
                private String REMARK2;
                private String CARD_NUM;
                private String OUTADDRESS;
                private String NAME;
                private Object RENTPHONE;
                private String POLITICAL_STATUS;
                private String GRIDNAME;
                private String SORT;
                private String GENDER;
                private String WORK;
                private String ID;

                public String getDOMICILE() {
                    return DOMICILE;
                }

                public void setDOMICILE(String DOMICILE) {
                    this.DOMICILE = DOMICILE;
                }

                public String getRELATION() {
                    return RELATION;
                }

                public void setRELATION(String RELATION) {
                    this.RELATION = RELATION;
                }

                public String getMARITAL_STATUS() {
                    return MARITAL_STATUS;
                }

                public void setMARITAL_STATUS(String MARITAL_STATUS) {
                    this.MARITAL_STATUS = MARITAL_STATUS;
                }

                public String getPHONE() {
                    return PHONE;
                }

                public void setPHONE(String PHONE) {
                    this.PHONE = PHONE;
                }

                public String getREMARK1() {
                    return REMARK1;
                }

                public void setREMARK1(String REMARK1) {
                    this.REMARK1 = REMARK1;
                }

                public Object getRENTNAME() {
                    return RENTNAME;
                }

                public void setRENTNAME(Object RENTNAME) {
                    this.RENTNAME = RENTNAME;
                }

                public String getZZ_RESIDENCE() {
                    return ZZ_RESIDENCE;
                }

                public void setZZ_RESIDENCE(String ZZ_RESIDENCE) {
                    this.ZZ_RESIDENCE = ZZ_RESIDENCE;
                }

                public String getHOBBY() {
                    return HOBBY;
                }

                public void setHOBBY(String HOBBY) {
                    this.HOBBY = HOBBY;
                }

                public String getHUZU() {
                    return HUZU;
                }

                public void setHUZU(String HUZU) {
                    this.HUZU = HUZU;
                }

                public String getEDUCATION() {
                    return EDUCATION;
                }

                public void setEDUCATION(String EDUCATION) {
                    this.EDUCATION = EDUCATION;
                }

                public String getREMARK2() {
                    return REMARK2;
                }

                public void setREMARK2(String REMARK2) {
                    this.REMARK2 = REMARK2;
                }

                public String getCARD_NUM() {
                    return CARD_NUM;
                }

                public void setCARD_NUM(String CARD_NUM) {
                    this.CARD_NUM = CARD_NUM;
                }

                public String getOUTADDRESS() {
                    return OUTADDRESS;
                }

                public void setOUTADDRESS(String OUTADDRESS) {
                    this.OUTADDRESS = OUTADDRESS;
                }

                public String getNAME() {
                    return NAME;
                }

                public void setNAME(String NAME) {
                    this.NAME = NAME;
                }

                public Object getRENTPHONE() {
                    return RENTPHONE;
                }

                public void setRENTPHONE(Object RENTPHONE) {
                    this.RENTPHONE = RENTPHONE;
                }

                public String getPOLITICAL_STATUS() {
                    return POLITICAL_STATUS;
                }

                public void setPOLITICAL_STATUS(String POLITICAL_STATUS) {
                    this.POLITICAL_STATUS = POLITICAL_STATUS;
                }

                public String getGRIDNAME() {
                    return GRIDNAME;
                }

                public void setGRIDNAME(String GRIDNAME) {
                    this.GRIDNAME = GRIDNAME;
                }

                public String getSORT() {
                    return SORT;
                }

                public void setSORT(String SORT) {
                    this.SORT = SORT;
                }

                public String getGENDER() {
                    return GENDER;
                }

                public void setGENDER(String GENDER) {
                    this.GENDER = GENDER;
                }

                public String getWORK() {
                    return WORK;
                }

                public void setWORK(String WORK) {
                    this.WORK = WORK;
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
