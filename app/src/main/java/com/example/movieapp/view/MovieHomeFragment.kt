package com.example.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapter.MovieViewAdapter
import com.example.movieapp.databinding.FragmentMovieHomeBinding
import com.example.movieapp.viewModel.MovieHomeViewModel
import kotlinx.android.synthetic.main.fragment_movie_home.*
import kotlinx.android.synthetic.main.movie_card_layout.*

class MovieHomeFragment : Fragment() {
    private lateinit var viewModel : MovieHomeViewModel
    private  val movieViewAdapter = MovieViewAdapter(arrayListOf())
    private lateinit var dataBinding : FragmentMovieHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_home,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieHomeViewModel::class.java)
        viewModel.refreshData()

        recyclerView.adapter = movieViewAdapter



        swipeRefreshLayout.setOnRefreshListener {
            yukleniyorMovie.visibility = View.VISIBLE
            recyclerView.visibility =View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing = false
        }


        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.movieYukleniyor.observe(viewLifecycleOwner, Observer { movieyukleniyor->
            movieyukleniyor?.let {
                if(it){
                    recyclerView.visibility = View.GONE
                    yukleniyorMovie.visibility= View.VISIBLE
                }else{
                    recyclerView.visibility =View.VISIBLE
                    yukleniyorMovie.visibility=View.GONE
                }
            }

        })
        viewModel.movies.observe(viewLifecycleOwner, Observer { movieList->
            movieList?.let {
                    movieViewAdapter.movieGuncelle(it)
            }

        })
    }


}