package com.example.lianxi.callback;

import com.example.lianxi.bean.InfoBean;

public interface InfoCallBack {
    void onSuccess(InfoBean bean);
    void onFail(String error);
    void collectSuccess(String string);
    void collectFail(String error);
}
