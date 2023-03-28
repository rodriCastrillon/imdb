package com.imdb.data.di

import com.imdb.core.Constants.API_KEY
import com.imdb.core.Constants.BASE_URL
import com.imdb.data.network.MovieService
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.data.source.remote.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(Interceptor { chain ->
                return@Interceptor chain.proceed(chain.request()
                    .newBuilder()
                    .url(chain.request()
                        .url
                        .newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build())
                    .build())
            })
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun providerInterface(service: MovieService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(service)
}
