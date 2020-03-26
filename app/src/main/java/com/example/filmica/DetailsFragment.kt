package com.example.filmica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_film.view.*

class DetailsFragment: Fragment() {

    companion object{
        fun newInstance(id: String): DetailsFragment{
            val instance = DetailsFragment()
            val args = Bundle()
            args.putString("id",id)
            instance.arguments = args

            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details,container ,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val id: String = arguments?.getString("id") ?: ""
        val film = FilmsRepo.findFilmById(id)

        film?.let{
            with(film){
                labelTitle.text = title
                labelOverview.text = overview
                labelGenre.text = genre
                labelRelease.text = release
                labelVotes.text = voteRating.toString()

                Picasso.get()
                    .load(getPosterUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imgPoster)
            }

        }

        btnAdd.setOnClickListener{ view ->
            Toast.makeText( context, "Added to list", Toast.LENGTH_LONG).show()
        }

    }
}