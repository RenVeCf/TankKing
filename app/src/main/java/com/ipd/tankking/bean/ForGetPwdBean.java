package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/4.
 */
public class ForGetPwdBean {
    /**
     * code : 200
     * msg : 更改密码成功
     * data : {"member_password":"a80dd3daa32c99416ab875e059238b9d","update_at":"2019-06-04 14:16:42"}
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
         * member_password : a80dd3daa32c99416ab875e059238b9d
         * update_at : 2019-06-04 14:16:42
         */

        private String member_password;
        private String update_at;

        public String getMember_password() {
            return member_password;
        }

        public void setMember_password(String member_password) {
            this.member_password = member_password;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }
    }
}
