package com.themoviedb.kotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.themoviedb.kotlin.R
import com.themoviedb.kotlin.entity.Movie
import com.themoviedb.kotlin.views.DetailsMovie

class MoviesAdapter(context: Context, private val movieList: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var activity: Context = context
    val res = activity?.getResources()
    override fun getItemCount(): Int = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movieList[position]
        holder.itemView.setOnClickListener {

            val intent = Intent(activity, DetailsMovie::class.java)
            intent.putExtra(res?.getString(R.string.title), movie.title)
            intent.putExtra(res?.getString(R.string.poster_path), movie.poster_path)
            intent.putExtra(res?.getString(R.string.overview), movie.overview)

            activity.startActivity(intent)


        }


        holder.movieName.text = movie.original_title
        Picasso.get().load(res?.getString(R.string.img_name) + movie.poster_path).into(holder.moviePoster)

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var moviePoster: ImageView
        internal var movieName: TextView

        init {

            movieName = view.findViewById(R.id.movie_name)
            moviePoster = view.findViewById(R.id.movie_poster)

        }
    }
}
