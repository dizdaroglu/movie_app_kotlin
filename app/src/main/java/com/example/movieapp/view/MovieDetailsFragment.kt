package com.example.movieapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.util.gorselIndir
import com.example.movieapp.util.placeholderYap
import com.example.movieapp.viewModel.MovieDetailsViewModel
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : Fragment() {
    private var movieId:Int = 0
    private lateinit var  viewModel : MovieDetailsViewModel
    private lateinit var dataBinding : FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            movieId = MovieDetailsFragmentArgs.fromBundle(it).movieId
        }

        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        viewModel.veriyiRoomdanAl(movieId)

        observeLiveData()
    }

   private fun observeLiveData(){
        viewModel.movieData.observe(viewLifecycleOwner, Observer {movie->
            movie?.let {

                dataBinding.secilenMovie = it
                println(it)
            }
        })
    }
}