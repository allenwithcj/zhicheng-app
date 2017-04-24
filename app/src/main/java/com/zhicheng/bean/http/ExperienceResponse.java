package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by lwl on 2017/4/14.
 */

public class ExperienceResponse {

    /**
     * iq : {"namespace":"ExperienceResponse","query":{"prelogcon":{"prelogs":[{"POSTED_TIME":"2017-04-14 15:20:32.0","TITLE_NAME":"流王龙","ID":"63","CONTENT":"12131231","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-14 13:59:02.0","TITLE_NAME":"测试钱","ID":"57","CONTENT":"驱蚊器无","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","ID":"47","CONTENT":"4435435453535435","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 15:07:15.0","TITLE_NAME":"1234","ID":"38","CONTENT":"123","POSTED_BY":"caozheng"},{"POSTED_TIME":"2017-04-13 08:44:26.0","TITLE_NAME":"经验交流仓前街","ID":"36","CONTENT":"仓前街","POSTED_BY":"黄利芬"},{"POSTED_TIME":"2017-04-12 17:21:08.0","TITLE_NAME":"565465546","ID":"33","CONTENT":"756546546546546","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"测试经验交流提交","ID":"24","CONTENT":"以审批人员角色进入系统，处理两项申请，可以都审批通过，即结果是两名经办人申请了同一时间同一地点的会议室，出现了冲突","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"8888888888888","ID":"27","CONTENT":"在某一个经办人【会议室申请】中填写会议室申请表送办之后，另一个经办人再次点击【会议室申请】中的申请表可以申请与前一个经办人同样的时间地点的会议室。在【会议室看板中】鼠标移动会显示两名申请人员的申请情况，两项申请的处理状态都处于待","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"11","ID":"29","CONTENT":"11","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"32423432","ID":"22","CONTENT":"43254332432467657768768789768","POSTED_BY":"吴全超"}],"allnum":13},"errorCode":"0","errorMessage":"成功"}}
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
         * namespace : ExperienceResponse
         * query : {"prelogcon":{"prelogs":[{"POSTED_TIME":"2017-04-14 15:20:32.0","TITLE_NAME":"流王龙","ID":"63","CONTENT":"12131231","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-14 13:59:02.0","TITLE_NAME":"测试钱","ID":"57","CONTENT":"驱蚊器无","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","ID":"47","CONTENT":"4435435453535435","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 15:07:15.0","TITLE_NAME":"1234","ID":"38","CONTENT":"123","POSTED_BY":"caozheng"},{"POSTED_TIME":"2017-04-13 08:44:26.0","TITLE_NAME":"经验交流仓前街","ID":"36","CONTENT":"仓前街","POSTED_BY":"黄利芬"},{"POSTED_TIME":"2017-04-12 17:21:08.0","TITLE_NAME":"565465546","ID":"33","CONTENT":"756546546546546","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"测试经验交流提交","ID":"24","CONTENT":"以审批人员角色进入系统，处理两项申请，可以都审批通过，即结果是两名经办人申请了同一时间同一地点的会议室，出现了冲突","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"8888888888888","ID":"27","CONTENT":"在某一个经办人【会议室申请】中填写会议室申请表送办之后，另一个经办人再次点击【会议室申请】中的申请表可以申请与前一个经办人同样的时间地点的会议室。在【会议室看板中】鼠标移动会显示两名申请人员的申请情况，两项申请的处理状态都处于待","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"11","ID":"29","CONTENT":"11","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"32423432","ID":"22","CONTENT":"43254332432467657768768789768","POSTED_BY":"吴全超"}],"allnum":13},"errorCode":"0","errorMessage":"成功"}
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
             * prelogcon : {"prelogs":[{"POSTED_TIME":"2017-04-14 15:20:32.0","TITLE_NAME":"流王龙","ID":"63","CONTENT":"12131231","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-14 13:59:02.0","TITLE_NAME":"测试钱","ID":"57","CONTENT":"驱蚊器无","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","ID":"47","CONTENT":"4435435453535435","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 15:07:15.0","TITLE_NAME":"1234","ID":"38","CONTENT":"123","POSTED_BY":"caozheng"},{"POSTED_TIME":"2017-04-13 08:44:26.0","TITLE_NAME":"经验交流仓前街","ID":"36","CONTENT":"仓前街","POSTED_BY":"黄利芬"},{"POSTED_TIME":"2017-04-12 17:21:08.0","TITLE_NAME":"565465546","ID":"33","CONTENT":"756546546546546","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"测试经验交流提交","ID":"24","CONTENT":"以审批人员角色进入系统，处理两项申请，可以都审批通过，即结果是两名经办人申请了同一时间同一地点的会议室，出现了冲突","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"8888888888888","ID":"27","CONTENT":"在某一个经办人【会议室申请】中填写会议室申请表送办之后，另一个经办人再次点击【会议室申请】中的申请表可以申请与前一个经办人同样的时间地点的会议室。在【会议室看板中】鼠标移动会显示两名申请人员的申请情况，两项申请的处理状态都处于待","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"11","ID":"29","CONTENT":"11","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"32423432","ID":"22","CONTENT":"43254332432467657768768789768","POSTED_BY":"吴全超"}],"allnum":13}
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
                 * prelogs : [{"POSTED_TIME":"2017-04-14 15:20:32.0","TITLE_NAME":"流王龙","ID":"63","CONTENT":"12131231","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-14 13:59:02.0","TITLE_NAME":"测试钱","ID":"57","CONTENT":"驱蚊器无","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 17:45:35.0","TITLE_NAME":"45353454353","ID":"47","CONTENT":"4435435453535435","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-13 15:07:15.0","TITLE_NAME":"1234","ID":"38","CONTENT":"123","POSTED_BY":"caozheng"},{"POSTED_TIME":"2017-04-13 08:44:26.0","TITLE_NAME":"经验交流仓前街","ID":"36","CONTENT":"仓前街","POSTED_BY":"黄利芬"},{"POSTED_TIME":"2017-04-12 17:21:08.0","TITLE_NAME":"565465546","ID":"33","CONTENT":"756546546546546","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"测试经验交流提交","ID":"24","CONTENT":"以审批人员角色进入系统，处理两项申请，可以都审批通过，即结果是两名经办人申请了同一时间同一地点的会议室，出现了冲突","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"8888888888888","ID":"27","CONTENT":"在某一个经办人【会议室申请】中填写会议室申请表送办之后，另一个经办人再次点击【会议室申请】中的申请表可以申请与前一个经办人同样的时间地点的会议室。在【会议室看板中】鼠标移动会显示两名申请人员的申请情况，两项申请的处理状态都处于待","POSTED_BY":"吴全超"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"11","ID":"29","CONTENT":"11","POSTED_BY":"章思思"},{"POSTED_TIME":"2017-04-12 00:00:00.0","TITLE_NAME":"32423432","ID":"22","CONTENT":"43254332432467657768768789768","POSTED_BY":"吴全超"}]
                 * allnum : 13
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
                     * POSTED_TIME : 2017-04-14 15:20:32.0
                     * TITLE_NAME : 流王龙
                     * ID : 63
                     * CONTENT : 12131231
                     * POSTED_BY : 章思思
                     */

                    private String POSTED_TIME;
                    private String TITLE_NAME;
                    private String ID;
                    private String CONTENT;
                    private String POSTED_BY;
                    private String DEPARTMENT;

                    public String getPOSTED_TIME() {
                        return POSTED_TIME;
                    }

                    public void setPOSTED_TIME(String POSTED_TIME) {
                        this.POSTED_TIME = POSTED_TIME;
                    }

                    public String getTITLE_NAME() {
                        return TITLE_NAME;
                    }

                    public void setTITLE_NAME(String TITLE_NAME) {
                        this.TITLE_NAME = TITLE_NAME;
                    }

                    public String getID() {
                        return ID;
                    }

                    public void setID(String ID) {
                        this.ID = ID;
                    }

                    public String getCONTENT() {
                        return CONTENT;
                    }

                    public void setCONTENT(String CONTENT) {
                        this.CONTENT = CONTENT;
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
                }
            }
        }
    }
}
