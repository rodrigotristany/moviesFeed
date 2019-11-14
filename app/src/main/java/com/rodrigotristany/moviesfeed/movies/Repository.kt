package com.rodrigotristany.moviesfeed.movies

import com.rodrigotristany.moviesfeed.http.apimodel.Result
import io.reactivex.Observable

interface Repository {
    fun countryDataFromNetwork() : Observable<String>
    fun countryDataFromCache() : Observable<String>
    fun countryData() : Observable<String>
    fun resultFromNetwork() : Observable<Result>
    fun resultFromCache() : Observable<Result>
    fun resultData() : Observable<Result>
}