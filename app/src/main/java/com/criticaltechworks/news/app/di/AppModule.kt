package com.criticaltechworks.news.app.di

import com.criticaltechworks.news.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @NewsSource
    fun provideNewsSource() = BuildConfig.NEWS_SOURCE
}
