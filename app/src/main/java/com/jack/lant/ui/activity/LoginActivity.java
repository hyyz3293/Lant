package com.jack.lant.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jack.lant.R;
import com.jack.lant.base.AppManager;
import com.jack.lant.base.BaseActivity;
import com.jack.lant.ui.model.UserInfoEntity;
import com.jack.lant.ui.view.PhoneExtendEditText;
import com.jack.lant.utils.SoftKeyBoardListener;
import com.jack.lant.utils.StringUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private PhoneExtendEditText mEtUser;
    private PhoneExtendEditText mEtPwd;
    private TextView mTvLogin;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        super.initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.white));
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        mEtUser = findViewById(R.id.etLoginInnerPhoneNumber);
        mEtPwd = findViewById(R.id.etLoginInnerPhonePasss);
        mTvLogin = findViewById(R.id.btnLoginHint);

    }

    @Override
    public void initData() {
        super.initData();
        UserInfoEntity entity = AppManager.getInstance().getUserInfoEntity();
        if (entity != null) {
            mEtUser.setText(StringUtils.valueOf(entity.user));
            mEtPwd.setText(StringUtils.valueOf(entity.pwd));
            loginStatus();
        }
    }

    @Override
    public void initListener() {
        super.initListener();

        mEtUser.setOnClickListener(this);
        mEtPwd.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);

        mEtUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEtUser.setBackgroundResource(R.drawable.shape_round_rec_dark_gary_btn_bg);
                    if (!StringUtils.isBlank(mEtUser.getText().toString())) {
                        mEtUser.setDrawableVisible(true);
                    }
                } else {
                    mEtUser.setBackgroundResource(R.drawable.shape_round_rec_light_gary_btn_bg);
                    mEtUser.setDrawableVisible(false);
                }
            }
        });

        mEtPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEtPwd.setBackgroundResource(R.drawable.shape_round_rec_dark_gary_btn_bg);
                    if (!StringUtils.isBlank(mEtPwd.getText().toString())) {
                        mEtPwd.setDrawableVisible(true);
                    }
                } else {
                    mEtPwd.setBackgroundResource(R.drawable.shape_round_rec_light_gary_btn_bg);
                    mEtPwd.setDrawableVisible(false);
                }
            }
        });

        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Log.d("Login", "键盘弹出");

            }

            @Override
            public void keyBoardHide(int height) {
                Log.d("Login", "键盘收回");
                loginStatus();
            }
        });

    }

    /** 登录状态 */
    private void loginStatus() {
        String user = mEtUser.getText().toString();
        String pwd = mEtPwd.getText().toString();
        if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(pwd)) {
            mTvLogin.setEnabled(true);
            mTvLogin.setBackgroundResource(R.drawable.shape_round_rectangle_orange_light_real);
        } else {
            mTvLogin.setEnabled(false);
            mTvLogin.setBackgroundResource(R.drawable.shape_round_rectangle_orange_light);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etLoginInnerPhoneNumber:
                mEtUser.setBackgroundResource(R.drawable.shape_round_rec_dark_gary_btn_bg);
                break;
            case R.id.etLoginInnerPhonePasss:
                mEtPwd.setBackgroundResource(R.drawable.shape_round_rec_dark_gary_btn_bg);
                break;
            case R.id.btnLoginHint:
                loginBtn();
                break;
        }
    }

    /** 登录 */
    private void loginBtn() {
        String user = mEtUser.getText().toString();
        String pwd = mEtPwd.getText().toString();
        if (StringUtils.isBlank(user)) {
            ToastUtils.showShort("请输入用户名");
            return;
        }
        if (StringUtils.isBlank(pwd)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        AppManager.getInstance().setUserInfoEntity(user, pwd);
        mTvLogin.setClickable(false);
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
