package com.boe.tvpad.presenter.presenterImpl;
import android.util.Log;
import com.boe.tvpad.bean.SlidHollListBean;
import com.boe.tvpad.common.HttpCommon;
import com.boe.tvpad.okgo.JsonCallback;
import com.boe.tvpad.okgo.OkGoUtil;
import com.boe.tvpad.presenter.SlidHollListPresenter;
import com.boe.tvpad.view.ListShowView;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class SlidHollListPresenterImpl implements SlidHollListPresenter {
    ListShowView listShowView;

    public SlidHollListPresenterImpl(ListShowView listShowView) {
        this.listShowView = listShowView;
    }

    @Override
    public void obtainallholls(String padToken) {
        HashMap<String, String> map = new HashMap<>();
        map.put("padToken",padToken);
        OkGoUtil.postRequestJson(HttpCommon.getSlidHollList,this, map,new JsonCallback<SlidHollListBean>() {
            @Override
            public void onSuccess(Response<SlidHollListBean> response) {
                listShowView.onGetSlidHollList(response.body(),null);
            }

            @Override
            public void onError(Response<SlidHollListBean> response) {
                listShowView.onGetSlidHollList(response.body(),response.message());
            }
        });
    }
}
