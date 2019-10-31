package com.boe.tvpad.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.boe.tvpad.R;
import com.boe.tvpad.base.BaseActivity;
import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.presenter.presenterImpl.ChangePwdPresenterImpl;
import com.boe.tvpad.utils.MD5;
import com.boe.tvpad.utils.SharedPreferencesUtils;
import com.boe.tvpad.utils.ToastMgr;
import com.boe.tvpad.view.ChangePwdView;
import com.umeng.analytics.MobclickAgent;

public class ChangePwdActivity extends BaseActivity implements View.OnClickListener, ChangePwdView {

    private String isfirst;
    private EditText et_oldpwd;
    private EditText et_newpwd;
    private EditText et_confirmpwd;
    private Button btn_confirmchange;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String username;
    private String password;
    private ChangePwdPresenterImpl changePwdPresenter;
    private String newPassword;
    private String confirmPassword;
    private String oldpwd;
    private String padToken;
    private String old;
    private String newpwd;
    private String confirm;
    public static final String PW_PATTERN = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{8,16}$";
    private ImageView img_pwdback;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_change_pwd;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        isfirst = intent.getStringExtra("isfirst");
        if (isfirst.equals("yes")){
            et_oldpwd.setVisibility(View.GONE);
            img_pwdback.setVisibility(View.GONE);
        }else {
            et_oldpwd.setVisibility(View.VISIBLE);
            img_pwdback.setVisibility(View.VISIBLE);
        }
        btn_confirmchange.setOnClickListener(this);
        img_pwdback.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        username = (String) sharedPreferencesUtils.getData("username","");
        password = (String) sharedPreferencesUtils.getData("password", "");
        padToken = (String) sharedPreferencesUtils.getData("padToken", "");
        et_oldpwd = findViewById(R.id.et_oldpwd);
        et_newpwd = findViewById(R.id.et_newpwd);
        et_confirmpwd = findViewById(R.id.et_confirmnewpwd);
        btn_confirmchange = findViewById(R.id.btn_confirmchange);
        img_pwdback = findViewById(R.id.img_pwdback);
        changePwdPresenter = new ChangePwdPresenterImpl(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirmchange:
                oldpwd = et_oldpwd.getText().toString();
                newPassword = et_newpwd.getText().toString();
                confirmPassword = et_confirmpwd.getText().toString();

                old = MD5.getMD5(oldpwd);
                newpwd = MD5.getMD5(newPassword);
                confirm = MD5.getMD5(confirmPassword);

                if (isfirst.equals("yes")){
                    if (newPassword.equals("")&&confirmPassword.equals("")){
                        ToastMgr.show("请输入新密码");
                    }else if (newPassword.equals("")){
                        ToastMgr.show("请输入新密码");
                    }else if (confirmPassword.equals("")){
                        ToastMgr.show("请输入确认密码");
                    }else {
                        //正则校验输入的密码
                        boolean matches = newPassword.matches(PW_PATTERN);
                        if (!matches){
                            ToastMgr.show("密码由8-16位大小写字母，数字或符号任意两种的组合");
                        }else if (!newPassword.equals(confirmPassword)){
                            ToastMgr.show("两次输入密码不一致");
                        }else {
                            changePwdPresenter.obtainChangeData(username,password,newpwd,confirm,padToken);
                        }
                    }
                }else {
                    if (oldpwd.equals("")&&newPassword.equals("")&&confirmPassword.equals("")){
                        ToastMgr.show("请输入原密码");
                    }  else if (oldpwd.equals("")){
                        ToastMgr.show("请输入原密码");
                    }else{
                        String md5 = MD5.getMD5(oldpwd);
                        //判断旧密码是否输入正确
                        if (!md5.equals(password)){
                            ToastMgr.show("原密码输入不正确");
                        }else {
                            if (newPassword.equals("")){
                                ToastMgr.show("请输入新密码");
                            }else {
                                //正则校验输入的密码
                                boolean matches = newPassword.matches(PW_PATTERN);
                                if (!matches){
                                    ToastMgr.show("密码由8-16位大小写字母，数字或符号任意两种的组合");
                                }else {
                                    if (confirmPassword.equals("")){
                                        ToastMgr.show("请输入确认密码");
                                    }else {
                                        if (!newPassword.equals(confirmPassword)){
                                            ToastMgr.show("两次输入密码不一致");
                                        }else {
                                            changePwdPresenter.obtainChangeData(username,old,newpwd,confirm,padToken);
                                        }
                                    }
                                }
                            }
                        }
                        }
                    }
                break;
            case R.id.img_pwdback:
                finish();
                break;
        }
    }

    //修改密码
    @Override
    public void onGetChangePwdData(BaseBean bean, String errorMsg) {
        if (bean.getState().equals("s")){
            ToastMgr.show("修改成功");
            if (isfirst.equals("yes")){
                finish();
            }else {
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }

        }else if (bean.getState().equals("-1")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
