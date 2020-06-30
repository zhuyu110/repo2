package com.manage.tn.tn_booss.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.base.view.ViewHolder;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.manage.tn.tn_booss.R;
import com.manage.tn.tn_booss.base.BaseFragment;
import com.manage.tn.tn_booss.base.ViewModelFactory;
import com.manage.tn.tn_booss.databinding.UserLoginBinding;
import com.manage.tn.tn_booss.viewModel.UserLoginViewModel;


public class LoginFragment extends BaseFragment<UserLoginBinding> {

    private UserLoginBinding binding;
    private UserLoginViewModel userLoginViewModel;

    /*
     * 获取数据
     * */
    @Override
    public void doBusiness() {
        userLoginViewModel.getMLDByCode(this,"login",String.class,true).observe(this, (success)->{
        });
        userLoginViewModel.getMLDByCode(this,"",Boolean.class,true).observe(this,(resule)->{
        });
    }

    @Override
    protected void initViews(ViewHolder holder, View root) {
        userLoginViewModel= new ViewModelProvider(this,new ViewModelFactory()).get(UserLoginViewModel.class);
        binding= ((UserLoginBinding)holder.getBinding());
        binding.setLogin(this);
    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.a_user_time:
                userLoginViewModel.getCode();
                if (binding.userPhone.getText().toString().trim().length() == 11) {
                    binding.aUserTime.setSelected(true);
                    binding.aUserTime.startTime();
                } else {
                    ToastUtils.showLong("请输入正确的手机号或验证码");
                }

                break;
            case R.id.a_user_login:

                userLoginViewModel.login(DeviceUtils.getUniqueDeviceId(),"123456",binding.userPhone.getText().toString().trim(),binding.aUserCode.getText().toString().trim());

                break;


        }


    }






    @Override
    public int bindLayout() {
        return R.layout.user_login;
    }



    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
