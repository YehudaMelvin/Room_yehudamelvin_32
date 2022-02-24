package com.example.room_yehudamelvin_32

import android.graphics.Movie
import android.location.GnssAntennaInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room_yehudamelvin_32.room.movie
import kotlinx.android.synthetic.main.list_movie.view.*

class MovieAdapter(private val movies: ArrayList<movie>, private val listener: OnAdapterListener)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie  = movies[position]
        holder.view.text_tittle.text = movie.tittle
        holder.view.text_tittle.setOnClickListener {
            listener.onClick(movie)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(movie)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(movie)
        }
    }



    override fun getItemCount() = movies.size

    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<movie>){
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(movie: movie)
        fun onUpdate(movie: movie)
        fun onDelete(movie: movie)
    }


}