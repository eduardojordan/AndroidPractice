package com.example.filmica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

       val button: Button = findViewById(R.id.btn_add)
// opcion 2
    //    val listener: (View) -> Unit = {

       // Toast.makeText( this, "Added to list", Toast.LENGTH_LONG).show()

            // Opcion 1
       // val listener: View.OnClickListener = object : View.OnClickListener{

     //     override fun onClick(v: View?) {
     //           Toast.makeText(this@MainActivity, "Added to List",Toast.LENGTH_LONG).show()

     //       }
     //   }
// Opcion 3 - Lambda
        button.setOnClickListener{ view ->
            Toast.makeText( this, "Added to list", Toast.LENGTH_LONG).show()
        }

    }
}
