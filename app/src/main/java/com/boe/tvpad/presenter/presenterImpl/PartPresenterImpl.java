package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.bean.PartBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.PartPresenter;
import com.boe.tvpad.view.PartView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class PartPresenterImpl implements PartPresenter {
    PartView partView;

    public PartPresenterImpl(PartView partView) {
        this.partView = partView;
    }

    @Override
    public void obtainPartData(String partitionId, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("partitionId",partitionId);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.getPartData,this, map,new JsonCallback<PartBean>() {
            @Override
            public void onSuccess(Response<PartBean> response) {
                partView.onGetPartData(response.body(),null);
            }

            @Override
            public void onError(Response<PartBean> response) {
                partView.onGetPartData(response.body(),response.message());
            }
        });
    }
}
