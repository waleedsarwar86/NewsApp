package com.criticaltechworks.news.app.di

import com.criticaltechworks.news.app.appinitializers.StrictModeInitializer
import com.criticaltechworks.news.app.appinitializers.TimberInitializer
import com.criticaltechworks.news.core.auth.BiometricAuthenticator
import com.criticaltechworks.news.core.auth.BiometricAuthenticatorImpl
import com.criticaltechworks.news.core.common.appinitializer.AppInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModuleBinds {

    @Binds
    @IntoSet
    abstract fun provideTimberInitializer(bind: TimberInitializer): AppInitializer

    @Binds
    @IntoSet
    abstract fun provideStrictModelInitializer(bind: StrictModeInitializer): AppInitializer

    @Binds
    abstract fun provideBiometricAuthenticator(bind: BiometricAuthenticatorImpl): BiometricAuthenticator
}
