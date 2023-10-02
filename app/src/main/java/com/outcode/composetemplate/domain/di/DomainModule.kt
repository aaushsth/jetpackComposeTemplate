package com.outcode.myapplication.domain.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Ayush Shrestha$ on 2022/12/12$.
 */
@SuppressLint("VisibleForTests")
@Module(
    includes = [
        RepositoryModule::class,
        NewsDomainModule::class
    ]
)
@InstallIn(SingletonComponent::class)
class DomainModule