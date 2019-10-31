package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.bean.DeviceBean;
import com.boe.tvpad.bean.LightBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.LightPresenter;
import com.boe.tvpad.view.ListShowView;
import com.boe.tvpad.view.PartView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class LightPresenterImpl implements LightPresenter {
    ListShowView listShowView;

    public LightPresenterImpl(ListShowView listShowView) {
        this.listShowView = listShowView;
    }

    @Override
    public void obtainLightData(String roomId, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("roomId",roomId);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.getLightData,this, map,new JsonCallback<LightBean>() {
            @Override
            public void onSuccess(Response<LightBean> response) {
                listShowView.onGetLightData(response.body(),null);
            }

            @Override
            public void onError(Response<LightBean> response) {
                listShowView.onGetLightData(response.body(),response.message());
            }
        });
    }
}
