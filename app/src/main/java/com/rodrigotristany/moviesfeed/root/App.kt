package com.rodrigotristany.moviesfeed.root

import android.app.Application
import com.rodrigotristany.moviesfeed.http.MoviesExtraInfoApiModule
import com.rodrigotristany.moviesfeed.http.MoviesTitleApiModule
import com.rodrigotristany.moviesfeed.movies.MoviesModule

class App: Application() {
    val component: ApplicationComponent = DaggerApplicationComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .moviesModule(MoviesModule())
        .moviesTitleApiModule((MoviesTitleApiModule()))
        .moviesExtraInfoApiModule(MoviesExtraInfoApiModule())
        .build()
}