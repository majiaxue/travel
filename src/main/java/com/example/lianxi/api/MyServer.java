package com.example.lianxi.api;

import com.example.lianxi.bean.InfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyServer {
    public String URL="http://api.tianapi.com/";
    @GET("wxnew/?")
    Observable<InfoBean>getCall(@Query("key")String key,@Query("num")int num,@Query("page")int page);
}
