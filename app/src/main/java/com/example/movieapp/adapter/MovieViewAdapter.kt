package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieCardLayoutBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.util.gorselIndir
import com.example.movieapp.util.placeholderYap

import com.example.movieapp.view.MovieHomeFragmentDirections
import kotlinx.android.synthetic.main.movie_card_layout.view.*

class MovieViewAdapter(private var movieList:ArrayList<Movie>):RecyclerView.Adapter<MovieViewAdapter.MovieViewHolder>() ,MovieClickListener{
    class MovieViewHolder(var view : MovieCardLayoutBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        // var view = inflater.inflate(R.layout.movie_card_layout,parent,false)
        var view = DataBindingUtil.inflate<MovieCardLayoutBinding>(inflater,R.layout.movie_card_layout,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


        holder.view.movie = movieList[position]
        holder.view.listener = this



    }
    override fun getItemCount(): Int = movieList.size

    fun movieGuncelle(yeniMovieList:List<Movie>){
        movieList.clear()
        movieList.addAll(yeniMovieList)
        notifyDataSetChanged()
    }

    override fun movieTiklandi(view: View) {


        val id = view.movieId.text.toString().toIntOrNull()
           id?.let{
               val action = MovieHomeFragmentDirections.actionMovieHomeFragmentToMovieDetailsFragment(it)
               Navigation.findNavController(view).navigate(action)
           }





    }


}