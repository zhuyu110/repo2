package com.manage.tn.tn_booss.base;


import android.content.res.Resources;
import android.databinding.ViewDataBinding;
import android.os.SystemClock;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.ToastUtils;

import com.danmo.commonutil.activity.CommonActivity;
import com.manage.tn.tn_booss.ui.activity.MainActivity;
import com.manage.tn.tn_booss.R;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity <DB extends ViewDataBinding> extends CommonActivity<DB> {

    private List<TurnBackListener> mTurnBackListeners=new ArrayList<>();//回调监听集合
    private long mBackPressedTime;//记录上次点击退回的事件
    public interface  TurnBackListener{//自定义一个回调接口用于管理回退事件
       boolean onTurnBack();
    }
    public void  addOnTurnBackListener(TurnBackListener l){
        this.mTurnBackListeners.add(l);
    }//注册回退按钮的监听


    /*
    * 导航返回按键退出
    * */
    @Override
    public void onBackPressed() {

       for(TurnBackListener listener :mTurnBackListeners){
           if(listener.onTurnBack()) return;
       }
        if (this instanceof MainActivity) {
            long curTime = SystemClock.uptimeMillis();
            if ((curTime - mBackPressedTime) < (3 * 1000)) {
                finish();
            } else {
                mBackPressedTime = curTime;
                ToastUtils.showShort(this.getString(R.string.tip_double_click_exit));
            }
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public Resources getResources() {
        return AdaptScreenUtils.adaptHeight(super.getResources(),1920);
    }
}
