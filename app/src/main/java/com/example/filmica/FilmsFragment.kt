package com.example.filmica

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FilmsFragment: Fragment() {
    lateinit var listener: OnItemClickListener

    val list: RecyclerView by lazy {

        val instance =  view!!.findViewById<RecyclerView>(R.id.list_films)
        //instance.layoutManager = LinearLayoutManager( this.context)
        instance.addItemDecoration(itemOffsetDecoration(R.dimen.offset_grid))

        instance
    }

    val adapter: FilmsAdapter by lazy {
        val instance = FilmsAdapter { film ->
            this.listener.onItemClicked(film)
        }
    instance.setFilms(FilmsRepo.films)
        instance

    }

    override fun onAttach(context: Context?) {
       super.onAttach(context)

       if (context is OnItemClickListener){
          listener = context
       }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_films,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = adapter
    }



    interface OnItemClickListener{
        fun onItemClicked(film:Film)

    }
}