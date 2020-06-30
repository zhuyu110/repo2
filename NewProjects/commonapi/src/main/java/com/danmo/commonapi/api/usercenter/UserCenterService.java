package com.danmo.commonapi.api.usercenter;




import com.danmo.commonapi.model.BaseResponse;
import com.danmo.commonapi.model.Vcode;
import com.danmo.commonapi.model.login.Token;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface UserCenterService {
    /*
     * 获取验证码
     * */
    @GET("user/get/vcode")
    Observable<BaseResponse<Vcode>> getVcode(@Query("telephone") String telephone);

    /*
    * 登录接口
    * */

    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<Token>> login(@Field("deviceId") String deviceId, @Field("password") String password,@Field("telephone") String telephone,@Field("vcode") String vcode );


    /*
    * 上传图片
    * */
    @Multipart
    @POST("file/upload/img")
    Observable<BaseResponse<String>> uploadImg(@PartMap Map<String, RequestBody> parms);


}
