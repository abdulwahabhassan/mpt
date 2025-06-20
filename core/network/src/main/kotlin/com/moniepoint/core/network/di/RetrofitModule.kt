package com.moniepoint.core.network.di

import com.moniepoint.core.network.service.MoniePointService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun providesIdentityService(
        client: OkHttpClient,
        json: Json,
    ): MoniePointService {
        return Retrofit.Builder()
            .baseUrl("https://app.moniepoint.org/")
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(MoniePointService::class.java)
    }
}
