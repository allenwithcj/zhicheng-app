package com.zhicheng.bean.json;

/**
 * Created by hp on 2017/3/15.
 */

public class CaseQueryRequest {
    private IqBean iq;

    public IqBean getIq() {
        return iq;
    }

    public void setIq(IqBean iq) {
        this.iq = iq;
    }

    public static class IqBean {

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
            private String manageStatus;
            private String caseTime;
            private String caseName;
            private String eventType;
            private int pageNum;


            public String getManageStatus() {
                return manageStatus;
            }

            public void setManageStatus(String manageStatus) {
                this.manageStatus = manageStatus;
            }

            public String getCaseTime() {
                return caseTime;
            }

            public void setCaseTime(String caseTime) {
                this.caseTime = caseTime;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public String getEventType() {
                return eventType;
            }

            public void setEventType(String eventType) {
                this.eventType = eventType;
            }

            public String getCaseName() {
                return caseName;
            }

            public void setCaseName(String caseName) {
                this.caseName = caseName;
            }
        }
    }
}
