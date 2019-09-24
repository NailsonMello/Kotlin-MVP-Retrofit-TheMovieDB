package com.themoviedb.kotlin.models

import com.themoviedb.kotlin.contract.ActivityContract
import com.themoviedb.kotlin.entity.TopMoviesResponse
import com.themoviedb.kotlin.network.TmdbApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieModel : ActivityContract.Model {
    val URL = "https://api.themoviedb.org"

    override fun getTopMovies(apiKey: String, id_genre: String, listener: ActivityContract.APIListener) {

        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create<TmdbApiInterface>(TmdbApiInterface::class.java)

            val options = HashMap<String, String>()
            options["api_key"] = apiKey
            options["language"] = "pt-br"
            options["sort_by"] = "popularity.desc"
            options["include_adult"] = "false"
            options["include_video"] = "false"
            options["with_genres"] = id_genre
            options["page"] = "1"
            val call = service.getTopMovies(options)

            SearchMovie(call, listener)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getSearchTopMovie(apiKey: String, id_genre: String, listener: ActivityContract.APIListener) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create<TmdbApiInterface>(TmdbApiInterface::class.java)

            val options = HashMap<String, String>()
            options["api_key"] = apiKey
            options["language"] = "pt-br"
            options["sort_by"] = "popularity.desc"
            options["include_adult"] = "false"
            options["include_video"] = "false"
            options["query"] = id_genre
            options["page"] = "1"

            val call = service.getSearchTopMovie(options)
            SearchMovie(call, listener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun SearchMovie(
        call: Call<TopMoviesResponse>,
        listener: ActivityContract.APIListener
    ) {
        call.enqueue(object : Callback<TopMoviesResponse> {
            override fun onResponse(call: Call<TopMoviesResponse>, response: Response<TopMoviesResponse>) {

                if (response.isSuccessful) {

                    listener.onSuccess(response)
                } else {

                    listener.onError(response)
                }
            }

            override fun onFailure(call: Call<TopMoviesResponse>, t: Throwable) {

                listener.onFailure(t)
            }
        })
    }
}