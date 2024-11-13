package org.sopt.and.services

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.and.BuildConfig
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL

    fun createRetrofit(context: Context): Retrofit {
        // AuthInterceptor 생성
        val authInterceptor = AuthInterceptor(context)

        // OkHttpClient에 AuthInterceptor와 LoggingInterceptor 추가
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // AuthInterceptor 추가
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(context: Context): T =
        createRetrofit(context).create(T::class.java)
}

object ServicePool {
    fun userService(context: Context) = ApiFactory.create<UserService>(context)
}