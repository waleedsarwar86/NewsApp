package com.criticaltechworks.news.core.remote.di

import com.criticaltechworks.news.BuildConfig
import com.criticaltechworks.news.core.remote.retrofit.AuthInterceptor
import com.criticaltechworks.news.core.remote.retrofit.LocalDateTimeAdapter
import com.criticaltechworks.news.core.remote.retrofit.ResultCallAdapterFactory
import com.criticaltechworks.news.core.remote.service.NewsService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(LocalDateTimeAdapter()).build()
    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(BuildConfig.NEWS_API_KEY)
    }

    @Provides
    fun provideRetrofit(authInterceptor: AuthInterceptor, moshi: Moshi): Retrofit {
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(authInterceptor).readTimeout(30, TimeUnit.SECONDS)
                .build()
        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ResultCallAdapterFactory()).client(okHttpClient)
            .baseUrl(BuildConfig.NEWS_API_URL).build()
    }

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)
}
