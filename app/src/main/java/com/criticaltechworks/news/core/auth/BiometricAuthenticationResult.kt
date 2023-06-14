package com.criticaltechworks.news.core.auth

sealed class BiometricAuthenticationResult {
    object Success : BiometricAuthenticationResult()
    data class Error(val errorCode: Int, val errorMessage: String) : BiometricAuthenticationResult()
    object Failure : BiometricAuthenticationResult()
}
