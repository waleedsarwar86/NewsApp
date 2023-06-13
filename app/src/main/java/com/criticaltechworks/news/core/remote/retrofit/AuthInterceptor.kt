package com.criticaltechworks.news.core.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Chain): Response {
        val chainBuilder = chain.request().newBuilder()
            .addHeader(AUTHORIZATION, authToken)
        return chain.proceed(chainBuilder.build())
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
