package com.boe.tvpad.view;

import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.LoginBean;

public interface LoginView {
    void onGetLoginData(LoginBean bean, String errorMsg);
    void onGetLoginState(BaseBean baseBean, String errorMsg);
}
