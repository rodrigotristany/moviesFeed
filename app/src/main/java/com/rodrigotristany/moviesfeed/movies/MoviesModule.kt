package com.rodrigotristany.moviesfeed.movies

import com.rodrigotristany.moviesfeed.http.MoviesApiService
import com.rodrigotristany.moviesfeed.http.MoviesExtraInfoApiService
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {
    @Provides
    fun providesMoviesPresenter(moviesModel : MoviesMVP.Model) : MoviesMVP.Presenter {
        return MoviesPresenter(moviesModel)
    }

    @Provides
    fun providesMoviesModel(repository: Repository) : MoviesMVP.Model {
        return MoviesModel(repository)
    }

    @Provides
    fun providesMovieRepository(moviesApiService: MoviesApiService,
                                moviesExtraInfoApiService: MoviesExtraInfoApiService) : Repository {
        return MoviesRepository(moviesApiService, moviesExtraInfoApiService)
    }
}