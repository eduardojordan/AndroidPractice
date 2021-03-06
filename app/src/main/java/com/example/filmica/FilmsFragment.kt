package com.example.filmica

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_films.*
import kotlinx.android.synthetic.main.layout_error.*

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
  //  instance.setFilms(FilmsRepo.films)
        instance

    }

    override fun onAttach(context: Context?) {
       super.onAttach(context)

       if (context is OnItemClickListener){
          listener = context
       }
    }

    fun reload(){
        FilmsRepo.discoverFilms(context!!,
            {films ->
                progress?.visibility = View.INVISIBLE
                layoutError?.visibility = View.INVISIBLE
                list.visibility = View.VISIBLE

                adapter.setFilms(films)
            },{error ->
                progress?.visibility = View.INVISIBLE
                list.visibility = View.INVISIBLE
                layoutError?.visibility = View.VISIBLE

                error.printStackTrace()

            })
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_films,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = adapter
        btnRetry?.setOnClickListener { reload() }
    }

    override fun onResume() {
        super.onResume()

        this.reload()
    }

    interface OnItemClickListener{
        fun onItemClicked(film:Film)

    }
}