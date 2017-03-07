package com.zhicheng.bean.json;

/**
 * Created by Donson on 2017/2/15.
 */

public class UpThingsRequest {

    /**
     * iq : {"namespace":"SubmitFormRequest","query":{"FC01":"","FC02":"","FC03":"2017-02-17 14:52:27","FC04":"广东省珠海市香洲区水风二街","FC05":"","FC07":"测试","FC08":"","FC10":"ed8a34cb-384b-4ef3-981d-110e9730cd26","FC11":"2017-02-17 14:53:26","FC12":"社管通App","FC13":"2017-02-17 14:53:26","FC14":"4421","FC15":"2058","FC18":"创建办","FC30":"5","FC28":"违法建筑/违规堆放垃圾","FC40":"1","FC19":"是","FC41":"113.580323","FC45":"22.377228","FC49":"","FC50":"工作日","FC51":"整治清理","FC17":"","FC57":"","FC58":"","FC59":"5","formNo":"1","form_key":"201509242003320","sbldlistbysgt":"","yjlistbysgt":"","sbbmlistbysgt":"","FC61":"0","FC62":"0","FC63":"0"}}
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

            private String ZC01;
            private String ZC02;
            private String ZC03;
            private String ZC04;
            private String ZC05;
            private String ZC06;
            private String ZC07;
            private String ZC08;
            private String ZC09;
            private String ZC10;
            private String ZC11;
            private String ZC12;
            private String ZC13;
            private String ZC14;
            private String ZC15;
            private String ZC16;
            private String ZC17;
            private String ZC18;
            private String ZC19;
            private String ZC20;
            private String ZC21;
            private String ZC22;
            private String ZC23;
            private String ZC24;
            private String ZC25;
            private String ZC26;
            private String ZC27;
            private String ZC28;
            private String ZC29;
            private String ZC30;
            private String ZC31;
            private String ZC32;
            private String ZC33;
            private String ZC34;
            private String ZC35;
            private String ZC36;
            private String ZC37;
            private String ZC38;
            private String ZC39;
            private String ZC40;
            private String ZC41;
            private String ZC42;
            private String ZC43;
            private String ZC44;
            private String ZC45;
            private String formNo;
            private String form_key;
            private String sbldlistbysgt;
            private String yjlistbysgt;
            private String sbbmlistbysgt;

            public String getZC01() {
                return ZC01;
            }

            public void setZC01(String ZC01) {
                this.ZC01 = ZC01;
            }

            public String getZC02() {
                return ZC02;
            }

            public void setZC02(String ZC02) {
                this.ZC02 = ZC02;
            }

            public String getZC03() {
                return ZC03;
            }

            public void setZC03(String ZC03) {
                this.ZC03 = ZC03;
            }

            public String getZC04() {
                return ZC04;
            }

            public void setZC04(String ZC04) {
                this.ZC04 = ZC04;
            }

            public String getZC06() {
                return ZC06;
            }

            public void setZC06(String ZC06) {
                this.ZC06 = ZC06;
            }

            public String getZC07() {
                return ZC07;
            }

            public void setZC07(String ZC07) {
                this.ZC07 = ZC07;
            }

            public String getZC08() {
                return ZC08;
            }

            public void setZC08(String ZC08) {
                this.ZC08 = ZC08;
            }

            public String getZC05() {
                return ZC05;
            }

            public void setZC05(String ZC05) {
                this.ZC05 = ZC05;
            }

            public String getZC09() {
                return ZC09;
            }

            public void setZC09(String ZC09) {
                this.ZC09 = ZC09;
            }

            public String getZC10() {
                return ZC10;
            }

            public void setZC10(String ZC10) {
                this.ZC10 = ZC10;
            }

            public String getZC12() {
                return ZC12;
            }

            public void setZC12(String ZC12) {
                this.ZC12 = ZC12;
            }

            public String getZC11() {
                return ZC11;
            }

            public void setZC11(String ZC11) {
                this.ZC11 = ZC11;
            }

            public String getZC14() {
                return ZC14;
            }

            public void setZC14(String ZC14) {
                this.ZC14 = ZC14;
            }

            public String getZC13() {
                return ZC13;
            }

            public void setZC13(String ZC13) {
                this.ZC13 = ZC13;
            }

            public String getZC16() {
                return ZC16;
            }

            public void setZC16(String ZC16) {
                this.ZC16 = ZC16;
            }

            public String getZC15() {
                return ZC15;
            }

            public void setZC15(String ZC15) {
                this.ZC15 = ZC15;
            }

            public String getZC17() {
                return ZC17;
            }

            public void setZC17(String ZC17) {
                this.ZC17 = ZC17;
            }

            public String getZC18() {
                return ZC18;
            }

            public void setZC18(String ZC18) {
                this.ZC18 = ZC18;
            }

            public String getZC19() {
                return ZC19;
            }

            public void setZC19(String ZC19) {
                this.ZC19 = ZC19;
            }

            public String getZC21() {
                return ZC21;
            }

            public void setZC21(String ZC21) {
                this.ZC21 = ZC21;
            }

            public String getZC20() {
                return ZC20;
            }

            public void setZC20(String ZC20) {
                this.ZC20 = ZC20;
            }

            public String getZC22() {
                return ZC22;
            }

            public void setZC22(String ZC22) {
                this.ZC22 = ZC22;
            }

            public String getZC23() {
                return ZC23;
            }

            public void setZC23(String ZC23) {
                this.ZC23 = ZC23;
            }

            public String getZC24() {
                return ZC24;
            }

            public void setZC24(String ZC24) {
                this.ZC24 = ZC24;
            }

            public String getZC26() {
                return ZC26;
            }

            public void setZC26(String ZC26) {
                this.ZC26 = ZC26;
            }

            public String getZC25() {
                return ZC25;
            }

            public void setZC25(String ZC25) {
                this.ZC25 = ZC25;
            }

            public String getZC28() {
                return ZC28;
            }

            public void setZC28(String ZC28) {
                this.ZC28 = ZC28;
            }

            public String getZC27() {
                return ZC27;
            }

            public void setZC27(String ZC27) {
                this.ZC27 = ZC27;
            }

            public String getZC30() {
                return ZC30;
            }

            public void setZC30(String ZC30) {
                this.ZC30 = ZC30;
            }

            public String getZC29() {
                return ZC29;
            }

            public void setZC29(String ZC29) {
                this.ZC29 = ZC29;
            }

            public String getZC32() {
                return ZC32;
            }

            public void setZC32(String ZC32) {
                this.ZC32 = ZC32;
            }

            public String getZC31() {
                return ZC31;
            }

            public void setZC31(String ZC31) {
                this.ZC31 = ZC31;
            }

            public String getZC33() {
                return ZC33;
            }

            public void setZC33(String ZC33) {
                this.ZC33 = ZC33;
            }

            public String getZC34() {
                return ZC34;
            }

            public void setZC34(String ZC34) {
                this.ZC34 = ZC34;
            }

            public String getZC36() {
                return ZC36;
            }

            public void setZC36(String ZC36) {
                this.ZC36 = ZC36;
            }

            public String getZC35() {
                return ZC35;
            }

            public void setZC35(String ZC35) {
                this.ZC35 = ZC35;
            }

            public String getZC38() {
                return ZC38;
            }

            public void setZC38(String ZC38) {
                this.ZC38 = ZC38;
            }

            public String getZC37() {
                return ZC37;
            }

            public void setZC37(String ZC37) {
                this.ZC37 = ZC37;
            }

            public String getZC41() {
                return ZC41;
            }

            public void setZC41(String ZC41) {
                this.ZC41 = ZC41;
            }

            public String getZC39() {
                return ZC39;
            }

            public void setZC39(String ZC39) {
                this.ZC39 = ZC39;
            }

            public String getZC40() {
                return ZC40;
            }

            public void setZC40(String ZC40) {
                this.ZC40 = ZC40;
            }

            public String getZC42() {
                return ZC42;
            }

            public void setZC42(String ZC42) {
                this.ZC42 = ZC42;
            }

            public String getZC43() {
                return ZC43;
            }

            public void setZC43(String ZC43) {
                this.ZC43 = ZC43;
            }

            public String getZC44() {
                return ZC44;
            }

            public void setZC44(String ZC44) {
                this.ZC44 = ZC44;
            }

            public String getZC45() {
                return ZC45;
            }

            public void setZC45(String ZC45) {
                this.ZC45 = ZC45;
            }

            public String getFormNo() {
                return formNo;
            }

            public void setFormNo(String formNo) {
                this.formNo = formNo;
            }

            public String getForm_key() {
                return form_key;
            }

            public void setForm_key(String form_key) {
                this.form_key = form_key;
            }

            public String getSbldlistbysgt() {
                return sbldlistbysgt;
            }

            public void setSbldlistbysgt(String sbldlistbysgt) {
                this.sbldlistbysgt = sbldlistbysgt;
            }
            public String getYjlistbysgt() {
                return yjlistbysgt;
            }

            public void setYjlistbysgt(String yjlistbysgt) {
                this.yjlistbysgt = yjlistbysgt;
            }

            public String getSbbmlistbysgt() {
                return sbbmlistbysgt;
            }

            public void setSbbmlistbysgt(String sbbmlistbysgt) {
                this.sbbmlistbysgt = sbbmlistbysgt;
            }
        }
    }
}
