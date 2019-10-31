package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.ChangePwdPresenter;
import com.boe.tvpad.view.ChangePwdView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class ChangePwdPresenterImpl implements ChangePwdPresenter {
    ChangePwdView changePwdView;

    public ChangePwdPresenterImpl(ChangePwdView changePwdView) {
        this.changePwdView = changePwdView;
    }

    @Override
    public void obtainChangeData(String username, String oldPassword, String password, String confirmPassword,String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("oldPassword",oldPassword);
        map.put("password",password);
        map.put("confirmPassword",confirmPassword);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.getChangePwdData,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                changePwdView.onGetChangePwdData(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                changePwdView.onGetChangePwdData(response.body(),response.message());
            }
        });
    }
}
