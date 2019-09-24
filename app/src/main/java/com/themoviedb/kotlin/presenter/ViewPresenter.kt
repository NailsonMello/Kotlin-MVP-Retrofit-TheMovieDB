package com.themoviedb.kotlin.presenter

import com.themoviedb.kotlin.contract.ActivityContract
import com.themoviedb.kotlin.entity.TopMoviesResponse
import com.themoviedb.kotlin.models.MovieModel
import retrofit2.Response

class ViewPresenter(internal var mView: ActivityContract.View) : ActivityContract.Presenter,
    ActivityContract.APIListener {


    internal var mModel: ActivityContract.Model

    init {
        mModel = MovieModel()

        mView.setupUI()
        getTopMovies(mView.apiKey, mView.id_genre)
    }


    override fun getTopMovies(apiKey: String, id_genre: String) {

        mModel.getTopMovies(apiKey, id_genre, this)

    }

    override fun getSearchTopMovie(apiKey: String, id_genre: String) {
        mModel.getSearchTopMovie(apiKey, id_genre, this)
    }

    override fun onSuccess(response: Response<TopMoviesResponse>) {

        mView.displayMovieData(response.body()!!.results!!)
    }

    override fun onError(response: Response<TopMoviesResponse>) {

        mView.showMessage("Ocorreu um erro")
    }

    override fun onFailure(t: Throwable) {

        mView.showMessage(t.message!!)
    }

}
