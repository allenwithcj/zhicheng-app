package com.zhicheng.bean.json;

import java.io.Serializable;

/**
 * Created by lwl on 2017/4/18.
 */

public class PersonQueryRequest {


    /**
     * iq : {"namespace":"PersonQueryRequest","query":{"pkey":"xxxx","userid":"宋莹","grid":"皇家湾社区第一网格","begintime":"2017-3-30 14:44:50","endtime":"2017-4-5 10:13:26","pageNum":"1"}}
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
         * namespace : PersonQueryRequest
         * query : {"pkey":"xxxx","userid":"宋莹","grid":"皇家湾社区第一网格","begintime":"2017-3-30 14:44:50","endtime":"2017-4-5 10:13:26","pageNum":"1"}
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

        public static class QueryBean implements Serializable{
            /**
             * pkey : xxxx
             * userid : 宋莹
             * grid : 皇家湾社区第一网格
             * begintime : 2017-3-30 14:44:50
             * endtime : 2017-4-5 10:13:26
             * pageNum : 1
             */

            private String pkey;
            private String userid;
            private String grid;
            private String begintime;
            private String endtime;
            private int pagenum;
            private String pmastkey;
            private String type;


            public String getPkey() {
                return pkey;
            }

            public void setPkey(String pkey) {
                this.pkey = pkey;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getGrid() {
                return grid;
            }

            public void setGrid(String grid) {
                this.grid = grid;
            }

            public String getBegintime() {
                return begintime;
            }

            public void setBegintime(String begintime) {
                this.begintime = begintime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public int getPagenum() {
                return pagenum;
            }

            public void setPagenum(int pagenum) {
                this.pagenum = pagenum;
            }

            public String getPmastkey() {
                return pmastkey;
            }

            public void setPmastkey(String pmastkey) {
                this.pmastkey = pmastkey;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
