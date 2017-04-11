package com.zhicheng.bean.http;

import java.util.List;

/**
 * Created by hp on 2017/3/14.
 */

public class AddressBookResponse {


    /**
     * iq : {"namespace":"AddressBookResponse","query":{"totalNums":2,"items":[{"type":"1","email":"","address":"","charType":"x","py":"xiong熊wen文wen文","deptPY":"a 安监中心","phone1":"","phone2":"","pinyin":"xww","sex":"","brithday":"","id":"7502","name":"熊文文","nums":"","departmentName":"安监中心","imageHref":"/UserUploadFile/photo/photo.png","position":"","tel":"","phone":""},{"type":"1","email":"","address":"","charType":"w","py":"wei卫xin新min民","deptPY":"a 安监中心","phone1":"","phone2":"","pinyin":"wxm","sex":"","brithday":"","id":"7508","name":"卫新民","nums":"","departmentName":"安监中心","imageHref":"/UserUploadFile/photo/photo.png","position":"","tel":"","phone":""}],"directorys":[{"id":"-1","name":"首页","type":"2"},{"id":"2333","name":"雉城街道","type":"2"},{"id":"2340","name":"安监中心","type":"4"}],"errorCode":"0","errorMessage":""}}
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
         * namespace : AddressBookResponse
         * query : {"totalNums":2,"items":[{"type":"1","email":"","address":"","charType":"x","py":"xiong熊wen文wen文","deptPY":"a 安监中心","phone1":"","phone2":"","pinyin":"xww","sex":"","brithday":"","id":"7502","name":"熊文文","nums":"","departmentName":"安监中心","imageHref":"/UserUploadFile/photo/photo.png","position":"","tel":"","phone":""},{"type":"1","email":"","address":"","charType":"w","py":"wei卫xin新min民","deptPY":"a 安监中心","phone1":"","phone2":"","pinyin":"wxm","sex":"","brithday":"","id":"7508","name":"卫新民","nums":"","departmentName":"安监中心","imageHref":"/UserUploadFile/photo/photo.png","position":"","tel":"","phone":""}],"directorys":[{"id":"-1","name":"首页"},{"id":"2333","name":"雉城街道","type":"2"},{"id":"2340","name":"安监中心","type":"4"}],"errorCode":"0","errorMessage":""}
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
             * totalNums : 2
             * items : [{"type":"1","email":"","address":"","charType":"x","py":"xiong熊wen文wen文","deptPY":"a 安监中心","phone1":"","phone2":"","pinyin":"xww","sex":"","brithday":"","id":"7502","name":"熊文文","nums":"","departmentName":"安监中心","imageHref":"/UserUploadFile/photo/photo.png","position":"","tel":"","phone":""},{"type":"1","email":"","address":"","charType":"w","py":"wei卫xin新min民","deptPY":"a 安监中心","phone1":"","phone2":"","pinyin":"wxm","sex":"","brithday":"","id":"7508","name":"卫新民","nums":"","departmentName":"安监中心","imageHref":"/UserUploadFile/photo/photo.png","position":"","tel":"","phone":""}]
             * directorys : [{"id":"-1","name":"首页"},{"id":"2333","name":"雉城街道","type":"2"},{"id":"2340","name":"安监中心","type":"4"}]
             * errorCode : 0
             * errorMessage :
             */

            private int totalNums;
            private String errorCode;
            private String errorMessage;
            private List<ItemsBean> items;
            private List<DirectorysBean> directorys;

            public int getTotalNums() {
                return totalNums;
            }

            public void setTotalNums(int totalNums) {
                this.totalNums = totalNums;
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

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public List<DirectorysBean> getDirectorys() {
                return directorys;
            }

            public void setDirectorys(List<DirectorysBean> directorys) {
                this.directorys = directorys;
            }

            public static class ItemsBean {
                /**
                 * type : 1
                 * email :
                 * address :
                 * charType : x
                 * py : xiong熊wen文wen文
                 * deptPY : a 安监中心
                 * phone1 :
                 * phone2 :
                 * pinyin : xww
                 * sex :
                 * brithday :
                 * id : 7502
                 * name : 熊文文
                 * nums :
                 * departmentName : 安监中心
                 * imageHref : /UserUploadFile/photo/photo.png
                 * position :
                 * tel :
                 * phone :
                 */

                private String type;
                private String email;
                private String address;
                private String charType;
                private String py;
                private String deptPY;
                private String phone1;
                private String phone2;
                private String pinyin;
                private String sex;
                private String brithday;
                private String id;
                private String name;
                private String nums;
                private String nodeNums;
                private String departmentName;
                private String imageHref;
                private String position;
                private String tel;
                private String phone;

                public String getNodeNums() {
                    return nodeNums;
                }

                public void setNodeNums(String nodeNums) {
                    this.nodeNums = nodeNums;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getCharType() {
                    return charType;
                }

                public void setCharType(String charType) {
                    this.charType = charType;
                }

                public String getPy() {
                    return py;
                }

                public void setPy(String py) {
                    this.py = py;
                }

                public String getDeptPY() {
                    return deptPY;
                }

                public void setDeptPY(String deptPY) {
                    this.deptPY = deptPY;
                }

                public String getPhone1() {
                    return phone1;
                }

                public void setPhone1(String phone1) {
                    this.phone1 = phone1;
                }

                public String getPhone2() {
                    return phone2;
                }

                public void setPhone2(String phone2) {
                    this.phone2 = phone2;
                }

                public String getPinyin() {
                    return pinyin;
                }

                public void setPinyin(String pinyin) {
                    this.pinyin = pinyin;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getBrithday() {
                    return brithday;
                }

                public void setBrithday(String brithday) {
                    this.brithday = brithday;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNums() {
                    return nums;
                }

                public void setNums(String nums) {
                    this.nums = nums;
                }

                public String getDepartmentName() {
                    return departmentName;
                }

                public void setDepartmentName(String departmentName) {
                    this.departmentName = departmentName;
                }

                public String getImageHref() {
                    return imageHref;
                }

                public void setImageHref(String imageHref) {
                    this.imageHref = imageHref;
                }

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
                }

                public String getTel() {
                    return tel;
                }

                public void setTel(String tel) {
                    this.tel = tel;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }
            }


            public static class DirectorysBean {
                /**
                 * id : -1
                 * name : 首页
                 * type : 2
                 */

                private String id;
                private String name;
                private String type;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
}
