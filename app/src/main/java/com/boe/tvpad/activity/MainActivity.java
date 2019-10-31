package com.boe.tvpad.activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.boe.tvpad.R;
import com.boe.tvpad.base.BaseActivity;
import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.LoginBean;
import com.boe.tvpad.presenter.presenterImpl.LoginOrNotPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.LoginPresenterImpl;
import com.boe.tvpad.utils.MD5;
import com.boe.tvpad.utils.SharedPreferencesUtils;
import com.boe.tvpad.utils.ToastMgr;
import com.boe.tvpad.view.LoginView;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends BaseActivity implements LoginView, View.OnClickListener {
    private EditText et_uername;
    private EditText et_password;
    private Button btn_login;
    private LoginPresenterImpl loginPresenter;
    private String username;
    private String password;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String md5pwd;
    private LoginOrNotPresenterImpl loginOrNotPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        String padToken = (String) sharedPreferencesUtils.getData("padToken", "");
        if (!padToken.equals("")){
            //跳转主页面
            Intent intent =new Intent(this,ListShowActivity.class);
            startActivity(intent);
            finish();
        }
        et_uername = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        loginPresenter = new LoginPresenterImpl(this);
        loginOrNotPresenter = new LoginOrNotPresenterImpl(this);
    //   loginOrNotPresenter.obtainLoginState(padToken);
        setDrawableLeft(et_uername,R.drawable.icon_account);
        setDrawableLeft(et_password,R.drawable.icon_password);
    }

    private void setDrawableLeft(EditText editText, int res) {
        Drawable drawable = getResources().getDrawable(res);
        drawable.setBounds(0,0,33,33);
        editText.setCompoundDrawables(drawable,null,null,null);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                username = et_uername.getText().toString();
                password = et_password.getText().toString();
                if (username.equals("")||password.equals("")){
                    ToastMgr.show("用户名或密码不能为空");
                }else {
                    md5pwd = MD5.getMD5(password);
                    //调接口
                    loginPresenter.obtainLoginData(username, md5pwd);
                }
                break;
        }
    }

    //登录成功
    @Override
    public void onGetLoginData(LoginBean bean, String errorMsg) {
        if (bean.getState().equals("s")){
            //登录成功保存用户名和密码
            sharedPreferencesUtils.putData("username",username);
            sharedPreferencesUtils.putData("password",md5pwd);
            sharedPreferencesUtils.putData("name",bean.getName());
            sharedPreferencesUtils.putData("padToken",bean.getPadToken());
            if (bean.getChangePassword().equals("1")){
                //首次登录修改密码
                Intent intent=new Intent(this, ChangePwdActivity.class);
                intent.putExtra("isfirst","yes");
                startActivity(intent);
            }else {
                //跳转主页面
                Intent intent =new Intent(this,ListShowActivity.class);
                startActivity(intent);
                finish();
            }
        }else {
            ToastMgr.show("用户名或密码错误");
        }
    }

    @Override
    public void onGetLoginState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")){
            ToastMgr.show("需要登录");
        }else {
            Intent intent=new Intent(this, ListShowActivity.class);
            startActivity(intent);
            finish();
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
