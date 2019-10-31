package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.bean.LoginBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.LoginPresenter;
import com.boe.tvpad.view.LoginView;
import com.lzy.okgo.model.Response;
import java.util.HashMap;

public class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void obtainLoginData(String username,String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("operator","pad");
        OkGoUtil.postRequestJson(HttpCommon.getLoginData,this, map,new JsonCallback<LoginBean>() {
            @Override
            public void onSuccess(Response<LoginBean> response) {
                loginView.onGetLoginData(response.body(),null);
            }

            @Override
            public void onError(Response<LoginBean> response) {
                loginView.onGetLoginData(response.body(),response.message());
            }
        });
    }
}
