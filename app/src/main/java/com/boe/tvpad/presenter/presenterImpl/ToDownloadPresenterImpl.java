package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.ProgramListBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.ToDownloadPresenter;
import com.boe.tvpad.view.ToDownloadView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class ToDownloadPresenterImpl implements ToDownloadPresenter {
    ToDownloadView toDownloadView;

    public ToDownloadPresenterImpl(ToDownloadView toDownloadView) {
        this.toDownloadView = toDownloadView;
    }

    @Override
    public void obtainallholls(String roomId, String partitionId,String id, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("roomId",roomId);
        map.put("partitionId",partitionId);
        map.put("id",id);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.todownload,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                toDownloadView.onGetToDownloadState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                toDownloadView.onGetToDownloadState(response.body(),response.message());
            }
        });
    }
}
