package com.ipd.tankking.common.config;

/**
 * Description ：公共配置类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface IConstants {
    /**
     * 包名
     */
    String PACKAGE_NAME = "com.liantong.membercenter.membercenter";

    /**
     * SharedPreferences
     * 共享参数
     */
    String FIRST_APP = "is_first"; //第一次进应用
    String IS_LOGIN = "is_login"; //已经登录
    String TOKEN = "token"; //token
    String AUTHORIZATION_TYPE = "token_type"; //token type
    String USER_ID = "user_id"; //用户id
    String NAME = "name"; //用户真实姓名
    String AVATAR = "avatar"; //头像
    String PHONE = "phone"; //用户手机号码
    String VIP_LV = "vip_lv"; //会员等级
    String EMAIL = "email"; //用户邮箱
    String GENDER = "gender"; //用户性别
    String IDCARD_NO = "idcard_no"; //用户身份证号
    String DIAMOND = "diamond"; //钻石
    String GOLD = "gold"; //金币

    /**
     * requestCode
     * 请求码
     */
    int PERMISSIONS_REQUEST_CODE = 1002;//支付权限申请
    int REQUEST_CODE_90 = 90; // 首页跳个人中心
    int REQUEST_CODE_91 = 91;// 首页跳会员商城
    int REQUEST_CODE_92 = 92;// 首页跳金币商城
    int REQUEST_CODE_93 = 93;// 首页跳钻石商城
    int REQUEST_CODE_94 = 94;// 会员商城跳金币商城
    int REQUEST_CODE_95 = 95;// 会员商城跳钻石商城
    int REQUEST_CODE_96 = 96;// 金币商城跳钻石商城
    int REQUEST_CODE_97 = 97;// 钻石商城跳金币商城
    int REQUEST_CODE_98 = 98;// 首页跳H5


    /**
     * resultCode
     * 返回码
     */
    int RESULT_CODE = 0;
}
