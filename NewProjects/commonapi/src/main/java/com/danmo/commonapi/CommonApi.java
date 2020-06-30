package com.danmo.commonapi;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.danmo.commonapi.api.usercenter.UserCenterImpl;
import com.danmo.commonapi.base.Constant;
/*
* 网络接口获取相关数据
* */
public class CommonApi {
    public static UserCenterImpl userCenterImpl;

    //--- 单例 -----------------------------------------------------------------------------------
    private volatile static CommonApi mCommonApi;

    private CommonApi() {
    }

    public static CommonApi getSingleInstance() {
        if (null == mCommonApi) {
            synchronized (CommonApi.class) {
                if (null == mCommonApi) {
                    mCommonApi = new CommonApi();
                }
            }
        }
        return mCommonApi;
    }

    //--- 初始化 ---------------------------------------------------------------------------------
    public static CommonApi init() {
        initLogger();
        initImplement();
        return getSingleInstance();
    }

    private static void initLogger() {
        // 在 debug 模式输出日志， release 模式自动移除
        if (AppUtils.isAppDebug()) {
            LogUtils.getConfig().setGlobalTag("DEBUG_"+AppUtils.getAppName());
        } else {
            LogUtils.getConfig().setGlobalTag("Release_"+AppUtils.getAppName());

        }
    }

    private static void initImplement() {
        try {
            userCenterImpl=new UserCenterImpl( Constant.BASE_URL, Constant.PARSE_GSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
