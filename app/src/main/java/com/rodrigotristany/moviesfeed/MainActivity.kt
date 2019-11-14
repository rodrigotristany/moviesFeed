package com.rodrigotristany.moviesfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rodrigotristany.moviesfeed.movies.ListAdapter
import com.rodrigotristany.moviesfeed.movies.MoviesMVP
import com.rodrigotristany.moviesfeed.movies.ViewModel
import com.rodrigotristany.moviesfeed.root.App
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesMVP.View {

    @Inject
    lateinit var presenter : MoviesMVP.Presenter

    private lateinit var listAdapter: ListAdapter
    private var resultList: MutableList<ViewModel> = mutableListOf()

    override fun updateData(viewModel: ViewModel) {
        resultList.add(viewModel)
        listAdapter.notifyItemChanged(resultList.size - 1)
        Log.d(TAG, "Información nueva: " + viewModel.name)
    }

    override fun showSnackBar(s: String) {
        Snackbar.make(activity_root_view, s, Snackbar.LENGTH_SHORT)
    }

    private val TAG: String = MainActivity::javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)

        listAdapter = ListAdapter(resultList)
        recycler_view_movies.adapter = listAdapter
        recycler_view_movies.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        recycler_view_movies.itemAnimator = DefaultItemAnimator()
        recycler_view_movies.setHasFixedSize(true)
        recycler_view_movies.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.loadData()
    }

    override fun onStop() {
        super.onStop()
        presenter.rxJavaUnsubscribe()
        resultList.clear()
        listAdapter.notifyDataSetChanged()
    }
}
