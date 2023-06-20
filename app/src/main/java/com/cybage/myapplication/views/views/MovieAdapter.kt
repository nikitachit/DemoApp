package com.cybage.myapplication.views.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cybage.myapplication.R
import com.cybage.myapplication.databinding.MovieRowLayoutBinding
import com.cybage.myapplication.views.models.Movie
import com.cybage.myapplication.views.networkservice.Constants

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {
    var movies = mutableListOf<Movie>()
    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieRowLayoutBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.txtmoviename.text = movie.title
        holder.binding.txtmovieDescription.text = movie.overview
        if(!movie.backdrop_path.isNullOrBlank())
        {
            var imgPath :String =Constants.IMAGE_PATH+movie.backdrop_path
            Glide.with(holder.itemView.context).load(imgPath).placeholder(R.drawable.ic_placeholder).into(holder.binding.imgThumbnail)

        }

    }
    override fun getItemCount(): Int {
        return movies.size
    }
}
class MovieViewHolder(val binding: MovieRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
}