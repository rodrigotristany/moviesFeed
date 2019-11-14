package com.rodrigotristany.moviesfeed.movies

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MoviesPresenter(private var model: MoviesMVP.Model) : MoviesMVP.Presenter {

    private var view : MoviesMVP.View? = null

    private var subscription : Disposable? = null

    override fun loadData() {
        subscription = model.result()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<ViewModel>() {
                override fun onComplete() {
                    view?.let {
                        it.showSnackBar("Información descargada con éxito")
                    }
                }

                override fun onNext(t: ViewModel) {
                    view?.updateData(t)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view?.showSnackBar("Error descargando las películas")
                }

            })
    }

    override fun rxJavaUnsubscribe() {
        subscription?.let {
            if(!it.isDisposed) it.dispose()
        }
    }

    override fun setView(view: MoviesMVP.View) {
        this.view = view
    }
}