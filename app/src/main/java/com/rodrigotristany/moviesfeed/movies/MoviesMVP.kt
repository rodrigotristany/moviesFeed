package com.rodrigotristany.moviesfeed.movies

import io.reactivex.Observable

interface MoviesMVP {

    interface View {
        fun updateData(viewModel: ViewModel)
        fun showSnackBar(s: String)
    }

    interface Presenter {
        fun loadData()
        fun rxJavaUnsubscribe()
        fun setView(view: MoviesMVP.View)
    }

    interface Model {
        fun result() : Observable<ViewModel>
    }
}