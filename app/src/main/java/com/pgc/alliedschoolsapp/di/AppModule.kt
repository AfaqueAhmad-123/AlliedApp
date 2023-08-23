package com.pgc.alliedschoolsapp.di

import android.content.Context
import com.example.examplecleanarch.network.ApiService
import com.example.examplecleanarch.utils.CallApis
import com.example.examplecleanarch.utils.NetworkManager
import com.pgc.alliedschoolsapp.utils.Constants
import com.pgc.alliedschoolsapp.utils.SingletonClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideNetworkManager(@ApplicationContext appContext: Context): NetworkManager =
        NetworkManager(appContext)

    @Singleton
    @Provides
    fun provideCallApis(networkManager: NetworkManager): CallApis =
        CallApis(networkManager)

    @Provides
    @Singleton
    fun getSingleton(): SingletonClass {
        return SingletonClass()
    }

}