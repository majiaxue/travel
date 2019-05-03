package com.example.lianxi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.lianxi.adapter.MyPagerAdapter;
import com.example.lianxi.fragment.CollectFragment;
import com.example.lianxi.fragment.InfoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Toolbar toolbar;
    private TabLayout tab;
    private ViewPager vp;
    private MyPagerAdapter adapter;
    private ArrayList<Fragment> list;
    private ArrayList<String> title;
    private String[] titles={"首页","收藏"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        list=new ArrayList<>();
        list.add(new InfoFragment());
        list.add(new CollectFragment());
        title=new ArrayList<>();
        title.add("首页");
        title.add("收藏");
        adapter=new MyPagerAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tv.setText(titles[0]);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
                tv.setText(titles[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }
}
