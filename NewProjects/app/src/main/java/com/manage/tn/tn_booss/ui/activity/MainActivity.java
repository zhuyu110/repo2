package com.manage.tn.tn_booss.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.LogUtils;
import com.manage.tn.tn_booss.R;
import com.manage.tn.tn_booss.base.BaseActivity;
import com.manage.tn.tn_booss.base.nav.NavFragment;
import com.manage.tn.tn_booss.base.nav.NavigationButton;
import com.manage.tn.tn_booss.base.nav.OnTabReselectListener;
import com.manage.tn.tn_booss.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements NavFragment.OnNavigationReselectListener{
    private NavFragment mNavBar;
    @Override
    public boolean isSwipeBack() {
        return false;
    }

    @Override
    public boolean bindDrawer() {
        return true;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, ActivityMainBinding childDb, @Nullable ViewDataBinding rootDb) {
        mNavBar=new NavFragment();
        FragmentUtils.add(getSupportFragmentManager(),mNavBar,childDb.fagNav.getId());
        mNavBar.setup(childDb.mainContainer.getId(),this);
    }

    /*
    * 数据耗时操作
    * */
    @Override
    public void doBusiness() {


    }

    /*
    * 点击事件回调
    * */
    @Override
    public void onDebouncingClick(@NonNull View view) {
        LogUtils.dTag("ZY","VIEW="+view.getId());

    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
            Fragment fragement=navigationButton.getFragment();
            if(fragement!=null&&fragement instanceof OnTabReselectListener){
                OnTabReselectListener listener = (OnTabReselectListener) fragement;
                listener.onTabReselect();
            }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }
}
