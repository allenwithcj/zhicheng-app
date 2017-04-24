package com.zhicheng.bean.http;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lwl on 2017/4/16.
 */

public class PersonMsgMaResponse {


    /**
     * iq : {"namespace":"PersonMsgResponse","query":{"prelogcon":{"prelogs":[{"ZZ_RESIDENCE":"居住地址","ID":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","NAME":"曹正PC端测试"},{"ZZ_RESIDENCE":"东百","ID":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","NAME":"王龙"},{"ZZ_RESIDENCE":"阿发","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","NAME":"刘爷"},{"ZZ_RESIDENCE":"地址","ID":"7a08e4c2-9fd8-43f5-a6c4-ad08a12896c4","NAME":"曹正15"},{"ZZ_RESIDENCE":"地址","ID":"28E77685-0B90-4D4A-A26C-534AC1774944","NAME":"曹正呀"},{"ZZ_RESIDENCE":null,"ID":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","NAME":"曹正01"},{"ZZ_RESIDENCE":null,"ID":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","NAME":"曹正"},{"ZZ_RESIDENCE":"123","ID":"520B8CBC-7CCC-BCAF-14A3-896553A23BD3","NAME":"123"},{"ZZ_RESIDENCE":"测试","ID":"9576EAC0-2F32-4141-835D-3CB2CF707BBF","NAME":"测试1123"},{"ZZ_RESIDENCE":"测试","ID":"1C8C7174-3225-62A4-4BBA-4D8E4550D0AC","NAME":"测试66666"}],"allnum":60},"errorCode":"0","errorMessage":"成功"}}
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
         * query : {"prelogcon":{"prelogs":[{"ZZ_RESIDENCE":"居住地址","ID":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","NAME":"曹正PC端测试"},{"ZZ_RESIDENCE":"东百","ID":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","NAME":"王龙"},{"ZZ_RESIDENCE":"阿发","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","NAME":"刘爷"},{"ZZ_RESIDENCE":"地址","ID":"7a08e4c2-9fd8-43f5-a6c4-ad08a12896c4","NAME":"曹正15"},{"ZZ_RESIDENCE":"地址","ID":"28E77685-0B90-4D4A-A26C-534AC1774944","NAME":"曹正呀"},{"ZZ_RESIDENCE":null,"ID":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","NAME":"曹正01"},{"ZZ_RESIDENCE":null,"ID":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","NAME":"曹正"},{"ZZ_RESIDENCE":"123","ID":"520B8CBC-7CCC-BCAF-14A3-896553A23BD3","NAME":"123"},{"ZZ_RESIDENCE":"测试","ID":"9576EAC0-2F32-4141-835D-3CB2CF707BBF","NAME":"测试1123"},{"ZZ_RESIDENCE":"测试","ID":"1C8C7174-3225-62A4-4BBA-4D8E4550D0AC","NAME":"测试66666"}],"allnum":60},"errorCode":"0","errorMessage":"成功"}
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
             * prelogcon : {"prelogs":[{"ZZ_RESIDENCE":"居住地址","ID":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","NAME":"曹正PC端测试"},{"ZZ_RESIDENCE":"东百","ID":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","NAME":"王龙"},{"ZZ_RESIDENCE":"阿发","ID":"8606783E-91A7-B0A5-0A37-4B0DB39E564C","NAME":"刘爷"},{"ZZ_RESIDENCE":"地址","ID":"7a08e4c2-9fd8-43f5-a6c4-ad08a12896c4","NAME":"曹正15"},{"ZZ_RESIDENCE":"地址","ID":"28E77685-0B90-4D4A-A26C-534AC1774944","NAME":"曹正呀"},{"ZZ_RESIDENCE":null,"ID":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","NAME":"曹正01"},{"ZZ_RESIDENCE":null,"ID":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","NAME":"曹正"},{"ZZ_RESIDENCE":"123","ID":"520B8CBC-7CCC-BCAF-14A3-896553A23BD3","NAME":"123"},{"ZZ_RESIDENCE":"测试","ID":"9576EAC0-2F32-4141-835D-3CB2CF707BBF","NAME":"测试1123"},{"ZZ_RESIDENCE":"测试","ID":"1C8C7174-3225-62A4-4BBA-4D8E4550D0AC","NAME":"测试66666"}],"allnum":60}
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

                public static class PrelogsBean implements Parcelable{
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

                    public static final Creator<PrelogsBean> CREATOR = new Creator<PrelogsBean>() {
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
        }
    }
}
