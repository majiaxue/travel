package com.example.lianxi.db;

import com.example.lianxi.bean.DbBean;
import com.example.lianxi.dao.DaoMaster;
import com.example.lianxi.dao.DaoSession;
import com.example.lianxi.dao.DbBeanDao;

import java.util.List;

public class MyDbHelper {
    private static MyDbHelper helper;
    private final DbBeanDao dao;
    private MyDbHelper(){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(), "judhd.db");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        DaoSession session = daoMaster.newSession();
        dao = session.getDbBeanDao();

    }
    public static MyDbHelper getHelper(){
        if (helper==null){
            synchronized (MyDbHelper.class){
                if (helper==null){
                    helper=new MyDbHelper();
                }
            }
        }
        return helper;
    }

    public long insert(DbBean bean){
        if (!has(bean)){
            dao.insertOrReplace(bean);
        }
        return -1;
    }

    public void delete(DbBean bean){
        dao.delete(bean);
    }

    public List<DbBean> query(){
        return dao.queryBuilder().list();
    }

    public boolean has(DbBean bean){
        List<DbBean> list = dao.queryBuilder().where(DbBeanDao.Properties.Title.eq(bean.getTitle())).list();
        if (list.size()>0&&list!=null){
            return true;
        }
        return false;
    }
}
