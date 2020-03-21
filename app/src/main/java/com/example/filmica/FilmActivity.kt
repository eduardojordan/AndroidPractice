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
            this.showDetails()
        }
       }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

     //   val list = findViewById<RecyclerView>(R.id.list_films)
     //   list.layoutManager = LinearLayoutManager(this)

    //    val adapter = FilmsAdapter {film ->
            // COn esto se ve en consola
           // Log.d(FilmActivity::class.java.canonicalName, item.toString())
      //      this.showDetails()

     //   }
        list.adapter = adapter
        adapter.setFilms(FilmsRepo.films)
    }
 //       val button: Button = findViewById(R.id.btn_film)
 //       button.setOnClickListener{
       //        view ->
      //      Toast.makeText( this, "Added to list", Toast.LENGTH_LONG).show()
            // Para revisar en en el log
        //    Log.d(FilmActivity::class.java.canonicalName,"Button was clicked")
            // Para llamar una nueva actividad
       //     val intentToDetails : Intent = Intent(this,DetailsActivity::class.java)
  //          startActivity(intentToDetails)
  //      }
 //   }

    fun showDetails(){
        val intentToDetails : Intent = Intent(this,DetailsActivity::class.java)
        startActivity(intentToDetails)

    }
}