package com.ipd.tankking.bean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/5.
 */
public class SelectBankBean {
    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":4,"uid":45,"bank":"6103123412341234","bank_name":"中国银行","bank_uname":"任梦阳","bank_open":"嘟嘟嘟","update_at":"0000-00-00 00:00:00","create_at":"2019-06-05 16:51:14"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * uid : 45
         * bank : 6103123412341234
         * bank_name : 中国银行
         * bank_uname : 任梦阳
         * bank_open : 嘟嘟嘟
         * update_at : 0000-00-00 00:00:00
         * create_at : 2019-06-05 16:51:14
         */

        private int id;
        private int uid;
        private String bank;
        private String bank_name;
        private String bank_uname;
        private String bank_open;
        private String update_at;
        private String create_at;
        private boolean isShow;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

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

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
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

        public String getBank_open() {
            return bank_open;
        }

        public void setBank_open(String bank_open) {
            this.bank_open = bank_open;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }
    }
}
