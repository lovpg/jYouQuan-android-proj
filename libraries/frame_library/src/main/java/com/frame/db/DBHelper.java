package com.frame.db;

import android.content.Context;

import com.frame.db.exception.DbException;
import com.frame.db.sqlite.Selector;
import com.frame.db.sqlite.WhereBuilder;

import java.util.List;

/**
 * Created by Administrator on 2015/8/20.
 */
public class DBHelper {
    private DbUtils db;
    public DBHelper(Context context,String name,int version, final List<Class> list, final Class clazz) throws DbException {
        DbUtils.DaoConfig daoConfig = new DbUtils.DaoConfig(context);
        daoConfig.setDbName(name);
        daoConfig.setDbVersion(version);
        daoConfig.setDbUpgradeListener(new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils db, int oldVersion, int newVersion) throws DbException {
                List<Object> objects = db.findAll(clazz);
                delteTable(list, db);
                createTable(list,db);
                db.saveOrUpdateAll(objects);
            }
        });
        db = DbUtils.create(daoConfig);
        createTable(list,db);
        db.configAllowTransaction(true);
    }

    public void createTable(List<Class> list,DbUtils db) throws DbException {
        for (Class clazz : list){
            db.createTableIfNotExist(clazz);
        }
    }

    public DbUtils getDb(){
        return db;
    }

    public void delteTable(List<Class> list,DbUtils db) throws DbException {
        for (Class clazz : list){
            db.dropTable(clazz);
        }
    }

    public void insert(Object bean) throws DbException {
      db.saveOrUpdate(bean);
    }

    public void insertList(List<Object> objects) throws DbException {
        db.saveOrUpdateAll(objects);
    }

    public List<Object> queryList(Class clazz,String name,String [] value) throws DbException {
       List<Object> objects = db.findAll(Selector.from(clazz).where(name,"=",value));
        return objects;
    }

    public Object query(Class clazz,String name,String value) throws DbException {
        Object object = db.findFirst(Selector.from(clazz).where(name, "=", value));
        return object;
    }

    public Object query(Class clazz,String name,String value,String ordby) throws DbException {
        Object object = db.findFirst(Selector.from(clazz).where(name,"=",value).orderBy(ordby));
        return object;
    }

    public void delete(Class clazz,String name,String value) throws DbException {
            db.delete(clazz, WhereBuilder.b(name, "=", value));
    }


}
