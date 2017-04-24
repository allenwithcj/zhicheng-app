package com.zhicheng.bean.json;

/**
 * Created by lwl on 2017/4/14.
 */

public class ExperienceRequest {

    private IqBean iq;

    public IqBean getIq() {
        return iq;
    }

    public void setIq(IqBean iq) {
        this.iq = iq;
    }

    public static class IqBean {
        /**
         * namespace : SubmitFormRequest
         * query : {"FC01":"","FC02":"","FC03":"2017-02-17 14:52:27","FC04":"广东省珠海市香洲区水风二街","FC05":"","FC07":"测试","FC08":"","FC10":"ed8a34cb-384b-4ef3-981d-110e9730cd26","FC11":"2017-02-17 14:53:26","FC12":"社管通App","FC13":"2017-02-17 14:53:26","FC14":"4421","FC15":"2058","FC18":"创建办","FC30":"5","FC28":"违法建筑/违规堆放垃圾","FC40":"1","FC19":"是","FC41":"113.580323","FC45":"22.377228","FC49":"","FC50":"工作日","FC51":"整治清理","FC17":"","FC57":"","FC58":"","FC59":"5","formNo":"1","form_key":"201509242003320","sbldlistbysgt":"","yjlistbysgt":"","sbbmlistbysgt":"","FC61":"0","FC62":"0","FC63":"0"}
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
            private String TITLE_NAME;//  标题
            private String POSTED_BY; //发布人ID
            private String DEPARTMENT;//  发布人部门
            private String POSTED_TIME;
            private String STATUS;
            private String UNITCODE;
            private String POSTED_PERSON;
            private String CONTENT;//主题内容
            private String ANNEX;//   附件
            private String taskTitle;
            private int page;
            private int row;
            private String type;//1列表  2详情
            private String formNo;
            private String ID;
            private String USERNAME;
            private String COUNT;
            private String IMG;
            private String LOCATION;
            private String DATETIME;
            private String DEPT;
            private String USERID;

            public String getTITLE_NAME() {
                return TITLE_NAME;
            }

            public void setTITLE_NAME(String TITLE_NAME) {
                this.TITLE_NAME = TITLE_NAME;
            }

            public String getPOSTED_BY() {
                return POSTED_BY;
            }

            public void setPOSTED_BY(String POSTED_BY) {
                this.POSTED_BY = POSTED_BY;
            }

            public String getDEPARTMENT() {
                return DEPARTMENT;
            }

            public void setDEPARTMENT(String DEPARTMENT) {
                this.DEPARTMENT = DEPARTMENT;
            }

            public String getPOSTED_TIME() {
                return POSTED_TIME;
            }

            public void setPOSTED_TIME(String POSTED_TIME) {
                this.POSTED_TIME = POSTED_TIME;
            }

            public String getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(String STATUS) {
                this.STATUS = STATUS;
            }

            public String getUNITCODE() {
                return UNITCODE;
            }

            public void setUNITCODE(String UNITCODE) {
                this.UNITCODE = UNITCODE;
            }

            public String getPOSTED_PERSON() {
                return POSTED_PERSON;
            }

            public void setPOSTED_PERSON(String POSTED_PERSON) {
                this.POSTED_PERSON = POSTED_PERSON;
            }

            public String getCONTENT() {
                return CONTENT;
            }

            public void setCONTENT(String CONTENT) {
                this.CONTENT = CONTENT;
            }

            public String getANNEX() {
                return ANNEX;
            }

            public void setANNEX(String ANNEX) {
                this.ANNEX = ANNEX;
            }

            public String getTaskTitle() {
                return taskTitle;
            }

            public void setTaskTitle(String taskTitle) {
                this.taskTitle = taskTitle;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getFormNo() {
                return formNo;
            }

            public void setFormNo(String formNo) {
                this.formNo = formNo;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getUSERNAME() {
                return USERNAME;
            }

            public void setUSERNAME(String USERNAME) {
                this.USERNAME = USERNAME;
            }

            public String getCOUNT() {
                return COUNT;
            }

            public void setCOUNT(String COUNT) {
                this.COUNT = COUNT;
            }

            public String getLOCATION() {
                return LOCATION;
            }

            public void setLOCATION(String LOCATION) {
                this.LOCATION = LOCATION;
            }

            public String getDATETIME() {
                return DATETIME;
            }

            public void setDATETIME(String DATETIME) {
                this.DATETIME = DATETIME;
            }

            public String getIMG() {
                return IMG;
            }

            public void setIMG(String IMG) {
                this.IMG = IMG;
            }

            public String getDEPT() {
                return DEPT;
            }

            public void setDEPT(String DEPT) {
                this.DEPT = DEPT;
            }

            public String getUSERID() {
                return USERID;
            }

            public void setUSERID(String USERID) {
                this.USERID = USERID;
            }
        }
    }
}
