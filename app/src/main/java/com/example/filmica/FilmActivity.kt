package com.example.filmica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_films.*

class FilmActivity: AppCompatActivity(), FilmsFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        if (savedInstanceState == null) {
            val filmsFragment = FilmsFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.container_list, filmsFragment)
                .commit()
        }
    }

    override fun onItemClicked(film: Film) {
        showDetails(film.id)

    }
    fun showDetails(id: String){
if (isTablet())
    showDetailsFragment(id)
    else
    launchDetailsActivity(id)
    }

    private fun isTablet() = this.containerDetails != null

    private fun showDetailsFragment(id: String) {
        val detailsFragment = DetailsFragment.newInstance(id)
        //val fragment = DetailsFragment()

    supportFragmentManager.beginTransaction()
         .replace(R.id.containerDetails, detailsFragment)
         .commit()
    }

    private fun launchDetailsActivity(id: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}