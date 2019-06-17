package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/4.
 */
public class GetUserInfoBean {
    /**
     * code : 200
     * msg : 获取成功
     * data : {"id":45,"member_id":"TKWZ4JYH9REFN9","member_mobile":"18502994087","member_lv":0,"member_sco":0,"member_ban":"0.00","member_com":"0.00","member_cash":"0.00","member_status":1,"create_at":"2019-06-04 13:53:26","update_at":"2019-06-04 15:49:11","member_sub":0,"member_name":"读读书","member_password":"a80dd3daa32c99416ab875e059238b9d","member_img":"http://61.244.208.205:8082\\uploads/20190604\\5b442e6566bc5bca544322958fd25be2.png","member_coin":"","member_diamonds":""}
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
         * id : 45
         * member_id : TKWZ4JYH9REFN9
         * member_mobile : 18502994087
         * member_lv : 0
         * member_sco : 0
         * member_ban : 0.00
         * member_com : 0.00
         * member_cash : 0.00
         * member_status : 1
         * create_at : 2019-06-04 13:53:26
         * update_at : 2019-06-04 15:49:11
         * member_sub : 0
         * member_name : 读读书
         * member_password : a80dd3daa32c99416ab875e059238b9d
         * member_img :
         * member_coin :
         * member_diamonds :
         */

        private int id;
        private String member_id;
        private String member_mobile;
        private int member_lv;
        private int member_sco;
        private String member_ban;
        private String member_com;
        private String member_cash;
        private int member_status;
        private String create_at;
        private String update_at;
        private int member_sub;
        private String member_name;
        private String member_password;
        private String member_img;
        private String member_coin;
        private String member_diamonds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public int getMember_lv() {
            return member_lv;
        }

        public void setMember_lv(int member_lv) {
            this.member_lv = member_lv;
        }

        public int getMember_sco() {
            return member_sco;
        }

        public void setMember_sco(int member_sco) {
            this.member_sco = member_sco;
        }

        public String getMember_ban() {
            return member_ban;
        }

        public void setMember_ban(String member_ban) {
            this.member_ban = member_ban;
        }

        public String getMember_com() {
            return member_com;
        }

        public void setMember_com(String member_com) {
            this.member_com = member_com;
        }

        public String getMember_cash() {
            return member_cash;
        }

        public void setMember_cash(String member_cash) {
            this.member_cash = member_cash;
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

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public int getMember_sub() {
            return member_sub;
        }

        public void setMember_sub(int member_sub) {
            this.member_sub = member_sub;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getMember_password() {
            return member_password;
        }

        public void setMember_password(String member_password) {
            this.member_password = member_password;
        }

        public String getMember_img() {
            return member_img;
        }

        public void setMember_img(String member_img) {
            this.member_img = member_img;
        }

        public String getMember_coin() {
            return member_coin;
        }

        public void setMember_coin(String member_coin) {
            this.member_coin = member_coin;
        }

        public String getMember_diamonds() {
            return member_diamonds;
        }

        public void setMember_diamonds(String member_diamonds) {
            this.member_diamonds = member_diamonds;
        }
    }
}
