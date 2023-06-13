package com.criticaltechworks.news.core.data.di

import com.criticaltechworks.news.core.data.repository.NewsRepositoryImpl
import com.criticaltechworks.news.core.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModuleBinds {
    @Binds
    fun bindNewsRepository(newsRepository: NewsRepositoryImpl): NewsRepository
}
