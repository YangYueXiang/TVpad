package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.DeviceBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.DeleteProgramPresenter;
import com.boe.tvpad.view.DeleteProgramView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class DeleteProgramPresenterImpl implements DeleteProgramPresenter {
    DeleteProgramView deleteProgramView;

    public DeleteProgramPresenterImpl(DeleteProgramView deleteProgramView) {
        this.deleteProgramView = deleteProgramView;
    }

    @Override
    public void obtainDeleteState(String programId, String exhibitsId, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("programId",programId);
        map.put("exhibitsId",exhibitsId);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.deleteProgram,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                deleteProgramView.onGetDeleteState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                deleteProgramView.onGetDeleteState(response.body(),response.message());
            }
        });
    }
}
