package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.ControllerPresenter;
import com.boe.tvpad.view.CurrentPlayView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class ControllerPresenterImpl implements ControllerPresenter {
    CurrentPlayView currentPlayView;

    public ControllerPresenterImpl(CurrentPlayView currentPlayView) {
        this.currentPlayView = currentPlayView;
    }

    @Override
    public void obtainControllerState(String exhibitsId, String programId, String control, String photoAuto, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("exhibitsId",exhibitsId);
        map.put("programId",programId);
        map.put("control",control);
        map.put("photoAuto",photoAuto);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.controller,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                currentPlayView.controllerState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                currentPlayView.controllerState(response.body(),response.message());
            }
        });
    }
}
