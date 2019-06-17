package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/4.
 */
public class RegisterBean {
    /**
     * code : 200
     * msg : 注册成功
     * data : {"member_id":"TKWZ4JYH9REFN9","member_mobile":"18502994087","member_password":"5a8078c3883edb5cf97913fe386ba542","member_name":"18502994087","member_status":1,"create_at":"2019-06-04 13:53:26"}
     */

    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * member_id : TKWZ4JYH9REFN9
         * member_mobile : 18502994087
         * member_password : 5a8078c3883edb5cf97913fe386ba542
         * member_name : 18502994087
         * member_status : 1
         * create_at : 2019-06-04 13:53:26
         */

        private String member_id;
        private String member_mobile;
        private String member_password;
        private String member_name;
        private int member_status;
        private String create_at;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMember_mobile() {
            return member_mobile;
        }

        public void setMember_mobile(String member_mobile) {
            this.member_mobile = member_mobile;
        }

        public String getMember_password() {
            return member_password;
        }

        public void setMember_password(String member_password) {
            this.member_password = member_password;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public int getMember_status() {
            return member_status;
        }

        public void setMember_status(int member_status) {
            this.member_status = member_status;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }
    }
}
