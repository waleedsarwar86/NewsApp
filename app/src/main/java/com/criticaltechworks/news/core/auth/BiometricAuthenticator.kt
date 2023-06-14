package com.criticaltechworks.news.core.auth

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.flow.Flow

interface BiometricAuthenticator {
    suspend fun authenticate(activity: AppCompatActivity): Flow<BiometricAuthenticationResult>
}