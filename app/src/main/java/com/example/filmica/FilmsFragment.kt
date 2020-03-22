package com.example.filmica

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FilmsFragment: Fragment() {
    val list: RecyclerView by lazy {

        val instance =  view!!.findViewById<RecyclerView>(R.id.list_films)
        instance.layoutManager = LinearLayoutManager( this.context)

        instance
    }

    val adapter: FilmsAdapter by lazy {
        FilmsAdapter { film ->
            this.showDetails(film.id)
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_films,container, false)

    }

    fun showDetails(filmId: String){
        val intentToDetails : Intent = Intent(this.context,DetailsActivity::class.java)
        intentToDetails.putExtra( "id", filmId)
        startActivity(intentToDetails)

    }
}