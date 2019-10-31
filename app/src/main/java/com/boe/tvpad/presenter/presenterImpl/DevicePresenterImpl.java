package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.DeviceBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.DevicePresenter;
import com.boe.tvpad.view.ListShowView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class DevicePresenterImpl implements DevicePresenter {
    ListShowView listShowView;

    public DevicePresenterImpl(ListShowView listShowView) {
        this.listShowView = listShowView;
    }

    @Override
    public void obtainDeviceData(String roomId, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("roomId",roomId);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.getDeviceData,this, map,new JsonCallback<DeviceBean>() {
            @Override
            public void onSuccess(Response<DeviceBean> response) {
                listShowView.onGetDeviceData(response.body(),null);
            }

            @Override
            public void onError(Response<DeviceBean> response) {
                listShowView.onGetDeviceData(response.body(),response.message());
            }
        });
    }
}
