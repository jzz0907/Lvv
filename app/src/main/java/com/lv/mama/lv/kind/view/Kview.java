package com.lv.mama.lv.kind.view;

import com.lv.mama.lv.kind.bean.DataleftBean;
import com.lv.mama.lv.kind.bean.DatarightBean;
import com.lv.mama.lv.kind.bean.DateGridBean;

/**
 * Created by 景瑾 on 2017/11/14.
 */

public interface Kview {
    void onLoginSuccess(DataleftBean a);
    void onLoginFailed(DatarightBean pageBean);
    void onLogin(DateGridBean pageBean);

}
