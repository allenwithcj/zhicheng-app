package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/15.
 */

public class CaseQueryResponse {

    /**
     * iq : {"query":{"caselistcon":{"allcasenum":36,"cases":[{"casenum":"YPT20170222002","caseaddress":"32132131","id":"122","casetime":"2017-02-22 15:22:00.0"},{"casenum":"YPT20170223023","caseaddress":"004","id":"147","casetime":"2017-02-23 19:10:00.0"},{"casenum":"YPT20170225001","caseaddress":"hjkhkjhkj","id":"181","casetime":"2017-02-25 01:08:07.0"},{"casenum":"YPT20170225004","caseaddress":"&hjdjdjjdldkcjjdkdndkdmd","id":"184","casetime":"2017-02-25 14:18:31.0"},{"casenum":"YPT20170308002","caseaddress":"测试数据","id":"223","casetime":"2017-03-08 13:29:39.0"},{"casenum":"YPT20170308004","caseaddress":"测试数据爆料001","id":"225","casetime":"2017-03-08 13:52:28.0"},{"casenum":"YPT20170308005","caseaddress":"测试001","id":"226","casetime":"2017-03-08 15:16:51.0"},{"casenum":"YPT20170308006","caseaddress":"测试如图000","id":"227","casetime":"2017-03-08 15:21:44.0"},{"casenum":"YPT20170313001","caseaddress":"测试111111-3.13号","id":"249","casetime":"2017-03-13 08:58:09.0"},{"casenum":"YPT20170313002","caseaddress":"测试111","id":"250","casetime":"2017-03-13 09:03:30.0"}]},"errorMessage":"成功","errorCode":0},"namespace":"CaseQueryResponse"}
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
         * query : {"caselistcon":{"allcasenum":36,"cases":[{"casenum":"YPT20170222002","caseaddress":"32132131","id":"122","casetime":"2017-02-22 15:22:00.0"},{"casenum":"YPT20170223023","caseaddress":"004","id":"147","casetime":"2017-02-23 19:10:00.0"},{"casenum":"YPT20170225001","caseaddress":"hjkhkjhkj","id":"181","casetime":"2017-02-25 01:08:07.0"},{"casenum":"YPT20170225004","caseaddress":"&hjdjdjjdldkcjjdkdndkdmd","id":"184","casetime":"2017-02-25 14:18:31.0"},{"casenum":"YPT20170308002","caseaddress":"测试数据","id":"223","casetime":"2017-03-08 13:29:39.0"},{"casenum":"YPT20170308004","caseaddress":"测试数据爆料001","id":"225","casetime":"2017-03-08 13:52:28.0"},{"casenum":"YPT20170308005","caseaddress":"测试001","id":"226","casetime":"2017-03-08 15:16:51.0"},{"casenum":"YPT20170308006","caseaddress":"测试如图000","id":"227","casetime":"2017-03-08 15:21:44.0"},{"casenum":"YPT20170313001","caseaddress":"测试111111-3.13号","id":"249","casetime":"2017-03-13 08:58:09.0"},{"casenum":"YPT20170313002","caseaddress":"测试111","id":"250","casetime":"2017-03-13 09:03:30.0"}]},"errorMessage":"成功","errorCode":0}
         * namespace : CaseQueryResponse
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
             * caselistcon : {"allcasenum":36,"cases":[{"casenum":"YPT20170222002","caseaddress":"32132131","id":"122","casetime":"2017-02-22 15:22:00.0"},{"casenum":"YPT20170223023","caseaddress":"004","id":"147","casetime":"2017-02-23 19:10:00.0"},{"casenum":"YPT20170225001","caseaddress":"hjkhkjhkj","id":"181","casetime":"2017-02-25 01:08:07.0"},{"casenum":"YPT20170225004","caseaddress":"&hjdjdjjdldkcjjdkdndkdmd","id":"184","casetime":"2017-02-25 14:18:31.0"},{"casenum":"YPT20170308002","caseaddress":"测试数据","id":"223","casetime":"2017-03-08 13:29:39.0"},{"casenum":"YPT20170308004","caseaddress":"测试数据爆料001","id":"225","casetime":"2017-03-08 13:52:28.0"},{"casenum":"YPT20170308005","caseaddress":"测试001","id":"226","casetime":"2017-03-08 15:16:51.0"},{"casenum":"YPT20170308006","caseaddress":"测试如图000","id":"227","casetime":"2017-03-08 15:21:44.0"},{"casenum":"YPT20170313001","caseaddress":"测试111111-3.13号","id":"249","casetime":"2017-03-13 08:58:09.0"},{"casenum":"YPT20170313002","caseaddress":"测试111","id":"250","casetime":"2017-03-13 09:03:30.0"}]}
             * errorMessage : 成功
             * errorCode : 0
             */

            private CaselistconBean caselistcon;
            private String errorMessage;
            private int errorCode;

            public CaselistconBean getCaselistcon() {
                return caselistcon;
            }

            public void setCaselistcon(CaselistconBean caselistcon) {
                this.caselistcon = caselistcon;
            }

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

            public static class CaselistconBean {
                /**
                 * allcasenum : 36
                 * cases : [{"casenum":"YPT20170222002","caseaddress":"32132131","id":"122","casetime":"2017-02-22 15:22:00.0"},{"casenum":"YPT20170223023","caseaddress":"004","id":"147","casetime":"2017-02-23 19:10:00.0"},{"casenum":"YPT20170225001","caseaddress":"hjkhkjhkj","id":"181","casetime":"2017-02-25 01:08:07.0"},{"casenum":"YPT20170225004","caseaddress":"&hjdjdjjdldkcjjdkdndkdmd","id":"184","casetime":"2017-02-25 14:18:31.0"},{"casenum":"YPT20170308002","caseaddress":"测试数据","id":"223","casetime":"2017-03-08 13:29:39.0"},{"casenum":"YPT20170308004","caseaddress":"测试数据爆料001","id":"225","casetime":"2017-03-08 13:52:28.0"},{"casenum":"YPT20170308005","caseaddress":"测试001","id":"226","casetime":"2017-03-08 15:16:51.0"},{"casenum":"YPT20170308006","caseaddress":"测试如图000","id":"227","casetime":"2017-03-08 15:21:44.0"},{"casenum":"YPT20170313001","caseaddress":"测试111111-3.13号","id":"249","casetime":"2017-03-13 08:58:09.0"},{"casenum":"YPT20170313002","caseaddress":"测试111","id":"250","casetime":"2017-03-13 09:03:30.0"}]
                 */

                private int allcasenum;
                private List<CasesBean> cases;
                private int ztal;
                private int btal;
                private int gtal;

                public int getAllcasenum() {
                    return allcasenum;
                }

                public void setAllcasenum(int allcasenum) {
                    this.allcasenum = allcasenum;
                }

                public int getZtal() {
                    return ztal;
                }

                public void setZtal(int ztal) {
                    this.ztal = ztal;
                }

                public int getBtal() {
                    return btal;
                }

                public void setBtal(int btal) {
                    this.btal = btal;
                }

                public int getGtal() {
                    return gtal;
                }

                public void setGtal(int gtal) {
                    this.gtal = gtal;
                }

                public List<CasesBean> getCases() {
                    return cases;
                }

                public void setCases(List<CasesBean> cases) {
                    this.cases = cases;
                }

                public static class CasesBean {
                    /**
                     * casenum : YPT20170222002
                     * caseaddress : 32132131
                     * id : 122
                     * casetime : 2017-02-22 15:22:00.0
                     */

                    private String casenum;
                    private String caseaddress;
                    private String id;
                    private String casetime;
                    private String casetype;

                    public String getCasenum() {
                        return casenum;
                    }

                    public void setCasenum(String casenum) {
                        this.casenum = casenum;
                    }

                    public String getCaseaddress() {
                        return caseaddress;
                    }

                    public void setCaseaddress(String caseaddress) {
                        this.caseaddress = caseaddress;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getCasetime() {
                        return casetime;
                    }

                    public void setCasetime(String casetime) {
                        this.casetime = casetime;
                    }

                    public String getCasetype() {
                        return casetype;
                    }

                    public void setCasetype(String casetype) {
                        this.casetype = casetype;
                    }
                }
            }
        }
    }
}
