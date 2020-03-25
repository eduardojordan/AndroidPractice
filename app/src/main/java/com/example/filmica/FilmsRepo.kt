package com.example.filmica

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Method

object FilmsRepo {
   private  val films: MutableList<Film> = mutableListOf()


    fun findFilmById(id: String): Film? {
        return films.find { film -> film.id == id }
    }

    private fun dummyFilms(): List<Film> {

        return (0..9).map {
            Film(
                title = "Film $it",
                genre = "Genre $it",
                release = "200 $it-0$it-0$it",
                voteRating = it.toDouble(),
                overview = "Overview $it"
            )
        }
    }

    fun discoverFilms(
        context: Context,
        callbackSuccess: ((MutableList<Film>) -> Unit),
        callbackError: ((VolleyError) -> Unit)
    ) {

        if (this.films.isEmpty()) {
            val url = ApiRoutes.discoverUrl()
            val request = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val newFilms = Film.parseFilms(response)
                    this.films.addAll(newFilms)
                    callbackSuccess.invoke(this.films)
                },
                { error ->
                    callbackError.invoke(error)
                })

            Volley.newRequestQueue(context)
                .add(request)
        } else {
            callbackSuccess.invoke((this.films))
        }


    }
}