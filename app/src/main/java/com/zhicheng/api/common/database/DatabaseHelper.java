package com.zhicheng.api.common.database;


import com.zhicheng.bean.http.LoginResponse;

import java.lang.reflect.Method;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Donson on 2016/12/30.
 */

public class DatabaseHelper{

    private Realm realm;

    public DatabaseHelper (){
        if (realm == null){
            realm = Realm.getDefaultInstance();
        }
    }

    /**
     * Work Note
     * @param wn WorkNote
     */
    public void setWorkNote(WorkNote wn){
        realm.beginTransaction();
        realm.copyToRealm(wn);
        realm.commitTransaction();
    }

    public void deleteWorkNote(String uID){
        WorkNote mWorkNote = realm.where(WorkNote.class).equalTo("uID",uID).findFirst();
        realm.beginTransaction();
        mWorkNote.deleteFromRealm();
        realm.commitTransaction();
    }

    public List<WorkNote> getWorkNote(){
        RealmResults<WorkNote> list = realm.where(WorkNote.class).findAll();
        return realm.copyFromRealm(list);
    }

    public long generateNewPrimaryKey() {
        long primaryKey = 0;
        RealmResults<WorkNote> results = realm.where(WorkNote.class).findAllSorted("id", Sort.ASCENDING);
        if(results != null && results.size() > 0) {
            WorkNote last = results.last();
            primaryKey = last.getId() + 1;
        }
        return primaryKey;
    }

    /**
     * Login
     *
     */
    public void setLocalConfig(LocalConfig lc){
        realm.beginTransaction();
        realm.copyToRealm(lc);
        realm.commitTransaction();
    }

    public LocalConfig getLocalConfig(){
        LocalConfig lc = realm.where(LocalConfig.class).findFirst();
        if (lc != null){
            return lc;
        }else {
            return null;
        }
    }

    public void updateLocalConfig(LoginResponse loginResponse) throws Exception {
        LocalConfig lcn = getLocalConfig();
        if (lcn != null){
            realm.beginTransaction();
            try {
                Class z = loginResponse.getIq().getQuery().getClass();
                Class c = lcn.getClass();
                Method[] methods = z.getDeclaredMethods();
                Method[] cMethods = c.getDeclaredMethods();
                for (Method method : methods){
                    if (method.getName().startsWith("get")){
                        for (Method m : cMethods){
                            if (m.getName().startsWith("realmSet$") && m.getName().toUpperCase().contains(method.getName().substring(3).toUpperCase())){
                                m.invoke(lcn,method.invoke(loginResponse.getIq().getQuery()));
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            realm.commitTransaction();
        }else {
            throw new Exception("Realm updateLocalConfig Lose,LocalConfig is null");
        }
    }

    public void deleteLocalConfig(){
        RealmResults<LocalConfig> results = realm.where(LocalConfig.class).findAll();
        realm.executeTransaction(realm1 -> {
            results.deleteAllFromRealm();
        });
    }
}
