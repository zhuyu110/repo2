package com.manage.tn.tn_booss.viewModel;


import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.danmo.commonapi.DataRepository;
import com.danmo.commonapi.base.Constant;
import com.danmo.commonapi.model.BaseResponse;
import com.danmo.commonapi.model.login.Token;
import com.manage.tn.tn_booss.base.BaseViewModel;


/*
* 绑定View和Data
* */
public class UserLoginViewModel extends BaseViewModel {

    public UserLoginViewModel(DataRepository dataRepository){
        super(dataRepository);
    }

    public void  getCode(){
        mDataSource.commonApi.userCenterImpl.getCode();
    }

    /*
    *
    * 登录
    * */
    public void  login(String deviceId, String password, String telephone, String vcode ){

        mDataSource.commonApi.userCenterImpl.login(deviceId,password,telephone,vcode);
    }

}
