package com.example.lianxi.model;

import com.example.lianxi.api.MyServer;
import com.example.lianxi.bean.DbBean;
import com.example.lianxi.bean.InfoBean;
import com.example.lianxi.callback.InfoCallBack;
import com.example.lianxi.db.MyDbHelper;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoModelimpl implements InfoModel {
    @Override
    public void getData(String key, int num, int page, final InfoCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyServer.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<InfoBean> call = myServer.getCall(key, num, page);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InfoBean bean) {
                        if (bean==null){
                            callBack.onFail("数据错误");
                        }else {
                            if (bean!=null&&bean.getCode()==200){
                                callBack.onSuccess(bean);
                            }else {
                                callBack.onFail(bean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void collect(final DbBean bean, final InfoCallBack callBack) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                long insert = MyDbHelper.getHelper().insert(bean);
                if (insert>0){
                    callBack.collectSuccess("收藏成功");
                }else {
                    callBack.onFail("收藏失败");
                }
            }
        }.start();
    }
}
