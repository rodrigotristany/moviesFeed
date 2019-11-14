package com.rodrigotristany.moviesfeed.movies

import com.rodrigotristany.moviesfeed.http.apimodel.Result
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MoviesModel(private var repository: Repository) : MoviesMVP.Model {

    override fun result(): Observable<ViewModel> {
        return Observable.zip(repository.resultData(), repository.countryData(), object : BiFunction<Result,String,ViewModel> {
            override fun apply(result: Result, country: String): ViewModel {
                return ViewModel(result.title, country)
            }
        })
    }
}