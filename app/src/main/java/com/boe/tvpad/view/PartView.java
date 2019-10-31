package com.boe.tvpad.view;

import com.boe.tvpad.bean.PartBean;

public interface PartView {
    void onGetPartData(PartBean partBean, String errorMsg);
}
