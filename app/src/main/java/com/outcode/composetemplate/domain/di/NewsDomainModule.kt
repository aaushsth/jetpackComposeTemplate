package com.outcode.myapplication.domain.di

import com.outcode.myapplication.data.repository.NewsRepository
import com.outcode.myapplication.domain.usecase.news.GetNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NewsDomainModule {
    @Singleton
    @Provides
    fun provideGetEpisodes(repository: NewsRepository): GetNews {
        return GetNews(repository)
    }

}