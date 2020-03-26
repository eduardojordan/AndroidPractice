package com.example.filmica

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_films.*

const val TAG_FILMS = "films"
const val TAG_WATCHLIST = "watchlist"

class FilmActivity: AppCompatActivity(), FilmsFragment.OnItemClickListener {

    private lateinit  var filmsFragment: FilmsFragment
    private lateinit  var watchlistFragment: WatchlistFragment
    private lateinit  var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films)

        if (savedInstanceState == null) {
            setupFragments()
        } else{
            val activeTag = savedInstanceState.getString("active", TAG_FILMS)
            restoreFragments(activeTag)
        }
        navigation?.setOnNavigationItemSelectedListener { item ->
            val id = item.itemId

            when (id){
                R.id.action_discover -> showMainFragment(filmsFragment, TAG_FILMS)
                R.id.action_watchlist -> showMainFragment(watchlistFragment, TAG_WATCHLIST)
            }

            true
        }
    }

    private fun restoreFragments(tag: String) {
       filmsFragment = supportFragmentManager.findFragmentByTag(TAG_FILMS) as FilmsFragment
        watchlistFragment = supportFragmentManager.findFragmentByTag(TAG_WATCHLIST) as WatchlistFragment

        activeFragment =
            if (tag == TAG_WATCHLIST)
                watchlistFragment
            else
                filmsFragment
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("active",activeFragment.tag)
    }

    private fun setupFragments() {
        filmsFragment = FilmsFragment()
        watchlistFragment = WatchlistFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.container_list, filmsFragment)
            .add(R.id.container_list, watchlistFragment)
            .hide(watchlistFragment)
            .commit()

        activeFragment = filmsFragment
    }

    override fun onItemClicked(film: Film) {
        showDetails(film.id)
     }

    private fun showMainFragment(fragment: Fragment, tagFilms: String){
supportFragmentManager.beginTransaction()
    .show(activeFragment)
    .hide(fragment)
    .commit()

        activeFragment = fragment
    }

    fun showDetails(id: String){
if (isTablet())
    showDetailsFragment(id)
    else
    launchDetailsActivity(id)
    }

    private fun isTablet() = this.containerDetails != null

    private fun showDetailsFragment(id: String) {
        val detailsFragment = DetailsFragment.newInstance(id)
        //val fragment = DetailsFragment()

    supportFragmentManager.beginTransaction()
         .replace(R.id.containerDetails, detailsFragment)
         .commit()
    }

    private fun launchDetailsActivity(id: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }


}