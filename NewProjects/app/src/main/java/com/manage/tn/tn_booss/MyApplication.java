package com.manage.tn.tn_booss;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.danmo.commonapi.AppDatabase;
import com.danmo.commonapi.CommonApi;
import com.danmo.commonapi.DataRepository;
import com.facebook.stetho.Stetho;
import com.lxj.xpopup.XPopup;
public class MyApplication extends MultiDexApplication {
public static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        sAppContext=this;
        Stetho.initializeWithDefaults(this);
        XPopup.setPrimaryColor(getResources().getColor(R.color.colorPrimary));//为弹框框架设置主题
    }

/*
* 获取数据库工具
* */
    public AppDatabase getDatabase() {

        return AppDatabase.getInstance(this);
    }
/*
* 获取网络工具
* */
    public CommonApi getCommonApi(){

        return CommonApi.init();
    }
/*
* 数据仓库
* */
    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase(),getCommonApi());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
