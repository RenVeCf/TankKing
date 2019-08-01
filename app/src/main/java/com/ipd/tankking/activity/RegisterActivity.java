package com.ipd.tankking.activity;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ipd.tankking.R;
import com.ipd.tankking.base.BaseActivity;
import com.ipd.tankking.bean.CaptchaBean;
import com.ipd.tankking.bean.RegisterBean;
import com.ipd.tankking.contract.RegisterContract;
import com.ipd.tankking.presenter.RegisterPresenter;
import com.ipd.tankking.utils.ApplicationUtil;
import com.ipd.tankking.utils.CountDownUtil;
import com.ipd.tankking.utils.T;
import com.ipd.tankking.utils.VerifyUtils;
import com.ipd.tankking.utils.isClickUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

/**
 * Description ：注册
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/5/31.
 */
public class RegisterActivity extends BaseActivity<RegisterContract.View, RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.tv_get_captcha)
    TextView tvGetCaptcha;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.cb_check)
    CheckBox cbCheck;
    @BindView(R.id.tv_check)
    TextView tvCheck;
    @BindView(R.id.tv_user_pact)
    TextView tvUserPact;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.bt_register)
    Button btRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public RegisterContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    // 用户协议
    private void showPopWindow() {
        final TextView tvText;

        // 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_user_pact, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        PopupWindow window = new PopupWindow(contentView, 1500, 1000, true);
        tvText = contentView.findViewById(R.id.tv_text);
        tvText.setText("特别提示\n\n" +
                "坦克王者在此特别提醒您（用户）在注册成为用户之前，请认真阅读本《用户协议》（以下简称“协议”），确保您充分理解本协议中各条款。请您审慎阅读并选择接受或不接受本协议。除非您接受本协议所有条款，否则您无权注册、登录或使用本协议所涉服务。您的注册、登录、使用等行为将视为对本协议的接受，并同意接受本协议各项条款的约束。\n" +
                "本协议约定坦克王者科技与用户之间关于“坦克王者”软件服务（以下简称“服务”）的权利义务。“用户”是指注册、登录、使用本服务的个人。本协议可由坦克王者科技随时更新，更新后的协议条款一旦公布即代替原来的协议条款，恕不再另行通知，用户可在本网站查阅最新版协议条款。在坦克王者科技修改协议条款后，如果用户不接受修改后的条款，请立即停止使用坦克王者科技提供的服务，用户继续使用坦克王者科技提供的服务将被视为接受修改后的协议。\n" +
                "一、帐号注册\n" +
                "1、用户在使用本服务前需要注册一个“坦克王者”帐号。“坦克王者”帐号应当使用手机号码绑定注册，请用户使用尚未与“坦克王者”帐号绑定的手机号码，以及未被坦克王者科技根据本协议封禁的手机号码注册“坦克王者”帐号。坦克王者科技可以根据用户需求或产品需要对帐号注册和绑定的方式进行变更，而无须事先通知用户。\n" +
                "2、“坦克王者”系基于地理位置的移动社交产品，用户注册时应当授权坦克王者科技公开及使用其地理位置信息方可成功注册“坦克王者”帐号。故用户完成注册即表明用户同意坦克王者科技提取、公开及使用用户的地理位置信息。如用户需要终止向其他用户公开其地理位置信息，可自行设置为隐身状态。\n" +
                "3、鉴于“坦克王者”帐号的绑定注册方式，您同意坦克王者科技在注册时将使用您提供的手机号码及/或自动提取您的手机号码及自动提取您的手机设备识别码等信息用于注册。您同意给予运营商授权，授权运营商有权自动提取您的手机号码进行认证并用于“坦克王者”账号注册，您保证遵守运营商的相关服务条款（点击查看服务条款），如运营商对您的手机号认证成功，则您的注册即完成。如您不同意对运营商的授权和/或服务条款或者是您的手机号认证失败，您可以手动修改运营商提取的手机号码，采取验证码方式进行注册登录。\n" +
                "4、在用户注册及使用本服务时，坦克王者科技需要搜集能识别用户身份的个人信息以便坦克王者科技可以在必要时联系用户，或为用户提供更好的使用体验。坦克王者科技搜集的信息包括但不限于用户的姓名、性别、年龄、出生日期、身份证号、地址、学校情况、公司情况、所属行业、兴趣爱好、常出没的地方、个人说明；坦克王者科技同意对这些信息的使用将受限于第三条用户个人隐私信息保护的约束。\n" +
                "二、服务内容\n" +
                "1、本服务的具体内容由坦克王者科技根据实际情况提供，包括但不限于授权用户通过其帐号进行即时通讯、添加好友、加入群组、关注他人、发布留言。坦克王者科技可以对其提供的服务予以变更，且坦克王者科技提供的服务内容可能随时变更；用户将会收到坦克王者科技关于服务变更的通知。\n" +
                "2、坦克王者科技提供的服务包含免费服务与收费服务。用户可以通过付费方式购买收费服务，具体方式为：用户通过网上银行、支付宝或其他“坦克王者”平台提供的付费途径支付一定数额的人民币购买“坦克王者”平台的虚拟货币——坦克王者币，然后根据坦克王者科技公布的资费标准以坦克王者币购买用户欲使用的收费服务，从而获得收费服务使用权限。对于收费服务，坦克王者科技会在用户使用之前给予用户明确的提示，只有用户根据提示确认其同意按照前述支付方式支付费用并完成了支付行为，用户才能使用该等收费服务。支付行为的完成以银行或第三方支付平台生成“支付已完成”的确认通知为准。\n" +
                "三、用户个人信息保护\n" +
                "1、用户在注册帐号或使用本服务的过程中，可能需要填写或提交一些必要的个人信息，如法律法规、规章规范性文件（以下称“法律法规”）规定的需要填写的身份信息。如用户提交的信息不完整或不符合法律法规的规定，则用户可能无法使用本服务或在使用本服务的过程中受到限制。\n" +
                "2、用户个人信息包括：1）用户自行提供的用户个人信息（如注册时填写的手机号码，电子邮件等个人信息，使用服务时提供的共享信息等）；2）其他方分享的用户个人信息；3）坦克王者科技为提供服务而合法收集的用户必要个人信息（如使用服务时系统自动采集的设备或软件信息，浏览历史信息，通讯时间信息等技术信息，用户开启定位功能并使用服务时的地理位置信息等）。\n" +
                "其中个人隐私信息是指涉及用户个人身份或个人隐私的信息，比如，用户真实姓名、身份证号、手机号码、手机设备识别码、IP地址、用户聊天记录。非个人隐私信息是指用户对本服务的操作状态以及使用习惯等明确且客观反映在坦克王者科技服务器端的基本记录信息、个人隐私信息范围外的其它普通信息，以及用户同意公开的上述隐私信息。坦克王者科技保证在取得用户书面同意的情况下收集、使用或公开用户的个人隐私信息，用户同意坦克王者科技无需获得用户的另行确认与授权即可收集、使用或公开用户的非个人隐私信息。\n" +
                "3、尊重用户个人信息的私有性是坦克王者科技的一贯制度，坦克王者科技将采取技术措施和其他必要措施，确保用户个人信息安全，防止在本服务中收集的用户个人信息泄露、毁损或丢失。在发生前述情形或者坦克王者科技发现存在发生前述情形的可能时，坦克王者科技将及时采取补救措施并告知用户，用户如发现存在前述情形亦需立即与坦克王者科技联系。\n" +
                "4、坦克王者科技未经用户同意不向任何第三方公开、 透露用户个人隐私信息。但以下特定情形除外：\n" +
                "(1) 坦克王者科技根据法律法规规定或有权机关的指示提供用户的个人隐私信息；\n" +
                "(2) 由于用户将其用户密码告知他人或与他人共享注册帐户与密码，由此导致的任何个人信息的泄漏，或其他非因坦克王者科技原因导致的个人隐私信息的泄露；\n" +
                "(3) 用户自行向第三方公开其个人隐私信息；\n" +
                "(4) 用户与坦克王者科技及合作单位之间就用户个人隐私信息的使用公开达成约定，坦克王者科技因此向合作单位公开用户个人隐私信息；\n" +
                "(5) 任何由于黑客攻击、电脑病毒侵入及其他不可抗力事件导致用户个人隐私信息的泄露；\n" +
                "(6) 用户个人信息已经经过处理无法识别特定个人且不能复原。\n" +
                "5、用户同意坦克王者科技可在以下事项中使用用户的个人隐私信息：\n" +
                "(1) 坦克王者科技向用户及时发送重要通知，如软件更新、本协议条款的变更；\n" +
                "(2) 坦克王者科技内部进行审计、数据分析和研究等，以改进坦克王者科技的产品、服务和与用户之间的沟通；\n" +
                "(3) 依本协议约定，坦克王者科技管理、审查用户信息及进行处理措施；\n" +
                "(4) 适用法律法规规定的其他事项。\n" +
                "除上述事项外，如未取得用户事先同意，坦克王者科技不会将用户个人隐私信息使用于任何其他用途。\n" +
                "6、坦克王者科技重视对未成年人的保护。坦克王者科技将依赖用户提供的个人信息判断用户是否为未成年人。任何18岁以下的未成年人均不得注册帐号或使用本服务\n" +
                "7、因坦克王者科技提供的服务系基于地理位置提供的移动社交服务，用户确认，其地理位置信息为非个人隐私信息，用户成功注册“坦克王者”帐号视为确认授权坦克王者科技提取、公开及使用用户的地理位置信息。用户地理位置信息将作为用户公开资料之一，由坦克王者科技向其他用户公开以便坦克王者科技向用户提供基于地理位置的移动社交服务。如用户需要终止向其他用户公开其地理位置信息，可随时自行设置为隐身状态。\n" +
                "8、为了改善坦克王者科技的技术和服务，向用户提供更好的服务体验，坦克王者科技或可会自行收集使用或向第三方提供用户的非个人隐私信息。\n" +
                "9、坦克王者科技保证在合法、正当与必要的原则下收集、使用或者公开用户个人信息且不会收集与提供的服务无关的用户个人信息。\n" +
                "10、坦克王者科技十分注重保护用户的个人隐私，并制定了《坦克王者隐私权政策》（点击查看），用户亦可以通过“设置”页面里的“帮助”来进行具体查看，用户确认并同意使用坦克王者科技提供的服务将被视为接受《坦克王者隐私权政策》。\n" +
                "四、内容规范\n" +
                "1、本条所述内容是指用户使用本服务过程中所制作、上载、复制、发布、传播的任何内容，包括但不限于帐号头像、名称、用户说明等注册信息及认证资料，或文字、语音、图片、视频、图文等发送、回复或自动回复消息和相关链接页面，以及其他使用帐号或本服务所产生的内容。\n" +
                "2、用户不得利用“坦克王者”帐号或本服务制作、上载、复制、发布、传播如下法律、法规和政策禁止的内容：\n" +
                "(1) 反对宪法所确定的基本原则的；\n" +
                "(2) 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；\n" +
                "(3) 损害国家荣誉和利益的；\n" +
                "(4) 煽动民族仇恨、民族歧视，破坏民族团结的；\n" +
                "(5) 破坏国家宗教政策，宣扬邪教和封建迷信的；\n" +
                "(6) 散布谣言，扰乱社会秩序，破坏社会稳定的；\n" +
                "(7) 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；\n" +
                "(8) 侮辱或者诽谤他人，侵害他人合法权益的；\n" +
                "(9) 不遵守法律法规底线、社会主义制度底线、国家利益底线、公民合法权益底线、社会公共秩序底线、道德风尚底线和信息真实性底线的“七条底线”要求的；\n" +
                "(10) 含有法律、行政法规禁止的其他内容的信息。\n" +
                "3、用户不得利用“坦克王者”帐号或本服务制作、上载、复制、发布、传播如下干扰“坦克王者”正常运营，以及侵犯其他用户或第三方合法权益的内容：\n" +
                "(1) 含有任何性或性暗示的；\n" +
                "(2) 含有辱骂、恐吓、威胁内容的；\n" +
                "(3) 含有骚扰、垃圾广告、恶意信息、诱骗信息的；\n" +
                "(4) 涉及他人隐私、个人信息或资料的；\n" +
                "(5) 侵害他人名誉权、肖像权、知识产权、商业秘密等合法权利的；\n" +
                "(6) 含有其他干扰本服务正常运营和侵犯其他用户或第三方合法权益内容的信息。\n" +
                "五、使用规则\n" +
                "1、用户在本服务中或通过本服务所传送、发布的任何内容并不反映或代表，也不得被视为反映或代表坦克王者科技的观点、立场或政策，坦克王者科技对此不承担任何责任。\n" +
                "2、用户不得利用“坦克王者”帐号或本服务进行如下行为：\n" +
                "(1) 提交、发布虚假信息，或盗用他人头像或资料，冒充、利用他人名义的；\n" +
                "(2) 强制、诱导其他用户关注、点击链接页面或分享信息的；\n" +
                "(3) 虚构事实、隐瞒真相以误导、欺骗他人的；\n" +
                "(4) 利用技术手段批量建立虚假帐号的；\n" +
                "(5) 利用“坦克王者”帐号或本服务从事任何违法犯罪活动的；\n" +
                "(6) 制作、发布与以上行为相关的方法、工具，或对此类方法、工具进行运营或传播，无论这些行为是否为商业目的；\n" +
                "(7) 其他违反法律法规规定、侵犯其他用户合法权益、干扰“坦克王者”正常运营或坦克王者科技未明示授权的行为。\n" +
                "3、用户须对利用“坦克王者”帐号或本服务传送信息的真实性、合法性、无害性、准确性、有效性等全权负责，与用户所传播的信息相关的任何法律责任由用户自行承担，与坦克王者科技无关。如因此给坦克王者科技或第三方造成损害的，用户应当依法予以赔偿。\n" +
                "4、坦克王者科技提供的服务中可能包括广告，用户同意在使用过程中显示坦克王者科技和第三方供应商、合作伙伴提供的广告。除法律法规明确规定外，用户应自行对依该广告信息进行的交易负责，对用户因依该广告信息进行的交易或前述广告商提供的内容而遭受的损失或损害，坦克王者科技不承担任何责任。\n" +
                "5、除非坦克王者科技书面许可，用户不得从事下列任一行为：\n" +
                "(1) 删除软件及其副本上关于著作权的信息；\n" +
                "(2) 对软件进行反向工程、反向汇编、反向编译，或者以其他方式尝试发现软件的源代码；\n" +
                "(3) 对坦克王者科技拥有知识产权的内容进行使用、出租、出借、复制、修改、链接、转载、汇编、发表、出版、建立镜像站点等；\n" +
                "(4) 对软件或者软件运行过程中释放到任何终端内存中的数据、软件运行过程中客户端与服务器端的交互数据，以及软件运行所必需的系统数据，进行复制、修改、增加、删除、挂接运行或创作任何衍生作品，形式包括但不限于使用插件、外挂或非经坦克王者科技授权的第三方工具/服务接入软件和相关系统；\n" +
                "(5) 通过修改或伪造软件运行中的指令、数据，增加、删减、变动软件的功能或运行效果，或者将用于上述用途的软件、方法进行运营或向公众传播，无论这些行为是否为商业目的；\n" +
                "(6) 通过非坦克王者科技开发、授权的第三方软件、插件、外挂、系统，登录或使用坦克王者科技软件及服务，或制作、发布、传播非坦克王者科技开发、授权的第三方软件、插件、外挂、系统。\n" +
                "六、虚拟货币\n" +
                "1、坦克王者科技将在“坦克王者”平台发行虚拟货币，即坦克王者币。坦克王者币可用于购买“坦克王者”平台的增值服务，包括但不限于表情服务及会员服务，除此外，不得用于其他任何用途。该等增值服务的价格均以坦克王者币为单位，具体价格信息将由坦克王者科技自行决定并在相关服务页面上显示。\n" +
                "2、坦克王者币和人民币的兑换比例依用户购买渠道的不同而有不同的兑换比例，具体兑换比例以用户购买坦克王者币相关渠道服务页面显示为准。坦克王者科技有权根据运营情况随时变更上述兑换比例，并将在用户购买坦克王者币相关渠道服务页面显示。\n" +
                "3、用户默认已开通坦克王者币账户，可进行坦克王者币购买（下称“充值”）和消费。用户可在设置页面查询到坦克王者币余额、购买记录和消费记录。坦克王者币相关信息将不作为公开信息。\n" +
                "4、用户可以通过网上银行、支付宝或其他“坦克王者”平台提供的充值途径为坦克王者币账户进行充值。用户使用坦克王者币购买相关收费服务后，可将相关收费服务赠与其他用户。用户确认不会以非法方式或者使用非平台所指定的充值途径进行充值,如果用户违规使用非坦克王者科技认可的充值途径非法充值/购买坦克王者币，则坦克王者科技不保证充值顺利或正确完成，若因此造成用户权益受损，坦克王者科技将不会作出任何补偿或赔偿，坦克王者科技同时保留随时终止用户坦克王者账号资格及使用各项充值服务的权利，并进行相应惩罚。\n" +
                "5、用户确认在进行充值前已经仔细确认过自己的账号并仔细选择了相关操作选项，若因用户自身输入账号错误、操作不当或不了解充值计费方式等因素造成充错账号、错选充值种类等情形而损害自身权益的，坦克王者科技将不会作出任何补偿或赔偿。\n" +
                "6、用户确认，坦克王者币一经充值成功，除法律法规明确规定外，在任何情况下不能兑换为法定货币，不能转让他人。除法律法规明确规定外，坦克王者币账户充值完成后，坦克王者科技不予退款。\n" +
                "7、用户确认，坦克王者币只能用于购买“坦克王者”平台上的各类增值服务，任何情况下不得与坦克王者科技以外的第三方进行坦克王者币交易，亦不得在除“坦克王者”平台以外的第三方平台（如淘宝）上进行交易；如违反前述约定，造成用户或第三方任何损失，坦克王者科技不负任何责任，且如坦克王者科技有理由怀疑用户的坦克王者币账户或使用情况有作弊或异常状况，坦克王者科技将拒绝该用户使用坦克王者币进行支付，直至按本协议约定采取相关封禁措施。\n" +
                "8、坦克王者科技有权基于交易安全等方面的考虑不时设定或修改涉及交易的相关事项，包括但不限于交易限额、交易次数等。用户了解并确认坦克王者科技的前述设定或修改可能对用户的交易产生一定的不便，用户对此没有异议。\n" +
                "9、用户确认，除法律法规明确规定或本协议另有约定外，用户已购买的任何收费服务不能以任何理由退购（即退换成坦克王者币或法定货币）或调换成其他服务。\n" +
                "10、因用户自身的原因导致坦克王者科技无法提供坦克王者币购买服务或提供坦克王者币购买服务时发生任何错误而产生的任何损失或责任，由用户自行负责，坦克王者科技不承担责任，包括但不限于：\n" +
                "(1) 因用户的坦克王者账号丢失、被封禁或冻结；\n" +
                "(2) 用户将密码告知他人导致的财产损失；\n" +
                "(3) 因用户绑定的第三方支付机构账户的原因导致的任何损失或责任；\n" +
                "(4) 其他用户故意或者重大过失或者违反法律法规导致的财产损失。\n" +
                "11、用户在使用坦克王者科技提供的服务时，如出现违反国家法律法规、本协议约定或其他本平台对用户的管理规定的情形，坦克王者科技有权暂时或永久封禁用户的账号。账号封禁后至解禁（如有）前，用户账户上的剩余坦克王者币将被暂时冻结或全部扣除，不可继续用于购买平台上的虚拟产品或服务，同时不予返还用户购买坦克王者币时的现金价值。\n" +
                "12、用户确认并同意如用户主动注销账号，则用户已充值到账的坦克王者币，购买的虚拟礼物，游戏币以及会员权益等视为自动放弃，坦克王者科技不予返还相应的现金价值，也不会作出任何补偿。\n" +
                "七、账户管理\n" +
                "1、 “坦克王者”帐号的所有权归坦克王者科技所有，用户完成申请注册手续后，获得“坦克王者”帐号的使用权，该使用权仅属于初始申请注册人，禁止赠与、借用、租用、转让或售卖。坦克王者科技因经营需要，有权回收用户的“坦克王者”帐号。\n" +
                "2、用户可以通过1）查看与编辑个人资料页，2）“设置”页面里的“账号与安全”页面来查询、更改、删除、注销“坦克王者”帐户上的个人资料、注册信息及传送内容等，但需注意，删除有关信息的同时也会删除用户储存在系统中的文字和图片。用户需承担该风险。\n" +
                "3、用户有责任妥善保管注册帐号信息及帐号密码的安全，因用户保管不善可能导致遭受盗号或密码失窃，责任由用户自行承担。用户需要对注册帐号以及密码下的行为承担法律责任。用户同意在任何情况下不使用其他用户的帐号或密码。在用户怀疑他人使用其帐号或密码时，用户同意立即通知坦克王者科技。\n" +
                "4、用户应遵守本协议的各项条款，正确、适当地使用本服务，如因用户违反本协议中的任何条款，坦克王者科技在通知用户后有权依据协议中断或终止对违约用户“坦克王者”帐号提供服务。同时，坦克王者科技保留在任何时候收回“坦克王者”帐号、用户名的权利。\n" +
                "5、如用户注册“坦克王者”帐号后一年不登录，通知用户后，坦克王者科技可以收回该帐号，以免造成资源浪费，由此造成的不利后果由用户自行承担。\n" +
                "6、用户可以通过“设置”页面里的“账号与安全”页面来进行账号注销服务，用户确认注销账号是不可恢复的操作，用户应自行备份与坦克王者账号相关的信息和数据，用户确认操作之前与坦克王者账号相关的所有服务均已进行妥善处理。用户确认并同意注销账号后并不代表本坦克王者账号注销前的账号行为和相关责任得到豁免或减轻，如在注销期间，用户的账号被他人投诉、被国家机关调查或者正处于诉讼、仲裁程序中，坦克王者科技有限自行终止用户的账号注销并无需另行得到用户的同意。\n" +
                "八、数据储存\n" +
                "1、坦克王者科技不对用户在本服务中相关数据的删除或储存失败负责。\n" +
                "2、坦克王者科技可以根据实际情况自行决定用户在本服务中数据的最长储存期限，并在服务器上为其分配数据最大存储空间等。用户可根据自己的需要自行备份本服务中的相关数据。\n" +
                "3、如用户停止使用本服务或本服务终止，坦克王者科技可以从服务器上永久地删除用户的数据。本服务停止、终止后，坦克王者科技没有义务向用户返还任何数据。\n" +
                "九、风险承担\n" +
                "1、用户理解并同意，“坦克王者”仅为用户提供信息分享、传送及获取的平台，用户必须为自己注册帐号下的一切行为负责，包括用户所传送的任何内容以及由此产生的任何后果。用户应对“坦克王者”及本服务中的内容自行加以判断，并承担因使用内容而引起的所有风险，包括因对内容的正确性、完整性或实用性的依赖而产生的风险。坦克王者科技无法且不会对因用户行为而导致的任何损失或损害承担责任。\n" +
                "如果用户发现任何人违反本协议约定或以其他不当的方式使用本服务，请立即向坦克王者科技举报或投诉，举报或投诉电话为028-62836666，坦克王者科技将依本协议约定进行处理。\n" +
                "2、用户理解并同意，因业务发展需要，坦克王者科技保留单方面对本服务的全部或部分服务内容变更、暂停、终止或撤销的权利，用户需承担此风险。\n" +
                "十、知识产权声明\n" +
                "1、除本服务中涉及广告的知识产权由相应广告商享有外，坦克王者科技在本服务中提供的内容（包括但不限于网页、文字、图片、音频、视频、图表等）的知识产权均归坦克王者科技所有，但用户在使用本服务前对自己发布的内容已合法取得知识产权的除外。\n" +
                "2、除另有特别声明外，坦克王者科技提供本服务时所依托软件的著作权、专利权及其他知识产权均归坦克王者科技所有。\n" +
                "3、坦克王者科技在本服务中所涉及的图形、文字或其组成，以及其他坦克王者科技标志及产品、服务名称（以下统称“坦克王者科技标识”），其著作权或商标权归坦克王者科技所有。未经坦克王者科技事先书面同意，用户不得将坦克王者科技标识以任何方式展示或使用或作其他处理，也不得向他人表明用户有权展示、使用、或其他有权处理坦克王者科技标识的行为。\n" +
                "4、上述及其他任何坦克王者科技或相关广告商依法拥有的知识产权均受到法律保护，未经坦克王者科技或相关广告商书面许可，用户不得以任何形式进行使用或创造相关衍生作品。\n" +
                "5、用户在使用坦克王者服务时发表上传的文字、图片、视频、音频、软件以及表演等信息，此部分信息的知识产权归用户，责任由用户承担。但用户的发表、上传行为视为对坦克王者科技的授权，用户理解并同意授予坦克王者科技及其关联公司全球范围内完全免费、不可撤销、独家、永久、可转授权和可再许可的权利，包括但不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及《著作权法》规定的由著作权人享有的其他著作财产权利及邻接权利。坦克王者科技可自行选择是否使用以及使用方式，包括但不限于将前述信息在坦克王者科技旗下的服务平台上使用与传播，将上述信息再次编辑后使用，以及由坦克王者科技授权给合作方使用、编辑与传播等。\n" +
                "十一、法律责任\n" +
                "1、如果坦克王者科技发现或收到他人举报或投诉用户违反本协议约定的，坦克王者科技有权不经通知随时对相关内容，包括但不限于用户资料、聊天记录进行审查、删除，并视情节轻重对违规帐号处以包括但不限于警告、帐号封禁 、设备封禁 、功能封禁 的处罚，且通知用户处理结果。\n" +
                "2、因违反用户协议被封禁的用户，可以自行到 http://immomo.com/my 查询封禁期限，并在封禁期限届满后自助解封。其中，被实施功能封禁的用户会在封禁期届满后自动恢复被封禁功能。被封禁用户可向坦克王者科技网站相关页面提交申诉，坦克王者科技将对申诉进行审查，并自行合理判断决定是否变更处罚措施。\n" +
                "3、用户理解并同意，坦克王者科技有权依合理判断对违反有关法律法规或本协议规定的行为进行处罚，对违法违规的任何用户采取适当的法律行动，并依据法律法规保存有关信息向有关部门报告等，用户应承担由此而产生的一切法律责任。\n" +
                "4、用户理解并同意，因用户违反本协议约定，导致或产生的任何第三方主张的任何索赔、要求或损失，包括合理的律师费，用户应当赔偿坦克王者科技与合作公司、关联公司，并使之免受损害。\n" +
                "十二、不可抗力及其他免责事由\n" +
                "1、用户理解并确认，在使用本服务的过程中，可能会遇到不可抗力等风险因素，使本服务发生中断。不可抗力是指不能预见、不能克服并不能避免且对一方或双方造成重大影响的客观事件，包括但不限于自然灾害如洪水、地震、瘟疫流行和风暴等以及社会事件如战争、动乱、政府行为等。出现上述情况时，坦克王者科技将努力在第一时间与相关单位配合，及时进行修复，但是由此给用户或第三方造成的损失，坦克王者科技及合作单位在法律允许的范围内免责。\n" +
                "2、本服务同大多数互联网服务一样，受包括但不限于用户原因、网络服务质量、社会环境等因素的差异影响，可能受到各种安全问题的侵扰，如他人利用用户的资料，造成现实生活中的骚扰；用户下载安装的其它软件或访问的其他网站中含有“特洛伊木马”等病毒，威胁到用户的计算机信息和数据的安全，继而影响本服务的正常使用等等。用户应加强信息安全及使用者资料的保护意识，要注意加强密码保护，以免遭致损失和骚扰。\n" +
                "3、用户理解并确认，本服务存在因不可抗力、计算机病毒或黑客攻击、系统不稳定、用户所在位置、用户关机以及其他任何技术、互联网络、通信线路原因等造成的服务中断或不能满足用户要求的风险，因此导致的用户或第三方任何损失，坦克王者科技不承担任何责任。\n" +
                "4、用户理解并确认，在使用本服务过程中存在来自任何他人的包括误导性的、欺骗性的、威胁性的、诽谤性的、令人反感的或非法的信息，或侵犯他人权利的匿名或冒名的信息，以及伴随该等信息的行为，因此导致的用户或第三方的任何损失，坦克王者科技不承担任何责任。\n" +
                "5、用户理解并确认，坦克王者科技需要定期或不定期地对“坦克王者”平台或相关的设备进行检修或者维护，如因此类情况而造成服务在合理时间内的中断，坦克王者科技无需为此承担任何责任，但坦克王者科技应事先进行通告。\n" +
                "6、坦克王者科技依据法律法规、本协议约定获得处理违法违规或违约内容的权利，该权利不构成坦克王者科技的义务或承诺，坦克王者科技不能保证及时发现违法违规或违约行为或进行相应处理。\n" +
                "7、用户理解并确认，对于坦克王者科技向用户提供的下列产品或者服务的质量缺陷及其引发的任何损失，坦克王者科技无需承担任何责任：\n" +
                "(1) 坦克王者科技向用户免费提供的服务；\n" +
                "(2) 坦克王者科技向用户赠送的任何产品或者服务。\n" +
                "8、在任何情况下，坦克王者科技均不对任何间接性、后果性、惩罚性、偶然性、特殊性或刑罚性的损害，包括因用户使用“坦克王者”或本服务而遭受的利润损失，承担责任（即使坦克王者科技已被告知该等损失的可能性亦然）。尽管本协议中可能含有相悖的规定，坦克王者科技对用户承担的全部责任，无论因何原因或何种行为方式，始终不超过用户因使用坦克王者科技提供的服务而支付给坦克王者科技的费用(如有)。\n" +
                "十三、服务的变更、中断、终止\n" +
                "1、鉴于网络服务的特殊性，用户同意坦克王者科技有权随时变更、中断或终止部分或全部的服务（包括收费服务）。坦克王者科技变更、中断或终止的服务，坦克王者科技应当在变更、中断或终止之前通知用户，并应向受影响的用户提供等值的替代性的服务；如用户不愿意接受替代性的服务，如果该用户已经向坦克王者科技支付的坦克王者币，坦克王者科技应当按照该用户实际使用服务的情况扣除相应坦克王者币之后将剩余的坦克王者币退还用户的坦克王者币账户中。\n" +
                "2、如发生下列任何一种情形，坦克王者科技有权变更、中断或终止向用户提供的免费服务或收费服务，而无需对用户或任何第三方承担任何责任：\n" +
                "(1) 根据法律规定用户应提交真实信息，而用户提供的个人资料不真实、或与注册时信息不一致又未能提供合理证明；\n" +
                "(2) 用户违反相关法律法规或本协议的约定；\n" +
                "(3) 按照法律规定或有权机关的要求；\n" +
                "(4) 出于安全的原因或其他必要的情形。\n" +
                "十四、“附近活动”服务说明与免责条款\n" +
                "1、坦克王者“附近活动”功能板块，是以地理位置为基础、发布用户周边文化活动信息的活动信息共享平台（下称“本平台”）。\n" +
                "2、本平台发布之全部文化活动信息,包括但不限于活动地理位置信息，均直接或者间接来自于文化活动主办方。\n" +
                "3、本平台发布文化活动信息，意在宣传文化活动、丰富用户文化生活。本平台用户于本平台“附近活动”功能板块发布的留言、评论等信息，均系用户自行发布。本平台不对上述信息的真实性、准确性，或及时性、完整性负责。\n" +
                "4、如果相关文化活动主办方及/或相关权利主体，认为该等文化活动信息之发布，侵害其合法权益，可直接联系平台客服。本平台在核实其提供的证明材料后，将依法履行网络平台发布者的义务，维护其合法权益。\n" +
                "十五、其他\n" +
                "1、坦克王者科技郑重提醒用户注意本协议中免除坦克王者科技责任和限制用户权利的条款，请用户仔细阅读，自主考虑风险\n" +
                "2、本协议的效力、解释及纠纷的解决，适用于中华人民共和国法律。若用户和坦克王者科技之间发生任何纠纷或争议，首先应友好协商解决，协商不成的，用户同意将纠纷或争议提交坦克王者科技住所地有管辖权的人民法院管辖。\n" +
                "3、本协议的任何条款无论因何种原因无效或不具可执行性，其余条款仍有效，对双方具有约束力。\n" +
                "4、由于互联网高速发展，您与坦克王者科技签署的本协议列明的条款可能并不能完整罗列并覆盖您与坦克王者所有权利与义务，现有的约定也不能保证完全符合未来发展的需求。因此，坦克王者隐私权政策、坦克王者平台行为规范等均为本协议的补充协议，与本协议不可分割且具有同等法律效力。如您使用坦克王者平台服务，视为您同意上述补充协议。\n\n");
        // 设置PopupWindow的背景
//        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bg_user_pact)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(view, 0, 0);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        // window.showAtLocation(parent, gravity, x, y);
        window.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        window.setAnimationStyle(R.style.animTranslate);
    }

    @OnClick({R.id.tv_get_captcha, R.id.tv_user_pact, R.id.tv_check, R.id.ib_back, R.id.bt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_captcha:
                if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                    TreeMap<String, String> captchaMap = new TreeMap<>();
                    captchaMap.put("mobile", etPhone.getText().toString().trim());
                    getPresenter().getCaptcha(captchaMap, false, false);
                }
                break;
            case R.id.tv_user_pact:
                showPopWindow();
                break;
            case R.id.tv_check:
                if (cbCheck.isChecked())
                    cbCheck.setChecked(false);
                else
                    cbCheck.setChecked(true);
                break;
            case R.id.ib_back:
                if (this instanceof Activity && isClickUtil.isFastClick()) {
                    this.finish();
                    if (this.getCurrentFocus() != null) {
                        ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            case R.id.bt_register:
                if (cbCheck.isChecked()) {
                    if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                        TreeMap<String, String> registerMap = new TreeMap<>();
                        registerMap.put("mobile", etPhone.getText().toString().trim());
                        registerMap.put("code", etCaptcha.getText().toString().trim());
                        registerMap.put("password", etPwd.getText().toString().trim());
                        registerMap.put("sub", "");
                        getPresenter().getRegister(registerMap, false, false);
                    } else {
                        T.Long("手机号码格式错误", 2);
                    }
                } else
                    T.Long("请勾选用户注册协议", 0);
                break;
        }
    }

    @Override
    public void resultRegister(RegisterBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            finish();
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public void resultCaptcha(CaptchaBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            //验证码倒计时60内不能重新发送
            new CountDownUtil(tvGetCaptcha)
                    .setCountDownMillis(60_000L)//倒计时60000ms
                    .setCountDownColor(R.color.get_pwd, R.color.white)//不同状态字体颜色
                    .setOnClickListener(new View.OnClickListener() {
                        //重新获取验证码
                        @Override
                        public void onClick(View v) {
                            if (etPhone.getText().toString().trim().length() == 11 && VerifyUtils.isMobileNumber(etPhone.getText().toString().trim())) {
                                TreeMap<String, String> captchaMap = new TreeMap<>();
                                captchaMap.put("mobile", etPhone.getText().toString().trim());
                                getPresenter().getCaptcha(captchaMap, false, false);
                            } else {
                                T.Short("请检查手机号码", 0);
                            }
                        }
                    })
                    .start();
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
