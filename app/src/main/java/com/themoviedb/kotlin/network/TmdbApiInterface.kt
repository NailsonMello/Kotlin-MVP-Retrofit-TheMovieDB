package com.themoviedb.kotlin.network

import com.themoviedb.kotlin.entity.TopMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TmdbApiInterface {

    @GET("/3/discover/movie")
    fun getTopMovies(@QueryMap options: Map<String, String>): Call<TopMoviesResponse>

    @GET("/3/search/movie")
    fun getSearchTopMovie(@QueryMap options: Map<String, String>): Call<TopMoviesResponse>
}