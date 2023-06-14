package com.criticaltechworks.news.core.auth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.biometric.BiometricPrompt.AuthenticationResult
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class BiometricAuthenticatorImpl @Inject constructor() : BiometricAuthenticator {
    override suspend fun authenticate(activity: AppCompatActivity): Flow<BiometricAuthenticationResult> =
        callbackFlow {
            if (isBiometricAvailable(activity) && isBiometricConfigured(activity)) {
                val executor = ContextCompat.getMainExecutor(activity)
                val callback = object : AuthenticationCallback() {
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        trySend(
                            BiometricAuthenticationResult.Error(
                                errorCode,
                                errString.toString(),
                            ),
                        )
                    }

                    override fun onAuthenticationSucceeded(result: AuthenticationResult) {
                        trySend(BiometricAuthenticationResult.Success)
                    }

                    override fun onAuthenticationFailed() {
                        trySend(BiometricAuthenticationResult.Failure)
                    }
                }
                val biometricPrompt = BiometricPrompt(activity, executor, callback)
                val promptInfo = createPromptInfo()
                biometricPrompt.authenticate(promptInfo)
                awaitClose ()
            } else {
                trySend(BiometricAuthenticationResult.Success)
                awaitClose()
            }
        }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Authenticate using your biometric data")
            .setNegativeButtonText("Cancel")
            .build()
    }

    private fun isBiometricAvailable(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS
    }

    private fun isBiometricConfigured(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(Authenticators.BIOMETRIC_WEAK) != BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE &&
            biometricManager.canAuthenticate(Authenticators.BIOMETRIC_WEAK) != BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE &&
            biometricManager.canAuthenticate(Authenticators.BIOMETRIC_WEAK) != BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
    }
}
