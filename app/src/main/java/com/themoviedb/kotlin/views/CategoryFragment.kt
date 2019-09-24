package com.themoviedb.kotlin.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.themoviedb.kotlin.R
import com.themoviedb.kotlin.adapter.MoviesAdapter
import com.themoviedb.kotlin.contract.ActivityContract
import com.themoviedb.kotlin.entity.Movie
import com.themoviedb.kotlin.presenter.ViewPresenter
import kotlinx.android.synthetic.main.fragment_acao.*


class CategoryFragment(num: String) : Fragment(), ActivityContract.View {
    val nu: String = num
    internal lateinit var mPresenter: ActivityContract.Presenter
    internal lateinit var movieList: MutableList<Movie>
    internal lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        mPresenter = ViewPresenter(this@CategoryFragment)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_acao, container, false)
        val rec = rootView.findViewById(R.id.recyclerView) as RecyclerView
        rec.setLayoutManager(GridLayoutManager(activity, 2))
        rec.adapter = moviesAdapter

        return rootView
    }

    override val apiKey: String
        get() = getString(R.string.api_key)
    override val id_genre: String
        get() = nu

    override fun setupUI() {
        recyclerView
        movieList = ArrayList()
        moviesAdapter = MoviesAdapter(activity!!, movieList)

    }

    override fun displayMovieData(moviesList: List<Movie>) {
        movieList.clear()
        movieList.addAll(moviesList)
        moviesAdapter.notifyDataSetChanged()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}
