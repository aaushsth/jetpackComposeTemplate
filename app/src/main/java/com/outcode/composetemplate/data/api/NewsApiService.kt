package com.outcode.composetemplate.data.api

import com.outcode.composetemplate.data.response.AllNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Ayush Shrestha$ on 2022/11/21$.
 */
interface NewsApiService {
    @GET("/v2/everything")
   suspend fun getAllNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): AllNewsResponse
}