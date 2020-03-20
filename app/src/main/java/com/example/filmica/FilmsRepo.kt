package com.example.filmica

object FilmsRepo{
    val films: MutableList<Film> = mutableListOf()
    get() {
        if (field.isEmpty()){
            field.addAll(dummyFilms())
        }
        return field

    }
    private fun dummyFilms():List<Film>{
// Opcion2 map
       return  (0..9).map {
            Film(
                title = "Film $it",
                genre = "Genre $it" ,
                release = "200 $it-0$it-0$it",
                voteRating = it.toDouble(),
                overview = "Overview $it"
            )
        }



        /* // Opcion 1
        val films: MutableList<Film> = mutableListOf()
        val range: IntRange = 0..99


        for (i in 0..9){
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
        */

    }
}