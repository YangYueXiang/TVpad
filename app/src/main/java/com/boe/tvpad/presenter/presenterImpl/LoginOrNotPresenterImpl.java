package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.DeviceBean;
import com.boe.tvpad.bean.LoginBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.LoginOrNotPresenter;
import com.boe.tvpad.presenter.LoginPresenter;
import com.boe.tvpad.view.LoginView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class LoginOrNotPresenterImpl implements LoginOrNotPresenter {
    LoginView loginView;

    public LoginOrNotPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void obtainLoginState(String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.needOrNotLogin,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                loginView.onGetLoginState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                loginView.onGetLoginState(response.body(),response.message());
            }
        });
    }
}
