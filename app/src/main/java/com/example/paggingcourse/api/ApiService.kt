package com.example.paggingcourse.api

import com.example.paggingcourse.model.ResponseApi
import com.example.paggingcourse.utils.Constans
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constans.END_POINT)
    suspend fun getAllCharecters(
        @Query("page") page : Int
    ) : Response<ResponseApi>
}