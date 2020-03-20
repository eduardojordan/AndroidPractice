package com.example.filmica

object FilmsRepo{
    val films: MutableList<Film> = mutableListOf()
    get() {
        if (field.isEmpty()){
            field.addAll(dummyFilms())
        }
        return field

    }
    private fun dummyFilms():MutableList<Film>{
        val films: MutableList<Film> = mutableListOf()
        for (i in 1..9){
            films.add(
                Film(
                    title = "Film ${i}",
                     genre = "Genre ${i}" ,
                    release = "200 ${i}-0${i}-0${i}",
                    voteRating = i.toDouble(),
                    overview = "Overview ${i}"
                )
            )

        }
        return films
    }
}