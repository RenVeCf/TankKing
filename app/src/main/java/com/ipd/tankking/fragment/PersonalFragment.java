package com.ipd.tankking.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ipd.tankking.R;
import com.ipd.tankking.adapter.ImageSelectGridAdapter;
import com.ipd.tankking.base.BaseFragment;
import com.ipd.tankking.bean.ModifyHeadImgBean;
import com.ipd.tankking.bean.ModifyUserNameBean;
import com.ipd.tankking.common.view.CircleImageView;
import com.ipd.tankking.contract.ModifyPersonalDataContract;
import com.ipd.tankking.presenter.ModifyPersonalDataPresenter;
import com.ipd.tankking.utils.SPUtil;
import com.ipd.tankking.utils.T;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.io.File;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ipd.tankking.common.config.IConstants.AVATAR;
import static com.ipd.tankking.common.config.IConstants.NAME;
import static com.ipd.tankking.common.config.IConstants.PHONE;
import static com.ipd.tankking.common.config.IConstants.USER_ID;

/**
 * Description ：个人资料
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/3.
 */
public class PersonalFragment extends BaseFragment<ModifyPersonalDataContract.View, ModifyPersonalDataContract.Presenter> implements ModifyPersonalDataContract.View {
    @BindView(R.id.civ_head)
    CircleImageView civHead;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public ModifyPersonalDataContract.Presenter createPresenter() {
        return new ModifyPersonalDataPresenter(mContext);
    }

    @Override
    public ModifyPersonalDataContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        Glide.with(this).load(SPUtil.get(getActivity(), AVATAR, "") + "").apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(civHead);
        etName.setText(SPUtil.get(getActivity(), NAME, "") + "");
        tvPhone.setText(SPUtil.get(getActivity(), PHONE, "") + "");
    }

    @Override
    public void initListener() {
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //回车键
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    TreeMap<String, String> modifyUserNameMap = new TreeMap<>();
                    modifyUserNameMap.put("id", SPUtil.get(getActivity(), USER_ID, "") + "");
                    modifyUserNameMap.put("name", etName.getText().toString().trim());
                    getPresenter().getModifyUserName(modifyUserNameMap, true, false);
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }

    public static RequestBody getImageRequestBody(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        return RequestBody.create(MediaType.parse(options.outMimeType), new File(filePath));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    TreeMap<String, RequestBody> map = new TreeMap<>();
                    map.put("image\";filename=\"" + ".png", getImageRequestBody(PictureSelector.obtainMultipleResult(data).get(0).getCompressPath()));
                    getPresenter().getModifyHeadImg(map, SPUtil.get(getActivity(), USER_ID, "") + "", true, false);
                    break;
            }
        }
    }

    @OnClick({R.id.ll_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                PictureSelector.create(PersonalFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
        }
    }

    @Override
    public void resultModifyUserName(ModifyUserNameBean data) {
        SPUtil.put(getActivity(), NAME, data.getData().getMember_name());
    }

    @Override
    public void resultModifyHeadImg(ModifyHeadImgBean data) {
        if (data.getCode().equals("200")) {
            T.Short(data.getMsg(), 1);
            Glide.with(this).load(data.getData()).apply(new RequestOptions()).into(civHead);
            SPUtil.put(getActivity(), AVATAR, data.getData());
        } else
            T.Short(data.getMsg(), 2);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }

    @Override
    public void initImmersionBar() {
    }
}
