package com.example.filmica

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.reflect.Method

object FilmsRepo{
    val films: MutableList<Film> = mutableListOf()
    get() {
        if (field.isEmpty()){
            field.addAll(dummyFilms())
        }
        return field

    }

    fun findFilmById(id:String): Film? {
       return films.find { film -> film.id == id }
    }

    private fun dummyFilms():List<Film>{

       return  (0..9).map {
            Film(
                title = "Film $it",
                genre = "Genre $it" ,
                release = "200 $it-0$it-0$it",
                voteRating = it.toDouble(),
                overview = "Overview $it"
            )
        }
    }

    fun discoverFilms(context: Context){
        val url = discoverUrl()
        val request = JsonObjectRequest(Request.Method.GET,url,null, {
       // Log.d( "Filmrepo", it.toString())
            parseFilms(it)
        }, {error ->
            error.printStackTrace()

        })

        Volley.newRequestQueue(context)
            .add(request)
    }

    fun parseFilms(response:JSONObject):MutableList<Film>{
      val films = mutableListOf<Film>()
    val filmsArray = response.getJSONArray("results")

        for(i in 0..(filmsArray.length() - 1)){
            Log.d("FilmsRepo",filmsArray.getJSONObject(i).toString())
        }
        return films
    }

    fun discoverUrl(
        language : String = "en-US" ,
        sort: String = "popularity.desc",
        page: Int = 1
    ):String{

        return Uri.Builder()
            .scheme("https")
            .authority( "api.themoviedb.org")
            .appendPath("3")
            .appendPath("discover")
            .appendPath("movie")
            .appendQueryParameter("api_key","bb3cda839e0cf4f922d98d8f8025e07b")
            .appendQueryParameter("language", language)
            .appendQueryParameter("sort_by", sort)
            .appendQueryParameter("page", page.toString())
            .appendQueryParameter("include_adult", "false" )
            .appendQueryParameter("include_video", "false")
            .build()
            .toString()
    }
}