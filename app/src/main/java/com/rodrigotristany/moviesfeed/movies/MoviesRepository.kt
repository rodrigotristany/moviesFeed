package com.rodrigotristany.moviesfeed.movies

import com.rodrigotristany.moviesfeed.http.MoviesApiService
import com.rodrigotristany.moviesfeed.http.MoviesExtraInfoApiService
import com.rodrigotristany.moviesfeed.http.apimodel.Result
import io.reactivex.Observable

class MoviesRepository(private val mService: MoviesApiService,
                       private val mExtraInfo: MoviesExtraInfoApiService) :Repository {

    private var countries : MutableList<String> = mutableListOf()
    private var results : MutableList<Result> = mutableListOf()
    private var lastTimestamp : Long = 0L

    companion object {
        val CACHE_LIFETIME : Long = 20 * 1000
    }

    init {
        this.lastTimestamp = System.currentTimeMillis()
    }

    fun isUpdated() = (System.currentTimeMillis() - this.lastTimestamp) < CACHE_LIFETIME

    override fun countryDataFromNetwork(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun countryDataFromCache(): Observable<String> {
        return if(isUpdated()){
            Observable.fromIterable(this.countries)
        } else {
            this.lastTimestamp = 0L
            this.countries.clear()
            Observable.empty()
        }
    }

    override fun resultFromNetwork(): Observable<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resultFromCache(): Observable<Result> {
        return if(isUpdated()){
            Observable.fromIterable(this.results)
        } else {
            this.lastTimestamp = 0L
            this.results.clear()
            Observable.empty()
        }
    }

    override fun resultData(): Observable<Result> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun countryData(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}