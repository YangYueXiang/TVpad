package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.PlayProgramPresenter;
import com.boe.tvpad.view.CurrentPlayView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class PlayProgramPresenterImpl implements PlayProgramPresenter {
    CurrentPlayView currentPlayView;

    public PlayProgramPresenterImpl(CurrentPlayView currentPlayView) {
        this.currentPlayView = currentPlayView;
    }

    @Override
    public void toPlayProgramState(String exhibitsId, String programId,String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("exhibitsId",exhibitsId);
        map.put("programId",programId);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.playProgram,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                currentPlayView.toPlayProgramState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                currentPlayView.toPlayProgramState(response.body(),response.message());
            }
        });
    }
}
