package com.rodrigotristany.moviesfeed.http

import com.rodrigotristany.moviesfeed.http.apimodel.TopMoviesRated
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("top_rated")
    fun topMoviesRated(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<TopMoviesRated>


}