package com.boe.tvpad.view;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.DeviceBean;
import com.boe.tvpad.bean.LightBean;
import com.boe.tvpad.bean.SlidHollListBean;

public interface ListShowView {
    void onGetSlidHollList(SlidHollListBean slidHollListBean, String errorMsg);
    void onGetExitState(BaseBean baseBean, String errorMsg);
    void onGetDeviceData(DeviceBean deviceBean, String errorMsg);
    void onGetLightData(LightBean lightBean, String errorMsg);
}
