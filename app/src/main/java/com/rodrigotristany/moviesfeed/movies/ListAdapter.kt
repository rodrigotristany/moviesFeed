package com.rodrigotristany.moviesfeed.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodrigotristany.moviesfeed.R
import kotlinx.android.synthetic.main.movie_list_item.view.*

data class ListAdapter(var list: List<ViewModel>) : RecyclerView.Adapter<ListAdapter.Companion.ListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ListItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.itemName.text = list[position].name
        holder.countryName.text = list[position].country
    }

    companion object {
        class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var itemName: TextView = itemView.text_view_fragment_title
            var countryName: TextView = itemView.text_view_fragment_country
        }
    }
}