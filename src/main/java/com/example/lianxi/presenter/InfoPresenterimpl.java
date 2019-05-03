package com.example.lianxi.presenter;

import com.example.lianxi.bean.DbBean;
import com.example.lianxi.bean.InfoBean;
import com.example.lianxi.callback.InfoCallBack;
import com.example.lianxi.model.InfoModel;
import com.example.lianxi.view.InfoView;

public class InfoPresenterimpl implements InfoPresenter, InfoCallBack {
    private InfoModel model;
    private InfoView view;

    public InfoPresenterimpl(InfoModel model, InfoView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void getData(String key, int num, int page) {
        if (model!=null){
            model.getData(key,num,page,this);
        }
    }

    @Override
    public void collect(DbBean bean) {
        if (model!=null){
            model.collect(bean,this);
        }
    }

    @Override
    public void onSuccess(InfoBean bean) {
        if (view!=null){
            view.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String error) {
        if (view!=null){
            view.onFail(error);
        }
    }

    @Override
    public void collectSuccess(String string) {
        if (view!=null){
            view.collectSuccess(string);
        }
    }

    @Override
    public void collectFail(String error) {
        if (view!=null){
            view.collectFail(error);
        }
    }
}
