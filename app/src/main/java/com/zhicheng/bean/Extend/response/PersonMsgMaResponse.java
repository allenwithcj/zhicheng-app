package com.zhicheng.bean.Extend.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Donson on 2017/4/22.
 */

public class PersonMsgMaResponse {

    /**
     * iq : {"namespace":"PersonMsgResponse","query":{"preMsgcon":{"preMsgs":[{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"},{"DOMICILE":"浙江","REPORTUSER":"7675","ZZ_RESIDENCE":"hdh","HUZU":"8c8a8590-56c0-4364-9e9d-686615569cbf","ID":"5cd8b4d5-1b7b-43fb-89c4-c2188e163280","NAME":"jdi"},{"DOMICILE":"长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","NAME":"欧阳永轩"},{"DOMICILE":"广州","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"BD4C0343-573E-4785-A930-DA39B8AD9B9E","NAME":"欧阳楠楠"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","ID":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","NAME":"王五"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"F80C4DA3-D66A-40F1-981C-7520465A166F","ID":"F80C4DA3-D66A-40F1-981C-7520465A166F","NAME":"王五"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","ID":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","NAME":"李四"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"34A276B7-EEFD-45F6-BF86-0378B57451D4","ID":"34A276B7-EEFD-45F6-BF86-0378B57451D4","NAME":"李四"},{"DOMICILE":"浙江长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"浙江长兴","HUZU":"eede6d27-c064-4181-a27d-37bb64416ecb","ID":"eede6d27-c064-4181-a27d-37bb64416ecb","NAME":"王丽"}],"allnum":9},"errorCode":"0","errorMessage":"成功"}}
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
         * query : {"preMsgcon":{"preMsgs":[{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"},{"DOMICILE":"浙江","REPORTUSER":"7675","ZZ_RESIDENCE":"hdh","HUZU":"8c8a8590-56c0-4364-9e9d-686615569cbf","ID":"5cd8b4d5-1b7b-43fb-89c4-c2188e163280","NAME":"jdi"},{"DOMICILE":"长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","NAME":"欧阳永轩"},{"DOMICILE":"广州","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"BD4C0343-573E-4785-A930-DA39B8AD9B9E","NAME":"欧阳楠楠"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","ID":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","NAME":"王五"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"F80C4DA3-D66A-40F1-981C-7520465A166F","ID":"F80C4DA3-D66A-40F1-981C-7520465A166F","NAME":"王五"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","ID":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","NAME":"李四"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"34A276B7-EEFD-45F6-BF86-0378B57451D4","ID":"34A276B7-EEFD-45F6-BF86-0378B57451D4","NAME":"李四"},{"DOMICILE":"浙江长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"浙江长兴","HUZU":"eede6d27-c064-4181-a27d-37bb64416ecb","ID":"eede6d27-c064-4181-a27d-37bb64416ecb","NAME":"王丽"}],"allnum":9},"errorCode":"0","errorMessage":"成功"}
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
             * preMsgcon : {"preMsgs":[{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"},{"DOMICILE":"浙江","REPORTUSER":"7675","ZZ_RESIDENCE":"hdh","HUZU":"8c8a8590-56c0-4364-9e9d-686615569cbf","ID":"5cd8b4d5-1b7b-43fb-89c4-c2188e163280","NAME":"jdi"},{"DOMICILE":"长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","NAME":"欧阳永轩"},{"DOMICILE":"广州","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"BD4C0343-573E-4785-A930-DA39B8AD9B9E","NAME":"欧阳楠楠"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","ID":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","NAME":"王五"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"F80C4DA3-D66A-40F1-981C-7520465A166F","ID":"F80C4DA3-D66A-40F1-981C-7520465A166F","NAME":"王五"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","ID":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","NAME":"李四"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"34A276B7-EEFD-45F6-BF86-0378B57451D4","ID":"34A276B7-EEFD-45F6-BF86-0378B57451D4","NAME":"李四"},{"DOMICILE":"浙江长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"浙江长兴","HUZU":"eede6d27-c064-4181-a27d-37bb64416ecb","ID":"eede6d27-c064-4181-a27d-37bb64416ecb","NAME":"王丽"}],"allnum":9}
             * errorCode : 0
             * errorMessage : 成功
             */
            private PrelogconBean prelogcon;
            private PreMsgconBean preMsgcon;
            private PersonlistconBean personlistcon;
            private String errorCode;
            private String errorMessage;

            public PrelogconBean getPrelogcon() {
                return prelogcon;
            }

            public void setPrelogcon(PrelogconBean prelogcon) {
                this.prelogcon = prelogcon;
            }

            public PreMsgconBean getPreMsgcon() {
                return preMsgcon;
            }

            public void setPreMsgcon(PreMsgconBean preMsgcon) {
                this.preMsgcon = preMsgcon;
            }

            public PersonlistconBean getPersonlistcon() {
                return personlistcon;
            }

            public void setPersonlistcon(PersonlistconBean personlistcon) {
                this.personlistcon = personlistcon;
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

            public static class PersonlistconBean {
                /**
                 * prelogs : [{"DOMICILE":"提XP一无所有荣耀","ZZ_RESIDENCE":"武汉","HUZU":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","ID":"b2b2ce29-1c02-4c80-9a6e-dc33b393bee2","NAME":"今年是"},{"DOMICILE":"今日我","ZZ_RESIDENCE":"湖北","HUZU":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","ID":"a26a8521-616b-4e44-97ce-b0eaac1b912e","NAME":"jy"},{"DOMICILE":"mxkc","ZZ_RESIDENCE":"22","HUZU":"90616332-ED39-45B2-BED8-EF551261C085","ID":"3e865ad2-0d5d-4fd7-a1ac-656cd1fc1804","NAME":"11"},{"DOMICILE":"22","ZZ_RESIDENCE":"111","HUZU":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","ID":"fa1c7dea-0a19-430e-8bd9-50097b53df92","NAME":"11"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"湖州","HUZU":"906894FD-786E-4A09-AAC3-7EA8E50FB278","ID":"e480b282-8049-46e8-9376-42102a1fcd1f","NAME":"建军"},{"DOMICILE":"xjmxmx","ZZ_RESIDENCE":"123","HUZU":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","ID":"b79c9845-a0cb-472e-b484-c18371aa160c","NAME":"qqwer"},{"DOMICILE":"ndnndnfnmvmcm","ZZ_RESIDENCE":"jjjj","HUZU":"28E77685-0B90-4D4A-A26C-534AC1774944","ID":"f76d8fba-7a6f-4167-aa5c-f941cdda555f","NAME":"hshd"},{"DOMICILE":"以身相许需要","ZZ_RESIDENCE":"武汉","HUZU":"理发","ID":"4d7a1761-8a92-4b65-86ed-6be0b8a086a9","NAME":"建军刘"},{"DOMICILE":"破庸人自扰之","ZZ_RESIDENCE":"你之前","HUZU":"尼玛1","ID":"1168dcb9-aa92-42da-93e1-cdeaf66e0d9e","NAME":"尼玛1"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"安徽","HUZU":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","ID":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","NAME":"刘建军"}]
                 * allnum : 14
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
                     * DOMICILE : 提XP一无所有荣耀
                     * ZZ_RESIDENCE : 武汉
                     * HUZU : 8b26f0d1-c987-4601-bc72-9336cc5cbdba
                     * ID : b2b2ce29-1c02-4c80-9a6e-dc33b393bee2
                     * NAME : 今年是
                     */

                    private String DOMICILE;
                    private String ZZ_RESIDENCE;
                    private String HUZU;
                    private String ID;
                    private String NAME;
                    private String REPORTUSER;

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

                    public String getREPORTUSER() {
                        return REPORTUSER;
                    }

                    public void setREPORTUSER(String REPORTUSER) {
                        this.REPORTUSER = REPORTUSER;
                    }
                }
            }

            public static class PrelogconBean {
                /**
                 * prelogs : [{"ZZ_RESIDENCE":"居住地址","ID":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","NAME":"曹正PC端测试"},{"ZZ_RESIDENCE":"东百","ID":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","NAME":"王龙"},{"ZZ_RESIDENCE":"阿发","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","NAME":"刘爷"},{"ZZ_RESIDENCE":"地址","ID":"7a08e4c2-9fd8-43f5-a6c4-ad08a12896c4","NAME":"曹正15"},{"ZZ_RESIDENCE":"地址","ID":"28E77685-0B90-4D4A-A26C-534AC1774944","NAME":"曹正呀"},{"ZZ_RESIDENCE":null,"ID":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","NAME":"曹正01"},{"ZZ_RESIDENCE":null,"ID":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","NAME":"曹正"},{"ZZ_RESIDENCE":"123","ID":"520B8CBC-7CCC-BCAF-14A3-896553A23BD3","NAME":"123"},{"ZZ_RESIDENCE":"测试","ID":"9576EAC0-2F32-4141-835D-3CB2CF707BBF","NAME":"测试1123"},{"ZZ_RESIDENCE":"测试","ID":"1C8C7174-3225-62A4-4BBA-4D8E4550D0AC","NAME":"测试66666"}]
                 * allnum : 60
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

                public static class PrelogsBean implements Parcelable {
                    /**
                     * ZZ_RESIDENCE : 居住地址
                     * ID : D13CE00D-2168-3DD3-23DA-551FA0011AC5
                     * NAME : 曹正PC端测试
                     */

                    private String ZZ_RESIDENCE;
                    private String ID;
                    private String NAME;
                    private String HZNAME;

                    protected PrelogsBean(Parcel in) {
                        ZZ_RESIDENCE = in.readString();
                        ID = in.readString();
                        NAME = in.readString();
                        HZNAME = in.readString();
                    }

                    public static final Parcelable.Creator<PrelogsBean> CREATOR = new Creator<PrelogsBean>() {
                        @Override
                        public PrelogsBean createFromParcel(Parcel in) {
                            return new PrelogsBean(in);
                        }

                        @Override
                        public PrelogsBean[] newArray(int size) {
                            return new PrelogsBean[size];
                        }
                    };

                    public String getZZ_RESIDENCE() {
                        return ZZ_RESIDENCE;
                    }

                    public void setZZ_RESIDENCE(String ZZ_RESIDENCE) {
                        this.ZZ_RESIDENCE = ZZ_RESIDENCE;
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

                    public String getHZNAME() {
                        return HZNAME;
                    }

                    public void setHZNAME(String HZNAME) {
                        this.HZNAME = HZNAME;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel parcel, int i) {
                        parcel.writeString(ZZ_RESIDENCE);
                        parcel.writeString(ID);
                        parcel.writeString(NAME);
                        parcel.writeString(HZNAME);
                    }
                }
            }

            public static class PreMsgconBean {
                /**
                 * preMsgs : [{"DOMICILE":"12431234123","REPORTUSER":"7675","ZZ_RESIDENCE":"123412","HUZU":"a53a8ace-100c-44c1-a2f6-38e0c01f1e97","ID":"eba3daf2-d752-4526-81a3-fe8879e1aacb","NAME":"1234123412"},{"DOMICILE":"浙江","REPORTUSER":"7675","ZZ_RESIDENCE":"hdh","HUZU":"8c8a8590-56c0-4364-9e9d-686615569cbf","ID":"5cd8b4d5-1b7b-43fb-89c4-c2188e163280","NAME":"jdi"},{"DOMICILE":"长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","NAME":"欧阳永轩"},{"DOMICILE":"广州","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"43F67858-A8A8-4CF8-98B4-9E5B87DDA0E4","ID":"BD4C0343-573E-4785-A930-DA39B8AD9B9E","NAME":"欧阳楠楠"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","ID":"B6A455B8-C9BF-4257-8FF7-ADDD0E31814C","NAME":"王五"},{"DOMICILE":"北京","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"F80C4DA3-D66A-40F1-981C-7520465A166F","ID":"F80C4DA3-D66A-40F1-981C-7520465A166F","NAME":"王五"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","ID":"CF6FCC77-05E4-449B-85CA-B3ED3FD7A81B","NAME":"李四"},{"DOMICILE":"长沙","REPORTUSER":"7675","ZZ_RESIDENCE":"长兴","HUZU":"34A276B7-EEFD-45F6-BF86-0378B57451D4","ID":"34A276B7-EEFD-45F6-BF86-0378B57451D4","NAME":"李四"},{"DOMICILE":"浙江长兴","REPORTUSER":"7675","ZZ_RESIDENCE":"浙江长兴","HUZU":"eede6d27-c064-4181-a27d-37bb64416ecb","ID":"eede6d27-c064-4181-a27d-37bb64416ecb","NAME":"王丽"}]
                 * allnum : 9
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
