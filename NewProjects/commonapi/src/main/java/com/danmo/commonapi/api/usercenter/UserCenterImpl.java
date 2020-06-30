package com.danmo.commonapi.api.usercenter;


import com.blankj.utilcode.util.ReflectUtils;
import com.danmo.commonapi.RetrofitParameterBuilder;
import com.danmo.commonapi.base.BaseImpl;
import com.danmo.commonapi.base.UUIDGenerator;


import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;

public class UserCenterImpl extends BaseImpl<UserCenterService> {

    public UserCenterImpl( String baseUrl, int currentParse) {
        super( baseUrl, currentParse);
    }


    public String getCode(){
        String uuid=UUIDGenerator.getUUID();
        sub(mService.getVcode("18768103126"),uuid,"getCode");
        return  uuid;
    }
    /*
    * 登录
    * */
    public String login(String deviceId, String password, String telephone, String vcode ){
        String uuid=UUIDGenerator.getUUID();
        sub(mService.login(deviceId,password,telephone,vcode),uuid,"login");
        return  uuid;
    }



    /*
    *
    * 上传图片
    * */
    public String uploadImg(File file){
        String uuid=UUIDGenerator.getUUID();
        Map<String, RequestBody> params= RetrofitParameterBuilder.newBuilder()
                .addParameter("img",file)
                .bulider("");
        sub(mService.uploadImg(params),uuid,"uploadImg");
        return  uuid;

    }

}
