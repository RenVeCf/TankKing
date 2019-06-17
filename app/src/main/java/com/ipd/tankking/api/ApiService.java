package com.ipd.tankking.api;

import com.ipd.tankking.bean.AboutBean;
import com.ipd.tankking.bean.AddBankBean;
import com.ipd.tankking.bean.AliPayBean;
import com.ipd.tankking.bean.CaptchaBean;
import com.ipd.tankking.bean.DelBankBean;
import com.ipd.tankking.bean.DiamondStoreBean;
import com.ipd.tankking.bean.FeedBackBean;
import com.ipd.tankking.bean.ForGetPwdBean;
import com.ipd.tankking.bean.GetUserInfoBean;
import com.ipd.tankking.bean.GoldStoreBean;
import com.ipd.tankking.bean.LoginBean;
import com.ipd.tankking.bean.ModifyBankBean;
import com.ipd.tankking.bean.ModifyHeadImgBean;
import com.ipd.tankking.bean.ModifyUserNameBean;
import com.ipd.tankking.bean.MsgBean;
import com.ipd.tankking.bean.RegisterBean;
import com.ipd.tankking.bean.SelectBankBean;
import com.ipd.tankking.bean.ShareBean;
import com.ipd.tankking.bean.WeChatPayBean;
import com.ipd.tankking.bean.WithdrawBankBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

import static com.ipd.tankking.common.config.UrlConfig.ABOUT;
import static com.ipd.tankking.common.config.UrlConfig.ADD_BANK;
import static com.ipd.tankking.common.config.UrlConfig.CAPTCHA;
import static com.ipd.tankking.common.config.UrlConfig.DEL_BANK;
import static com.ipd.tankking.common.config.UrlConfig.DIAMOND_PAY;
import static com.ipd.tankking.common.config.UrlConfig.DIAMOND_STORE;
import static com.ipd.tankking.common.config.UrlConfig.FEED_BACK;
import static com.ipd.tankking.common.config.UrlConfig.FORGET_PWD;
import static com.ipd.tankking.common.config.UrlConfig.GET_USER_INFO;
import static com.ipd.tankking.common.config.UrlConfig.GOLD_STORE;
import static com.ipd.tankking.common.config.UrlConfig.LOGIN;
import static com.ipd.tankking.common.config.UrlConfig.MODIFY_BANK;
import static com.ipd.tankking.common.config.UrlConfig.MODIFY_HEAD_IMG;
import static com.ipd.tankking.common.config.UrlConfig.MODIFY_USER_NAME;
import static com.ipd.tankking.common.config.UrlConfig.MSG;
import static com.ipd.tankking.common.config.UrlConfig.REGISTER;
import static com.ipd.tankking.common.config.UrlConfig.SELECT_BANK;
import static com.ipd.tankking.common.config.UrlConfig.SHARE;
import static com.ipd.tankking.common.config.UrlConfig.VIP_PAY;
import static com.ipd.tankking.common.config.UrlConfig.WITHDRAW_BANK;

/**
 * Description ：请求配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */
public interface ApiService {

    //验证码
    @FormUrlEncoded
    @POST(CAPTCHA)
    Observable<CaptchaBean> getCaptcha(@FieldMap Map<String, String> map);

    //忘记密码
    @FormUrlEncoded
    @POST(FORGET_PWD)
    Observable<ForGetPwdBean> getForGetPwd(@FieldMap Map<String, String> map);

    //注册
    @FormUrlEncoded
    @POST(REGISTER)
    Observable<RegisterBean> getRegister(@FieldMap Map<String, String> map);

    //登录
    @FormUrlEncoded
    @POST(LOGIN)
    Observable<LoginBean> getLogin(@FieldMap Map<String, String> map);

    //用户名更改
    @FormUrlEncoded
    @POST(MODIFY_USER_NAME)
    Observable<ModifyUserNameBean> getModifyUserName(@FieldMap Map<String, String> map);

    //获取用户信息
    @FormUrlEncoded
    @POST(GET_USER_INFO)
    Observable<GetUserInfoBean> getGetUserInfo(@FieldMap Map<String, String> map);

    //消息中心
    @FormUrlEncoded
    @POST(MSG)
    Observable<MsgBean> getMsg(@FieldMap Map<String, String> map);

    //反馈意见
    @FormUrlEncoded
    @POST(FEED_BACK)
    Observable<FeedBackBean> getFeedBack(@FieldMap Map<String, String> map);

    //关于我们
    @GET(ABOUT)
    Observable<AboutBean> getAbout();

    //上传头像
    @Multipart
    @POST(MODIFY_HEAD_IMG)
    Observable<ModifyHeadImgBean> getModifyHeadImg(@PartMap Map<String, RequestBody> map, @Query("id") String userId);

    //查看银行卡
    @FormUrlEncoded
    @POST(SELECT_BANK)
    Observable<SelectBankBean> getSelectBank(@FieldMap Map<String, String> map);

    //添加银行卡
    @FormUrlEncoded
    @POST(ADD_BANK)
    Observable<AddBankBean> getAddBank(@FieldMap Map<String, String> map);

    //修改银行卡
    @FormUrlEncoded
    @POST(MODIFY_BANK)
    Observable<ModifyBankBean> getModifyBank(@FieldMap Map<String, String> map);

    //删除银行卡
    @FormUrlEncoded
    @POST(DEL_BANK)
    Observable<DelBankBean> getDelBank(@FieldMap Map<String, String> map);

    //提现到银行卡
    @FormUrlEncoded
    @POST(WITHDRAW_BANK)
    Observable<WithdrawBankBean> getWithdrawBank(@FieldMap Map<String, String> map);

    //金币兑换
    @FormUrlEncoded
    @POST(GOLD_STORE)
    Observable<GoldStoreBean> getGoldStore(@FieldMap Map<String, String> map);

    //钻石兑换
    @FormUrlEncoded
    @POST(DIAMOND_STORE)
    Observable<DiamondStoreBean> getDiamondStore(@FieldMap Map<String, String> map);

    //钻石支付宝充值
    @FormUrlEncoded
    @POST(DIAMOND_PAY)
    Observable<AliPayBean> getDiamondAliPay(@FieldMap Map<String, String> map);

    //钻石微信充值
    @FormUrlEncoded
    @POST(DIAMOND_PAY)
    Observable<WeChatPayBean> getDiamondWeChatPay(@FieldMap Map<String, String> map);

    //vip支付宝充值
    @FormUrlEncoded
    @POST(VIP_PAY)
    Observable<AliPayBean> getVipAliPay(@FieldMap Map<String, String> map);

    //vip微信充值
    @FormUrlEncoded
    @POST(VIP_PAY)
    Observable<WeChatPayBean> getVipWeChatPay(@FieldMap Map<String, String> map);

    //分享
    @FormUrlEncoded
    @POST(SHARE)
    Observable<ShareBean> getShare(@FieldMap Map<String, String> map);

//    //获取用户信息
//    @GET(UrlConfig.GET_USER_INFO)
//    Observable<UserInfoBean> getUserInfo();
//
//    //修改用户信息
//    @FormUrlEncoded
//    @PUT(UrlConfig.GET_USER_INFO)
//    Observable<ModifyPersonalDataBean> getModifyPersonalData(@FieldMap Map<String, String> map);
//
//    //获取用户成长值记录列表
//    @GET(UrlConfig.GROWTH_VALUE_LIST)
//    Observable<GrowthValueBean> getGrowthValueList(@QueryMap Map<String, String> map);
//
//    //获取券列表
//    @GET(UrlConfig.COUPON_LIST)
//    Observable<CouponListBean> getCouponList();
//
//    //券详情中立即使用按钮跳转的URL
//    @GET(UrlConfig.COUPON_DETAILS_BT_URL)
//    Observable<CouponDetailsBtUrlBean> getCouponDetailsBtUrl(@QueryMap Map<String, String> map);
//
//    //获取活动列表
//    @GET(UrlConfig.ACTIVITIES)
//    Observable<AcitvitiesBean> getActivities();
}
