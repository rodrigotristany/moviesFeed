package com.rodrigotristany.moviesfeed.root

import android.app.Activity
import com.rodrigotristany.moviesfeed.http.MoviesExtraInfoApiModule
import com.rodrigotristany.moviesfeed.http.MoviesTitleApiModule
import com.rodrigotristany.moviesfeed.movies.MoviesModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    MoviesTitleApiModule::class,
    MoviesExtraInfoApiModule::class,
    MoviesModule::class])
interface ApplicationComponent {
    fun inject(target: Activity)
}