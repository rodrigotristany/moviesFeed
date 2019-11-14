package com.rodrigotristany.moviesfeed.http

import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MoviesTitleApiModule {
    val BASE_URL: String = "http://api.themoviedb.org/3/movie/"
    val API_KEY: String = "ad8fee839f8f24512268bf27861a01eb"

    @Provides
    fun provideClient() : OkHttpClient {
        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                var request : Request = chain.request()
                val url : HttpUrl = request .url().newBuilder().addQueryParameter("api_key",API_KEY).build()
                request = request.newBuilder().build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService() : MoviesApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(MoviesApiService::class.java)
    }
}