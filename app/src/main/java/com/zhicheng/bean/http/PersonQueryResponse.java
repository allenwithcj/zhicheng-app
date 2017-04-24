package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by lwl on 2017/4/18.
 */

public class PersonQueryResponse {


    /**
     * iq : {"query":{"errorMessage":"成功","errorCode":0,"personlistcon":{"prelogs":[{"DOMICILE":"提XP一无所有荣耀","ZZ_RESIDENCE":"武汉","HUZU":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","ID":"b2b2ce29-1c02-4c80-9a6e-dc33b393bee2","NAME":"今年是"},{"DOMICILE":"今日我","ZZ_RESIDENCE":"湖北","HUZU":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","ID":"a26a8521-616b-4e44-97ce-b0eaac1b912e","NAME":"jy"},{"DOMICILE":"mxkc","ZZ_RESIDENCE":"22","HUZU":"90616332-ED39-45B2-BED8-EF551261C085","ID":"3e865ad2-0d5d-4fd7-a1ac-656cd1fc1804","NAME":"11"},{"DOMICILE":"22","ZZ_RESIDENCE":"111","HUZU":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","ID":"fa1c7dea-0a19-430e-8bd9-50097b53df92","NAME":"11"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"湖州","HUZU":"906894FD-786E-4A09-AAC3-7EA8E50FB278","ID":"e480b282-8049-46e8-9376-42102a1fcd1f","NAME":"建军"},{"DOMICILE":"xjmxmx","ZZ_RESIDENCE":"123","HUZU":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","ID":"b79c9845-a0cb-472e-b484-c18371aa160c","NAME":"qqwer"},{"DOMICILE":"ndnndnfnmvmcm","ZZ_RESIDENCE":"jjjj","HUZU":"28E77685-0B90-4D4A-A26C-534AC1774944","ID":"f76d8fba-7a6f-4167-aa5c-f941cdda555f","NAME":"hshd"},{"DOMICILE":"以身相许需要","ZZ_RESIDENCE":"武汉","HUZU":"理发","ID":"4d7a1761-8a92-4b65-86ed-6be0b8a086a9","NAME":"建军刘"},{"DOMICILE":"破庸人自扰之","ZZ_RESIDENCE":"你之前","HUZU":"尼玛1","ID":"1168dcb9-aa92-42da-93e1-cdeaf66e0d9e","NAME":"尼玛1"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"安徽","HUZU":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","ID":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","NAME":"刘建军"}],"allnum":14}},"namespace":"PersonQueryResponse"}
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
         * query : {"errorMessage":"成功","errorCode":0,"personlistcon":{"prelogs":[{"DOMICILE":"提XP一无所有荣耀","ZZ_RESIDENCE":"武汉","HUZU":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","ID":"b2b2ce29-1c02-4c80-9a6e-dc33b393bee2","NAME":"今年是"},{"DOMICILE":"今日我","ZZ_RESIDENCE":"湖北","HUZU":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","ID":"a26a8521-616b-4e44-97ce-b0eaac1b912e","NAME":"jy"},{"DOMICILE":"mxkc","ZZ_RESIDENCE":"22","HUZU":"90616332-ED39-45B2-BED8-EF551261C085","ID":"3e865ad2-0d5d-4fd7-a1ac-656cd1fc1804","NAME":"11"},{"DOMICILE":"22","ZZ_RESIDENCE":"111","HUZU":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","ID":"fa1c7dea-0a19-430e-8bd9-50097b53df92","NAME":"11"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"湖州","HUZU":"906894FD-786E-4A09-AAC3-7EA8E50FB278","ID":"e480b282-8049-46e8-9376-42102a1fcd1f","NAME":"建军"},{"DOMICILE":"xjmxmx","ZZ_RESIDENCE":"123","HUZU":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","ID":"b79c9845-a0cb-472e-b484-c18371aa160c","NAME":"qqwer"},{"DOMICILE":"ndnndnfnmvmcm","ZZ_RESIDENCE":"jjjj","HUZU":"28E77685-0B90-4D4A-A26C-534AC1774944","ID":"f76d8fba-7a6f-4167-aa5c-f941cdda555f","NAME":"hshd"},{"DOMICILE":"以身相许需要","ZZ_RESIDENCE":"武汉","HUZU":"理发","ID":"4d7a1761-8a92-4b65-86ed-6be0b8a086a9","NAME":"建军刘"},{"DOMICILE":"破庸人自扰之","ZZ_RESIDENCE":"你之前","HUZU":"尼玛1","ID":"1168dcb9-aa92-42da-93e1-cdeaf66e0d9e","NAME":"尼玛1"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"安徽","HUZU":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","ID":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","NAME":"刘建军"}],"allnum":14}}
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
             * personlistcon : {"prelogs":[{"DOMICILE":"提XP一无所有荣耀","ZZ_RESIDENCE":"武汉","HUZU":"8b26f0d1-c987-4601-bc72-9336cc5cbdba","ID":"b2b2ce29-1c02-4c80-9a6e-dc33b393bee2","NAME":"今年是"},{"DOMICILE":"今日我","ZZ_RESIDENCE":"湖北","HUZU":"fc05a2ed-1af5-46a9-8ea3-eaee64188824","ID":"a26a8521-616b-4e44-97ce-b0eaac1b912e","NAME":"jy"},{"DOMICILE":"mxkc","ZZ_RESIDENCE":"22","HUZU":"90616332-ED39-45B2-BED8-EF551261C085","ID":"3e865ad2-0d5d-4fd7-a1ac-656cd1fc1804","NAME":"11"},{"DOMICILE":"22","ZZ_RESIDENCE":"111","HUZU":"D13CE00D-2168-3DD3-23DA-551FA0011AC5","ID":"fa1c7dea-0a19-430e-8bd9-50097b53df92","NAME":"11"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"湖州","HUZU":"906894FD-786E-4A09-AAC3-7EA8E50FB278","ID":"e480b282-8049-46e8-9376-42102a1fcd1f","NAME":"建军"},{"DOMICILE":"xjmxmx","ZZ_RESIDENCE":"123","HUZU":"4EAA8F29-5325-405E-8AA5-0028898E1BF9","ID":"b79c9845-a0cb-472e-b484-c18371aa160c","NAME":"qqwer"},{"DOMICILE":"ndnndnfnmvmcm","ZZ_RESIDENCE":"jjjj","HUZU":"28E77685-0B90-4D4A-A26C-534AC1774944","ID":"f76d8fba-7a6f-4167-aa5c-f941cdda555f","NAME":"hshd"},{"DOMICILE":"以身相许需要","ZZ_RESIDENCE":"武汉","HUZU":"理发","ID":"4d7a1761-8a92-4b65-86ed-6be0b8a086a9","NAME":"建军刘"},{"DOMICILE":"破庸人自扰之","ZZ_RESIDENCE":"你之前","HUZU":"尼玛1","ID":"1168dcb9-aa92-42da-93e1-cdeaf66e0d9e","NAME":"尼玛1"},{"DOMICILE":"安徽","ZZ_RESIDENCE":"安徽","HUZU":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","ID":"a9972c97-f406-44e5-a809-35b9c9d0c4d7","NAME":"刘建军"}],"allnum":14}
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
                }
            }
        }
    }
}
