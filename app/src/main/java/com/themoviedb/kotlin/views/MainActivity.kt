package com.themoviedb.kotlin.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.widget.SearchView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.themoviedb.kotlin.R
import com.themoviedb.kotlin.adapter.FragmentAdapter
import com.themoviedb.kotlin.adapter.MoviesAdapter
import com.themoviedb.kotlin.contract.ActivityContract
import com.themoviedb.kotlin.entity.Movie
import com.themoviedb.kotlin.presenter.ViewPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ActivityContract.View {

    internal lateinit var mPresenter: ActivityContract.Presenter
    internal lateinit var movieList: MutableList<Movie>
    internal lateinit var moviesAdapter: MoviesAdapter
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.titleMain)
        toolbar.setTitleTextColor(Color.WHITE)
        viewPager = findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager!!)

        tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
        mPresenter = ViewPresenter(this@MainActivity)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val item: MenuItem = menu.findItem(R.id.action_search)

        searchView = item.actionView as SearchView
        searchView.queryHint = getString(R.string.Pesquisar)

        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(Color.WHITE)
                var sea = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
                sea.setHintTextColor(Color.BLACK)


                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                toolbar.setBackgroundColor(ContextCompat.getColor(applicationContext,
                    R.color.colorPrimary
                ))
                searchView.setQuery("", false)

                return true
            }
        })

        searchView.maxWidth = Int.MAX_VALUE

        searchMovie(searchView)

        return true
    }

    private fun searchMovie(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }


            override fun onQueryTextChange(texto: String?): Boolean {

                if (texto!!.isEmpty()) {
                    recyclerViewSearch.visibility = View.GONE
                    viewPager?.visibility = View.VISIBLE
                    tabs.visibility = View.VISIBLE

                } else {
                    mPresenter.getSearchTopMovie(getString(R.string.api_key), texto.toString())
                    recyclerViewSearch.visibility = View.VISIBLE
                    viewPager?.visibility = View.GONE
                    tabs.visibility = View.GONE

                }

                return true
            }

        })
    }

    override val apiKey: String
        get() = getString(R.string.api_key)
    override val id_genre: String
        get() = ""

    override fun setupUI() {
        recyclerViewSearch

        movieList = ArrayList()
        moviesAdapter = MoviesAdapter(this, movieList)

        recyclerViewSearch.setLayoutManager(GridLayoutManager(this@MainActivity, 2))
        recyclerViewSearch.adapter = moviesAdapter
    }

    override fun displayMovieData(moviesList: List<Movie>) {
        movieList.clear()
        movieList.addAll(moviesList)
        moviesAdapter.notifyDataSetChanged()
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
        super.onBackPressed()
    }
}
