package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val title: TextView
        val description: TextView
        val poster: ImageView

        init {

            title = itemView.findViewById(R.id.titleTv)
            description = itemView.findViewById(R.id.descriptionTv)
            poster = itemView.findViewById(R.id.posterIv)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.movies_item, parent, false)
        return ViewHolder(contactView)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val url = "https://image.tmdb.org/t/p/w500/"
        var fullUrl = url + movie.posterLink

        holder.title.text = movie.title
        holder.description.text = movie.description

        Glide.with(holder.itemView)
            .load(fullUrl)
            .centerInside()
            .into(holder.poster)

    }

}