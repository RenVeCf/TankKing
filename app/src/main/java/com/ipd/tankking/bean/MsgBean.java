package com.ipd.tankking.bean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/4.
 */
public class MsgBean {
    /**
     * code : 200
     * msg : 查询成功
     * data : {"total":12,"per_page":10,"current_page":1,"data":[{"id":1,"uid":46,"message":"今天我们玩些什么","create_at":"2019-06-04 15:12:01"},{"id":2,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":3,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":4,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":5,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":6,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":7,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":8,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":9,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":10,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"}]}
     */

    private String code;
    private String msg;
    private DataBeanX data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 12
         * per_page : 10
         * current_page : 1
         * data : [{"id":1,"uid":46,"message":"今天我们玩些什么","create_at":"2019-06-04 15:12:01"},{"id":2,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":3,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":4,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":5,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":6,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":7,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":8,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":9,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"},{"id":10,"uid":46,"message":"0007","create_at":"0000-00-00 00:00:00"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * uid : 46
             * message : 今天我们玩些什么
             * create_at : 2019-06-04 15:12:01
             */

            private int id;
            private int uid;
            private String message;
            private String create_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }
        }
    }
}
