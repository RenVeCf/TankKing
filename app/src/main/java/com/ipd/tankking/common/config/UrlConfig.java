package com.ipd.tankking.common.config;

/**
 * Description ：URL 配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UrlConfig {
    /**
     * 域名
     */
    String BASE_URL = "http://173.248.240.250/"; //正式
//    String BASE_URL = "http://61.244.208.205:8082/"; //测试

    /**
     * 登陆
     */
    String CAPTCHA = "api/Retrieve/getcode"; //验证码
    String LOGIN = "api/login/login"; //登陆
    String REGISTER = "api/register/register"; //注册
    String FORGET_PWD = "api/Retrieve/retrieve"; //忘记密码


    /**
     * 首页
     */
    String GET_USER_INFO = "api/Msgup/user"; //获取用户信息


    /**
     * 个人中心
     */
    String MODIFY_HEAD_IMG = "api/Img/Img"; //上传头像
    String MODIFY_USER_NAME = "api/Msgup/name"; //用户名更改
    String MSG = "api/Msgup/message"; //消息中心
    String ABOUT = "api/about/about"; //关于我们
    String FEED_BACK = "api/Msgup/feed"; //反馈意见
    String SELECT_BANK = "api/Bank/bank"; //查看银行卡
    String ADD_BANK = "api/Bank/join"; //添加银行卡
    String MODIFY_BANK = "api/Bank/editb"; //修改银行卡
    String DEL_BANK = "api/Bank/kill"; //删除银行卡
    String WITHDRAW_BANK = "api/cash/cash"; //提现到银行卡
    String SHARE = "api/member/share"; //分享


    /**
     * 金币商城
     */
    String GOLD_STORE = "api/coin/coin"; //金币兑换


    /**
     * 钻石商城
     */
    String DIAMOND_STORE = "api/coin/diam"; //钻石兑换

    /**
     * 充值
     */
    String DIAMOND_PAY = "api/order/diamond"; //钻石充值
    String VIP_PAY = "api/order/member"; //会员充值
}
