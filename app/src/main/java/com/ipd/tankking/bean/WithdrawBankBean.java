package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/6.
 */
public class WithdrawBankBean {
    /**
     * code : 200
     * msg : 申请提现已提交,请等待管理员审核
     * data : {"member_ban":193,"member_diamonds":193}
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
         * member_ban : 193
         * member_diamonds : 193
         */

        private int member_ban;
        private int member_diamonds;

        public int getMember_ban() {
            return member_ban;
        }

        public void setMember_ban(int member_ban) {
            this.member_ban = member_ban;
        }

        public int getMember_diamonds() {
            return member_diamonds;
        }

        public void setMember_diamonds(int member_diamonds) {
            this.member_diamonds = member_diamonds;
        }
    }
}
