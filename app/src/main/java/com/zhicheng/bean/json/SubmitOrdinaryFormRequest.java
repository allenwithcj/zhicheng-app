package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/1.
 */

public class SubmitOrdinaryFormRequest {

    private IqBean iq;

    public IqBean getIq() {
        return iq;
    }

    public void setIq(IqBean iq) {
        this.iq = iq;
    }

    public static class IqBean {

        private String namespace;
        private IqBean.QueryBean query;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public IqBean.QueryBean getQuery() {
            return query;
        }

        public void setQuery(IqBean.QueryBean query) {
            this.query = query;
        }

        public static class QueryBean {
            private String type;
            private Formobj formobj;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Formobj getFormobj() {
                return formobj;
            }

            public void setFormobj(Formobj formobj) {
                this.formobj = formobj;
            }

            public static class Formobj {
                private String ID;
                private String CARD_NUM;
                private String NAME;
                private String RELATION;
                private String GENDER;
                private String POLITICAL_STATUS;
                private String EDUCATION;
                private String MARITAL_STATUS;
                private String DOMICILE;
                private String ZZ_RESIDENCE;
                private String PHONE;
                private String WORK;
                private String HOBBY;
                private String GRIDNAME;
                private String REMARK1;
                private String REMARK2;
                private String OUTADDRESS;
                private String RENTNAME;
                private String RENTPHONE;
                private String HUZU;
                private String SORT;

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public String getCARD_NUM() {
                    return CARD_NUM;
                }

                public void setCARD_NUM(String CARD_NUM) {
                    this.CARD_NUM = CARD_NUM;
                }

                public String getNAME() {
                    return NAME;
                }

                public void setNAME(String NAME) {
                    this.NAME = NAME;
                }

                public String getRELATION() {
                    return RELATION;
                }

                public void setRELATION(String RELATION) {
                    this.RELATION = RELATION;
                }

                public String getGENDER() {
                    return GENDER;
                }

                public void setGENDER(String GENDER) {
                    this.GENDER = GENDER;
                }

                public String getPOLITICAL_STATUS() {
                    return POLITICAL_STATUS;
                }

                public void setPOLITICAL_STATUS(String POLITICAL_STATUS) {
                    this.POLITICAL_STATUS = POLITICAL_STATUS;
                }

                public String getEDUCATION() {
                    return EDUCATION;
                }

                public void setEDUCATION(String EDUCATION) {
                    this.EDUCATION = EDUCATION;
                }

                public String getMARITAL_STATUS() {
                    return MARITAL_STATUS;
                }

                public void setMARITAL_STATUS(String MARITAL_STATUS) {
                    this.MARITAL_STATUS = MARITAL_STATUS;
                }

                public String getDOMICILE() {
                    return DOMICILE;
                }

                public void setDOMICILE(String DOMICILE) {
                    this.DOMICILE = DOMICILE;
                }

                public String getZZ_RESIDENCE() {
                    return ZZ_RESIDENCE;
                }

                public void setZZ_RESIDENCE(String ZZ_RESIDENCE) {
                    this.ZZ_RESIDENCE = ZZ_RESIDENCE;
                }

                public String getPHONE() {
                    return PHONE;
                }

                public void setPHONE(String PHONE) {
                    this.PHONE = PHONE;
                }

                public String getWORK() {
                    return WORK;
                }

                public void setWORK(String WORK) {
                    this.WORK = WORK;
                }

                public String getHOBBY() {
                    return HOBBY;
                }

                public void setHOBBY(String HOBBY) {
                    this.HOBBY = HOBBY;
                }

                public String getGRIDNAME() {
                    return GRIDNAME;
                }

                public void setGRIDNAME(String GRIDNAME) {
                    this.GRIDNAME = GRIDNAME;
                }

                public String getREMARK1() {
                    return REMARK1;
                }

                public void setREMARK1(String REMARK1) {
                    this.REMARK1 = REMARK1;
                }

                public String getREMARK2() {
                    return REMARK2;
                }

                public void setREMARK2(String REMARK2) {
                    this.REMARK2 = REMARK2;
                }

                public String getOUTADDRESS() {
                    return OUTADDRESS;
                }

                public void setOUTADDRESS(String OUTADDRESS) {
                    this.OUTADDRESS = OUTADDRESS;
                }

                public String getRENTNAME() {
                    return RENTNAME;
                }

                public void setRENTNAME(String RENTNAME) {
                    this.RENTNAME = RENTNAME;
                }

                public String getRENTPHONE() {
                    return RENTPHONE;
                }

                public void setRENTPHONE(String RENTPHONE) {
                    this.RENTPHONE = RENTPHONE;
                }

                public String getHUZU() {
                    return HUZU;
                }

                public void setHUZU(String HUZU) {
                    this.HUZU = HUZU;
                }

                public String getSORT() {
                    return SORT;
                }

                public void setSORT(String SORT) {
                    this.SORT = SORT;
                }
            }
        }
    }
}