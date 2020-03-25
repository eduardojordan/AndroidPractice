package com.example.filmica

import android.net.Uri

object ApiRoutes {
    fun discoverUrl(
        language : String = "en-US" ,
        sort: String = "popularity.desc",
        page: Int = 1
    ):String{

        return getUriBuilder()
            .appendPath("discover")
            .appendPath("movie")
            .appendQueryParameter("language", language)
            .appendQueryParameter("sort_by", sort)
            .appendQueryParameter("page", page.toString())
            .appendQueryParameter("include_adult", "false" )
            .appendQueryParameter("include_video", "false")
            .build()
            .toString()
    }

    private fun getUriBuilder() =
        Uri.Builder()
        .scheme("https")
        .authority( "api.themoviedb.org")
        .appendPath("3")
            .appendQueryParameter("api_key","bb3cda839e0cf4f922d98d8f8025e07b")
}