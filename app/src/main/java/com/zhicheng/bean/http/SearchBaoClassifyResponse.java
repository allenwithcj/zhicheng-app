package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by Donson on 2017/2/16.
 */

public class SearchBaoClassifyResponse {

    /**
     * iq : {"query":{"errorMessage":"成功","errorCode":0,"items":[{"code":"05","pLimitUnit":"工作日","firstPart":"劳动保障","isSelect":"0","thirdName":"未足额发放劳动报酬","parentId":"2076","timeLimit":"7","firstName":"劳动保障","limitDescribe":"7个工作日","mergeCategory":"劳动保障/未足额发放劳动报酬","name":"未足额发放劳动报酬","id":"2077","secondName":"未足额发放劳动报酬"},{"code":"05","pLimitUnit":"工作日","firstPart":"劳动保障","isSelect":"0","thirdName":"未登记和缴纳社会保险","parentId":"2076","timeLimit":"7","firstName":"劳动保障","limitDescribe":"7个工作日","mergeCategory":"劳动保障/未登记和缴纳社会保险","name":"未登记和缴纳社会保险","id":"2078","secondName":"未登记和缴纳社会保险"}]},"namespace":"CaseItemResponse"}
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
         * query : {"errorMessage":"成功","errorCode":0,"items":[{"code":"05","pLimitUnit":"工作日","firstPart":"劳动保障","isSelect":"0","thirdName":"未足额发放劳动报酬","parentId":"2076","timeLimit":"7","firstName":"劳动保障","limitDescribe":"7个工作日","mergeCategory":"劳动保障/未足额发放劳动报酬","name":"未足额发放劳动报酬","id":"2077","secondName":"未足额发放劳动报酬"},{"code":"05","pLimitUnit":"工作日","firstPart":"劳动保障","isSelect":"0","thirdName":"未登记和缴纳社会保险","parentId":"2076","timeLimit":"7","firstName":"劳动保障","limitDescribe":"7个工作日","mergeCategory":"劳动保障/未登记和缴纳社会保险","name":"未登记和缴纳社会保险","id":"2078","secondName":"未登记和缴纳社会保险"}]}
         * namespace : CaseItemResponse
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
             * items : [{"code":"05","pLimitUnit":"工作日","firstPart":"劳动保障","isSelect":"0","thirdName":"未足额发放劳动报酬","parentId":"2076","timeLimit":"7","firstName":"劳动保障","limitDescribe":"7个工作日","mergeCategory":"劳动保障/未足额发放劳动报酬","name":"未足额发放劳动报酬","id":"2077","secondName":"未足额发放劳动报酬"},{"code":"05","pLimitUnit":"工作日","firstPart":"劳动保障","isSelect":"0","thirdName":"未登记和缴纳社会保险","parentId":"2076","timeLimit":"7","firstName":"劳动保障","limitDescribe":"7个工作日","mergeCategory":"劳动保障/未登记和缴纳社会保险","name":"未登记和缴纳社会保险","id":"2078","secondName":"未登记和缴纳社会保险"}]
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
                 * code : 05
                 * pLimitUnit : 工作日
                 * firstPart : 劳动保障
                 * isSelect : 0
                 * thirdName : 未足额发放劳动报酬
                 * parentId : 2076
                 * timeLimit : 7
                 * firstName : 劳动保障
                 * limitDescribe : 7个工作日
                 * mergeCategory : 劳动保障/未足额发放劳动报酬
                 * name : 未足额发放劳动报酬
                 * id : 2077
                 * secondName : 未足额发放劳动报酬
                 */

                private String code;
                private String pLimitUnit;
                private String firstPart;
                private String isSelect;
                private String thirdName;
                private String parentId;
                private String timeLimit;
                private String firstName;
                private String limitDescribe;
                private String mergeCategory;
                private String name;
                private String id;
                private String secondName;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getPLimitUnit() {
                    return pLimitUnit;
                }

                public void setPLimitUnit(String pLimitUnit) {
                    this.pLimitUnit = pLimitUnit;
                }

                public String getFirstPart() {
                    return firstPart;
                }

                public void setFirstPart(String firstPart) {
                    this.firstPart = firstPart;
                }

                public String getIsSelect() {
                    return isSelect;
                }

                public void setIsSelect(String isSelect) {
                    this.isSelect = isSelect;
                }

                public String getThirdName() {
                    return thirdName;
                }

                public void setThirdName(String thirdName) {
                    this.thirdName = thirdName;
                }

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }

                public String getTimeLimit() {
                    return timeLimit;
                }

                public void setTimeLimit(String timeLimit) {
                    this.timeLimit = timeLimit;
                }

                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }

                public String getLimitDescribe() {
                    return limitDescribe;
                }

                public void setLimitDescribe(String limitDescribe) {
                    this.limitDescribe = limitDescribe;
                }

                public String getMergeCategory() {
                    return mergeCategory;
                }

                public void setMergeCategory(String mergeCategory) {
                    this.mergeCategory = mergeCategory;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSecondName() {
                    return secondName;
                }

                public void setSecondName(String secondName) {
                    this.secondName = secondName;
                }
            }
        }
    }
}
