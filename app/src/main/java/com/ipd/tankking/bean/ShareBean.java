package com.ipd.tankking.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/17.
 */
public class ShareBean {
    /**
     * code : 200
     * msg :
     * data : {"qr_code":"http://61.244.208.205:8082/api/member/code?uid=45","share_url":"http://61.244.208.205:8082/tk_frontend/register/register.html?pid=45&timestamp=1560751134"}
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
         * qr_code : http://61.244.208.205:8082/api/member/code?uid=45
         * share_url : http://61.244.208.205:8082/tk_frontend/register/register.html?pid=45&timestamp=1560751134
         */

        private String qr_code;
        private String share_url;

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }
    }
}
