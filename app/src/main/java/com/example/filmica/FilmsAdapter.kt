package com.example.filmica

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.item_film.view.*
import kotlinx.android.synthetic.main.item_film.view.labelTitle
import kotlinx.android.synthetic.main.fragment_details.view.labelVotes as labelVotes1
import kotlinx.android.synthetic.main.item_film.view.titleGenre as titleGenre1


class FilmsAdapter(var itemClickListener: ((Film) -> Unit)? = null): RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

   private  val list = mutableListOf<Film>()

    override fun getItemCount(): Int {
return list.count()
    }

    override fun onCreateViewHolder(recyclerView: ViewGroup, type: Int): FilmViewHolder {
    val itemView = LayoutInflater.from(recyclerView.context).inflate(R.layout.item_film,recyclerView,false)

        return  FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film: Film = list[position]
        holder.film = film
    }

    fun setFilms(films: MutableList<Film>){
        list.clear()
        list.addAll(films)
        notifyDataSetChanged()
    }

    inner class FilmViewHolder(view: View): RecyclerView.ViewHolder(view){
var film: Film? = null
        set(value) {
            field = value

            value?.let{
                with(itemView){
                    with(value){
                        labelTitle.text = value.title
                        titleGenre.text = value.genre
                        labelVotes.text = value.voteRating.toString()
                    }
                }
            }

        }

        init {
            this.itemView.setOnClickListener {
                Log.d(FilmViewHolder:: class.java.simpleName, "Item was clicked")

                // el let hace lo mismo que lo que se ve abajo
                film?.let {
                    itemClickListener?.invoke(this.film as Film)
                }
       //         if (this.film != null)
      //          itemClickListener?.invoke(this.film as Film)

            }
        }
    }

}