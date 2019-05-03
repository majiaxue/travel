package com.example.lianxi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lianxi.R;
import com.example.lianxi.adapter.MyRecycAdapter;
import com.example.lianxi.bean.DbBean;
import com.example.lianxi.bean.InfoBean;
import com.example.lianxi.model.InfoModelimpl;
import com.example.lianxi.presenter.InfoPresenter;
import com.example.lianxi.presenter.InfoPresenterimpl;
import com.example.lianxi.view.InfoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements InfoView, MyRecycAdapter.onItemClick {

    private ArrayList<InfoBean.NewslistBean> list;
    private InfoPresenter presenter;
    private String key="52b7ec3471ac3bec6846577e79f20e4c";
    private int num=20;
    private  int page=1;
    private MyRecycAdapter adapter;
    private XRecyclerView xRecycler;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_info, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        presenter=new InfoPresenterimpl(new InfoModelimpl(),this);
        presenter.getData(key,num,page);
    }

    private void initView(View inflate) {
        xRecycler = (XRecyclerView) inflate.findViewById(R.id.xRecycler);
        list=new ArrayList<>();
        adapter=new MyRecycAdapter(list,getActivity());
        xRecycler.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        xRecycler.setLayoutManager(manager);
        adapter.setOnItemClick(this);
        xRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
                initData();
            }

            @Override
            public void onLoadMore() {
                ++page;
                initData();
            }
        });
    }

    @Override
    public void onSuccess(InfoBean bean) {
        list.addAll(bean.getNewslist());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {

    }

    @Override
    public void collectSuccess(final String string) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),string,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void collectFail(final String error) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItem(int position, InfoBean.NewslistBean bean) {
        DbBean dbBean=new DbBean();
        dbBean.setId(null);
        dbBean.setTitle(bean.getTitle());
        dbBean.setUrl(bean.getPicUrl());
        presenter.collect(dbBean);
    }
}
