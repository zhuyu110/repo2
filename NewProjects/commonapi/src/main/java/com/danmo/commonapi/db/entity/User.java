/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.danmo.commonapi.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 *登录后获取用户登录信息
 */
@Entity
public class User {

    @Id
    @NotNull
    private String accessToken;// 用户令牌(获取相关数据使用)
    @NotNull
    private String accessTokenExpire;//过期时间
    @Generated(hash = 1176318410)
    public User(@NotNull String accessToken, @NotNull String accessTokenExpire) {
        this.accessToken = accessToken;
        this.accessTokenExpire = accessTokenExpire;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessTokenExpire() {
        return this.accessTokenExpire;
    }
    public void setAccessTokenExpire(String accessTokenExpire) {
        this.accessTokenExpire = accessTokenExpire;
    }

    
}
