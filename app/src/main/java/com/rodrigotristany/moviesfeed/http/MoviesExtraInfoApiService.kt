package com.rodrigotristany.moviesfeed.http

import com.rodrigotristany.moviesfeed.http.apimodel.OmdbApi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesExtraInfoApiService {
    @GET("/")
    fun extraInfoMovie(@Query("t") title: String) : Observable<OmdbApi>
}