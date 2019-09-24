package com.themoviedb.kotlin.contract

import com.themoviedb.kotlin.entity.Movie
import com.themoviedb.kotlin.entity.TopMoviesResponse
import retrofit2.Response

interface ActivityContract {

    interface Model {

        fun getTopMovies(apiKey: String, id_genre: String, listener: APIListener)
        fun getSearchTopMovie(apiKey: String, id_genre: String, listener: APIListener)
    }

    interface View {
        val apiKey: String
        val id_genre: String
        fun setupUI()

        fun displayMovieData(moviesList: List<Movie>)
        fun showMessage(msg: String)
    }

    interface Presenter {

        fun getTopMovies(apiKey: String, id_genre: String)
        fun getSearchTopMovie(apiKey: String, id_genre: String)
    }

    interface APIListener {

        fun onSuccess(response: Response<TopMoviesResponse>)
        fun onError(response: Response<TopMoviesResponse>)
        fun onFailure(t: Throwable)
    }
}