package com.example.filmica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FilmActivity: AppCompatActivity() {

    val list: RecyclerView by lazy {
        val instance =  findViewById<RecyclerView>(R.id.list_films)
        instance.layoutManager = LinearLayoutManager( this)

       instance
    }

    val adapter: FilmsAdapter by lazy {
        FilmsAdapter { film ->
            this.showDetails(film.id)
        }
       }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        list.adapter = adapter
        adapter.setFilms(FilmsRepo.films)
    }

    fun showDetails(filmId: String){
        val intentToDetails : Intent = Intent(this,DetailsActivity::class.java)
        intentToDetails.putExtra( "id", filmId)
        startActivity(intentToDetails)

    }
}