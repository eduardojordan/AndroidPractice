package com.example.filmica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FilmActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)
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

    fun showDetails(clickedView: View){
        val intentToDetails : Intent = Intent(this,DetailsActivity::class.java)
        startActivity(intentToDetails)

    }
}