package com.example.filmica

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_film.view.*

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
                    with(value) {
                        labelTitle.text = value.title
                        titleGenre1.text = value.genre
                        labelVotes.text = value.voteRating.toString()

                        val target = SimpleTarget(successCallback = {bitmap, from ->

                            imgPoster.setImageBitmap(bitmap)
                            Palette.from(bitmap).generate {palette ->
                                val defaultColor = ContextCompat.getColor(itemView.context, R.color.colorPrimary)
                                val swatch = palette?.vibrantSwatch ?: palette?.dominantSwatch
                                val color = swatch?.rgb ?: defaultColor

                                container.setBackgroundColor(color)
                                containerData.setBackgroundColor(color)

                            }
                        }
                        )

                        imgPoster.tag = target


                        Picasso.get()
                            .load(value.getPosterUrl())
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.placeholder)
                            .into(target)
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