package com.example.filmica

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_film.view.*

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(id: String): DetailsFragment {
            val instance = DetailsFragment()
            val args = Bundle()
            args.putString("id", id)
            instance.arguments = args

            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: String = arguments?.getString("id") ?: ""
        val film = FilmsRepo.findFilmById(id)

        film?.let {
            with(film) {
                labelTitle.text = title
                labelOverview.text = overview
                labelGenre.text = genre
                labelRelease.text = release
                labelVotes.text = voteRating.toString()

                loadImage(film)
            }

        }

        btnAdd.setOnClickListener { view ->
            Toast.makeText(context, "Added to list", Toast.LENGTH_LONG).show()
        }

    }

    private fun loadImage(film: Film) {
        val target = SimpleTarget(successCallback = { bitmap, from ->
            imgPoster.setImageBitmap(bitmap)
            setColorFrom(bitmap)

        })

        imgPoster.tag = target

        Picasso.get()
            .load(film.getPosterUrl())
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(target)
    }

    private fun setColorFrom(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val defaultColor = ContextCompat.getColor(context!!, R.color.colorPrimary)
            val swatch = palette?.vibrantSwatch ?: palette?.dominantSwatch
            val color = swatch?.rgb ?: defaultColor
            val overlayColor = Color.argb(
                (Color.alpha(color) * 0.5).toInt(),
                Color.red(color),
                Color.green(color),
                Color.blue(color)
            )
            overlay.setBackgroundColor(overlayColor)
            btnAdd.backgroundTintList = ColorStateList.valueOf(color)

        }
    }
}