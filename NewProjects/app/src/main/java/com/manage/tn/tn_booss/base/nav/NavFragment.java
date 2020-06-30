package com.manage.tn.tn_booss.base.nav;


import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.base.view.ViewHolder;
import com.manage.tn.tn_booss.R;
import com.manage.tn.tn_booss.ui.fragment.TestFragment;
import com.manage.tn.tn_booss.base.BaseFragment;
import com.manage.tn.tn_booss.databinding.FragmentNavBinding;
import com.manage.tn.tn_booss.ui.fragment.LoginFragment;
import com.manage.tn.tn_booss.util.Config;
import com.manage.tn.tn_booss.util.fastcolck.SingleClick;

import java.util.List;

/**
 * 底部导航栏内容 用于切换不同菜单NAVBtn调用不同的fragment页面
 */
public class NavFragment extends BaseFragment<FragmentNavBinding> implements View.OnClickListener {
    private NavigationButton mNavHome;//首页
    private NavigationButton navThereport;//报表
    private ImageView mNavGo;//开启
    private NavigationButton mNavMessage;//消息
    private NavigationButton navFootprint;//足迹
    private LinearLayout  nav_layout;//
    private int mContainerId;
    private NavigationButton mCurrentNavButton;
    private OnNavigationReselectListener mOnNavigationReselectListener;

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_nav;
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }


    @Override
    protected void initViews(ViewHolder holder, View root) {
        mNavHome = holder.get(R.id.nav_item_home);
        navThereport = holder.get(R.id.nav_item_thereport);
        mNavGo=holder.get(R.id.nav_item_go);
        mNavMessage = holder.get(R.id.nav_item_message);
        navFootprint = holder.get(R.id.nav_item_footprint);
        nav_layout=holder.get(R.id.nav_layout);
        holder.setOnClickListener(this, R.id.nav_item_home);
        holder.setOnClickListener(this, R.id.nav_item_thereport);
        holder.setOnClickListener(this, R.id.nav_item_go);
        holder.setOnClickListener(this, R.id.nav_item_message);
        holder.setOnClickListener(this, R.id.nav_item_footprint);
        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(R.color.white)),
                lineDrawable
        });
        nav_layout.setBackgroundDrawable(layerDrawable);
        if (Config.navigationTitles.length < 4) {
            return;
        }
        mNavHome.init(R.drawable.tab_icon_home,
                Config.navigationTitles[0],
                LoginFragment.class);

        navThereport.init(R.drawable.tab_icon_home,
                Config.navigationTitles[1],
                TestFragment.class);


        mNavMessage.init(R.drawable.tab_icon_home,
                Config.navigationTitles[2],
                TestFragment.class);

        navFootprint.init(R.drawable.tab_icon_home,
                Config.navigationTitles[3],
                TestFragment.class);
        clearOldFragment();
        doSelect(mNavHome);
    }
    @SingleClick
    @Override
    public void onClick(View v) {
        if (v instanceof NavigationButton) {
            NavigationButton nav = (NavigationButton) v;
            doSelect(nav);
        } else if (v.getId() == R.id.nav_item_go) {
           //开启的功能
           /* startActivity(new Intent(mContext, NearbyOutletsActivity.class));*/
        }
    }

    public void setup(int contentId, OnNavigationReselectListener listener) {// 设置菜单事件监听和存放Fragment显示的FragLayout
        mContainerId = contentId;
        mOnNavigationReselectListener = listener;
    }



    private void clearOldFragment() {//清除历史缓存的Fragment

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0)
            return;
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this && fragment != null) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit)
            transaction.commitNow();
    }
/*
* 选择菜单
* */
    private void doSelect(NavigationButton newNavButton) {
        NavigationButton oldNavButton = null;
        if (mCurrentNavButton != null) {
            oldNavButton = mCurrentNavButton;
            if (oldNavButton == newNavButton) {
                onReselect(oldNavButton);
                return;
            }
            oldNavButton.setSelected(false);
        }
        newNavButton.setSelected(true);
        doTabChanged(oldNavButton, newNavButton);
        mCurrentNavButton = newNavButton;
    }
/*
* 菜单切换
* */
    private void doTabChanged(NavigationButton oldNavButton, NavigationButton newNavButton) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        if (oldNavButton != null) {
            if (oldNavButton.getFragment() != null) {
                ft.detach(oldNavButton.getFragment());
            }
        }
        if (newNavButton != null) {
            if (newNavButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(getContext(),
                        newNavButton.getClx().getName(), null);
                ft.add(mContainerId, fragment, newNavButton.getTag());
                newNavButton.setFragment(fragment);
            } else {
                ft.attach(newNavButton.getFragment());
            }
        }
        ft.commit();
    }
/*
* 取消选中的菜单效果
* */
    private void onReselect(NavigationButton navigationButton) {
        OnNavigationReselectListener listener = mOnNavigationReselectListener;
        if (listener != null) {
            listener.onReselect(navigationButton);
        }
    }

    public interface OnNavigationReselectListener {
        void onReselect(NavigationButton navigationButton);
    }
}
