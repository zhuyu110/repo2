/*
 * Copyright 2017, The Android Open Source Project
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

package com.danmo.commonapi;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.danmo.commonapi.db.greendao.DaoMaster;
import com.danmo.commonapi.db.greendao.UserDao;




/*
* 数据库数据工具
* */

public  class AppDatabase {

    private static AppDatabase sInstance;
    private UserDao userDao;
    private DaoMaster.DevOpenHelper helper;

    @VisibleForTesting
    public static final String DATABASE_NAME = "user-db";

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance =new AppDatabase(context);
                }
            }
        }
        return sInstance;
    }

    public AppDatabase(Context context){
        helper=new DaoMaster.DevOpenHelper( context,DATABASE_NAME,null);
        userDao=new DaoMaster(helper.getWritableDb()).newSession().getUserDao();
    }



    public void get(){}
}
