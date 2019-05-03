package com.example.lianxi.presenter;

import com.example.lianxi.bean.DbBean;

public interface InfoPresenter {
    void getData(String key,int num,int page);
    void collect(DbBean bean);
}
