package com.outcode.myapplication.data.repository

import com.outcode.composetemplate.BuildConfig
import com.outcode.composetemplate.data.api.NewsApiService
import javax.inject.Inject


/**
 * Created by Ayush Shrestha$ on 2022/11/23$.
 */
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService
) {
    suspend fun getAllNews(page: Int) = apiService.getAllNews(
        query = "everything",
        page = page,
        pageSize = 100,
        apiKey = BuildConfig.API_KEY
    )
}

/*
@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
) : NewsRepository {
    override suspend fun getAllNews(page: Int): Resource<AllNewsResponse> {
        return withContext(Dispatchers.Default) {
            doTryCatch {
                apiService.getAllNews(
                    query = "everything",
                    page = 1,
                    pageSize = 100,
                    apiKey = BuildConfig.API_KEY
                ).handleResponse()

            }
        }
    }
}*/
