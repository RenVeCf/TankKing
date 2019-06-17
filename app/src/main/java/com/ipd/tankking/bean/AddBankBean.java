package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/5.
 */
public class AddBankBean {
    /**
     * code : 200
     * msg : 添加成功
     * data : {"uid":"45","bank_name":"血继界限兼职","bank_uname":"小鸡小鸡","bank":"1234213421342134","bank_open":"吓唬吓唬自己去","create_at":"2019-06-06 17:32:56"}
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
         * uid : 45
         * bank_name : 血继界限兼职
         * bank_uname : 小鸡小鸡
         * bank : 1234213421342134
         * bank_open : 吓唬吓唬自己去
         * create_at : 2019-06-06 17:32:56
         */

        private String uid;
        private String bank_name;
        private String bank_uname;
        private String bank;
        private String bank_open;
        private String create_at;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

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

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBank_open() {
            return bank_open;
        }

        public void setBank_open(String bank_open) {
            this.bank_open = bank_open;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }
    }
}
