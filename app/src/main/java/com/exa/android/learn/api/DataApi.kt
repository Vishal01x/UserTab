package com.exa.android.learn.api

import com.exa.android.learn.DataTypes.User
import retrofit2.Response
import retrofit2.http.GET

interface  DataApi{
    @GET("users")
   suspend fun  data() : Response<User>
}