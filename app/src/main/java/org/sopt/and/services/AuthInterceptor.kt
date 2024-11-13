package org.sopt.and.services

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // SharedPreferences나 DataStore에서 토큰 가져오기
        val prefs = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token = prefs.getString("auth_token", null)

        // 요청에 헤더 추가
        val request = chain.request().newBuilder()
            .apply {
                if (token != null) {
                    addHeader("Authorization", "Bearer $token")
                }
            }
            .build()
        return chain.proceed(request)
    }
}