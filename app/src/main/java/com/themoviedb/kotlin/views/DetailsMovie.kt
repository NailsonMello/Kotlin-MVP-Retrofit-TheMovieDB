package com.themoviedb.kotlin.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.themoviedb.kotlin.R
import kotlinx.android.synthetic.main.activity_details_movie.*

class DetailsMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)

        var title: String? = intent.getStringExtra(getString(R.string.title))
        var poster_path: String? = intent.getStringExtra(getString(R.string.poster_path))
        var overview: String? = intent.getStringExtra(getString(R.string.overview))
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(getString(R.string.img_name) + poster_path).into(imgMovie)
        DescMovie.setText(overview.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()

    }
}
