package com.boe.tvpad.view;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.LoginBean;
import com.boe.tvpad.bean.ProgramListBean;

public interface CurrentPlayView {
    void onGetProgramList(ProgramListBean programListBean, String errorMsg);
    void toPlayProgramState(BaseBean baseBean, String errorMsg);
    void controllerState(BaseBean baseBean, String errorMsg);
}
