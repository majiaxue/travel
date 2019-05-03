package com.example.lianxi.model;

import com.example.lianxi.bean.DbBean;
import com.example.lianxi.callback.InfoCallBack;

public interface InfoModel {
    void getData(String key, int num, int page, InfoCallBack callBack);
    void collect(DbBean bean,InfoCallBack callBack);
}
