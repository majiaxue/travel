package com.example.lianxi.view;

import com.example.lianxi.bean.InfoBean;

public interface InfoView {
    void onSuccess(InfoBean bean);
    void onFail(String error);
    void collectSuccess(String string);
    void collectFail(String error);
}
