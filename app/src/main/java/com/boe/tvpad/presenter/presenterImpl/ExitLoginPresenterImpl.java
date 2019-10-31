package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.LoginBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.ExitLoginPresenter;
import com.boe.tvpad.view.ListShowView;
import com.boe.tvpad.view.LoginView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class ExitLoginPresenterImpl implements ExitLoginPresenter {
    ListShowView listShowView;

    public ExitLoginPresenterImpl(ListShowView listShowView) {
        this.listShowView = listShowView;
    }

    @Override
    public void obtainExitLoginData(String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.exitLogin,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                listShowView.onGetExitState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                listShowView.onGetExitState(response.body(),response.message());
            }
        });
    }
}
