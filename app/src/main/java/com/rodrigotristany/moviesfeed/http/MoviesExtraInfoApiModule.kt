package com.rodrigotristany.moviesfeed.http

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MoviesExtraInfoApiModule {
    val BASE_URL: String = "http://omdbapi.com/"

    @Provides
    fun providesClient() : OkHttpClient  {
        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Provides
    fun providesRetrofit(baseUrl: String, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApiService() : MoviesExtraInfoApiService {
        return providesRetrofit(BASE_URL, providesClient()).create(MoviesExtraInfoApiService::class.java)
    }


}