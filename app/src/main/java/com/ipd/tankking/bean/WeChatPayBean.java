package com.ipd.tankking.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/15.
 */
public class WeChatPayBean {
    /**
     * code : 200
     * msg :
     * data : {"appid":"wx359eeab705dff0ac","noncestr":"9cfec37b1c2ea0deabed101cc5672e2e","package":"Sign=WXPay","partnerid":"1538286861","prepayid":"wx1709445138884982f91a57541343673000","timestamp":1560735889,"sign":"121B78429BF1E682C88A314185391E41","prepay_id":"wx1709445138884982f91a57541343673000","app_secret":"c72f63ddd5eeb46ba5d37e8cc341c98a","api_key":"B8B99ECA6ADC0304261BA588D444C70C"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * appid : wx359eeab705dff0ac
         * noncestr : 9cfec37b1c2ea0deabed101cc5672e2e
         * package : Sign=WXPay
         * partnerid : 1538286861
         * prepayid : wx1709445138884982f91a57541343673000
         * timestamp : 1560735889
         * sign : 121B78429BF1E682C88A314185391E41
         * prepay_id : wx1709445138884982f91a57541343673000
         * app_secret : c72f63ddd5eeb46ba5d37e8cc341c98a
         * api_key : B8B99ECA6ADC0304261BA588D444C70C
         */

        private String appid;
        private String noncestr;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private int timestamp;
        private String sign;
        private String prepay_id;
        private String app_secret;
        private String api_key;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getApp_secret() {
            return app_secret;
        }

        public void setApp_secret(String app_secret) {
            this.app_secret = app_secret;
        }

        public String getApi_key() {
            return api_key;
        }

        public void setApi_key(String api_key) {
            this.api_key = api_key;
        }
    }
}
