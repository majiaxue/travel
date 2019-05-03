package com.example.lianxi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lianxi.R;
import com.example.lianxi.adapter.MyRecycAdapter2;
import com.example.lianxi.bean.DbBean;
import com.example.lianxi.db.MyDbHelper;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {

    private MyRecycAdapter2 adapter2;
    private ArrayList<DbBean> list=new ArrayList<>();
    private XRecyclerView xRecycler;

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_collect, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            initData();
        }else {
            list.clear();
        }
    }

    private void initData() {
        List<DbBean> query = MyDbHelper.getHelper().query();
        list.addAll(query);
        adapter2.setList(list);
        adapter2.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        xRecycler = (XRecyclerView) inflate.findViewById(R.id.xRecycler);
        adapter2=new MyRecycAdapter2(list,getActivity());
        xRecycler.setAdapter(adapter2);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        xRecycler.setLayoutManager(manager);
        adapter2.setOnItemClick(new MyRecycAdapter2.onItemClick() {
            @Override
            public void onItem(int position, DbBean bean) {
                MyDbHelper.getHelper().delete(bean);
                list.remove(position);
                adapter2.setList(list);
                adapter2.notifyDataSetChanged();
            }
        });
    }
}
