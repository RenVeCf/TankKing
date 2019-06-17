package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/5.
 */
public class ModifyBankBean {
    /**
     * code : 200
     * msg : 修改成功
     * data : {"bank_name":"福地湖","bank_uname":"新疆城建","bank_open":"陈新华大街","bank":"1472147214721472"}
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
         * bank_name : 福地湖
         * bank_uname : 新疆城建
         * bank_open : 陈新华大街
         * bank : 1472147214721472
         */

        private String bank_name;
        private String bank_uname;
        private String bank_open;
        private String bank;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_uname() {
            return bank_uname;
        }

        public void setBank_uname(String bank_uname) {
            this.bank_uname = bank_uname;
        }

        public String getBank_open() {
            return bank_open;
        }

        public void setBank_open(String bank_open) {
            this.bank_open = bank_open;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
    }
}
