package com.fengz.personal.fourweeks.business1.model.entity;

import java.util.List;

public class TestBean {

    /**
     * ret : 1000
     * content : {"scalar":false}
     * msg : 请求参数错误:order_id
     * timestamp : 1553827984
     * zone_id : 6000
     */

    private int ret;
    private ContentBean content;
    private String msg;
    private int timestamp;
    private String zone_id;
    private List<A> list;

    public List<A> getList() {
        return list;
    }

    public void setList(List<A> list) {
        this.list = list;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public static class ContentBean {
        /**
         * scalar : false
         */

        private boolean scalar;

        @Override
        public String toString() {
            return "ContentBean{" +
                    "scalar=" + scalar +
                    '}';
        }

        public boolean isScalar() {
            return scalar;
        }

        public void setScalar(boolean scalar) {
            this.scalar = scalar;
        }
    }

    public static class A{
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return "A{" +
                    "a='" + a + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "ret=" + ret +
                ", content=" + content +
                ", msg='" + msg + '\'' +
                ", timestamp=" + timestamp +
                ", zone_id='" + zone_id + '\'' +
                ", list=" + list +
                '}';
    }
}
