package com.boe.tvpad.presenter.presenterImpl;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.LoginBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.NoticeOpenOrClosePresenter;
import com.boe.tvpad.view.NoticeOpenOrCloseView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class NoticeOpenOrClosePresenterImpl implements NoticeOpenOrClosePresenter {
    NoticeOpenOrCloseView noticeOpenOrCloseView;

    public NoticeOpenOrClosePresenterImpl(NoticeOpenOrCloseView noticeOpenOrCloseView) {
        this.noticeOpenOrCloseView = noticeOpenOrCloseView;
    }

    @Override
    public void obtainOpenOrCloseState(String roomId, String partitionId, String id, String state, String type, String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("roomId",roomId);
        map.put("partitionId",partitionId);
        map.put("id",id);
        map.put("state",state);
        map.put("type",type);
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.noticeOpenOrCloseDeviceOrLight,this, map,new JsonCallback<BaseBean>() {
            @Override
            public void onSuccess(Response<BaseBean> response) {
                noticeOpenOrCloseView.onGetNoticeOpenOrCloseState(response.body(),null);
            }

            @Override
            public void onError(Response<BaseBean> response) {
                noticeOpenOrCloseView.onGetNoticeOpenOrCloseState(response.body(),response.message());
            }
        });
    }
}
