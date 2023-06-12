package com.criticaltechworks.news.core.common.logger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class LoggerModule {
    @Singleton
    @Binds
    internal abstract fun provideLogger(bind: NewsAppLogger): Logger
}
