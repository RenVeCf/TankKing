package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/15.
 */
public class AliPayBean {
    /**
     * code : 200
     * msg :
     * data : {"pay_str":"partner=\"2088531387256750\"&seller_id=\"too201911@163.com\"&out_trade_no=\"DIAM201906151635431646\"&subject=\"坦克王者钻石充值\"&body=\"坦克王者钻石充值\"&total_fee=\"0.01\"&notify_url=\"http://61.244.208.205:8082/api/callback/alipay\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&return_url=\"m.alipay.com\"&sign=\"ND26ndSArd6OpiGpxqNwDIaVngv265wu8DVXNJn5ItDDZlFELko22%2BwqSDBkAJXmwDMrFj71pnGVg%2FvfLarj23dV%2BXLf%2BzR5Dm0ZNn9dnjmf%2BwqGr6Pj3epObFgrao5lt48nM2jBNP%2FkKguOH39PG8o3tyXMTVOEczPgLvqaCgg%3D\"&sign_type=\"RSA\""}
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
         * pay_str : partner="2088531387256750"&seller_id="too201911@163.com"&out_trade_no="DIAM201906151635431646"&subject="坦克王者钻石充值"&body="坦克王者钻石充值"&total_fee="0.01"&notify_url="http://61.244.208.205:8082/api/callback/alipay"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="ND26ndSArd6OpiGpxqNwDIaVngv265wu8DVXNJn5ItDDZlFELko22%2BwqSDBkAJXmwDMrFj71pnGVg%2FvfLarj23dV%2BXLf%2BzR5Dm0ZNn9dnjmf%2BwqGr6Pj3epObFgrao5lt48nM2jBNP%2FkKguOH39PG8o3tyXMTVOEczPgLvqaCgg%3D"&sign_type="RSA"
         */

        private String pay_str;

        public String getPay_str() {
            return pay_str;
        }

        public void setPay_str(String pay_str) {
            this.pay_str = pay_str;
        }
    }
}
