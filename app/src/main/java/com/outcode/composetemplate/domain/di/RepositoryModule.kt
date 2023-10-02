package com.outcode.myapplication.domain.di

import android.annotation.SuppressLint
import com.outcode.composetemplate.data.api.NewsApiService
import com.outcode.myapplication.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Ayush Shrestha$ on 2022/11/24$.
 */



@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
 class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        apiService : NewsApiService
    ): NewsRepository = NewsRepository(apiService)

}