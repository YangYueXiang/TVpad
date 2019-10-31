package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.bean.PartBean;
import com.boe.tvpad.bean.ProgramListBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.ProgramListPresenter;
import com.boe.tvpad.view.CurrentPlayView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class ProgramListPresenterImpl implements ProgramListPresenter {
    CurrentPlayView currentPlayView;

    public ProgramListPresenterImpl(CurrentPlayView currentPlayView) {
        this.currentPlayView = currentPlayView;
    }

    @Override
    public void obtainProgramList(String exhibitsId, String padToken) {
            HashMap<String, String> map = new HashMap<>();
            map.put("exhibitsId",exhibitsId);
            map.put("padToken",padToken);
            OkGoUtil.postRequestJson(HttpCommon.getProgramList,this, map,new JsonCallback<ProgramListBean>() {
                @Override
                public void onSuccess(Response<ProgramListBean> response) {
                    currentPlayView.onGetProgramList(response.body(),null);
                }

                @Override
                public void onError(Response<ProgramListBean> response) {
                    currentPlayView.onGetProgramList(response.body(),response.message());
                }
            });
    }
}
