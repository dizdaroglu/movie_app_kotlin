package com.example.movieapp.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.PopularMovies
import com.example.movieapp.service.MovieDatabase
import com.example.movieapp.service.ServiceBuilder
import com.example.movieapp.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MovieHomeViewModel(application: Application) : BaseViewModel(application) {

    var APIKEY="d9d25ba60c3c37758dfa035b7f861397"

    val movies = MutableLiveData<List<Movie>>()
    val movieYukleniyor = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val guncellemeZamani = 5 * 60 * 1000 * 1000 * 1000L
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())


    /*
      .subscribe({
                          response -> println(response)},
                      {t -> println(t)
                      })
       */


    fun refreshData(){
        val kaydedilmeZamani =ozelSharedPreferences.zamaniAl()

        if(kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani){
            getDataFromSQLite()
        }else {
            getDataFromInternet()
        }

    }
    fun refreshFromInternet(){
        getDataFromInternet()
    }
    private fun getDataFromInternet(){
            movieYukleniyor.value = true

            disposable.add(
                ServiceBuilder.buildService().getMovies(APIKEY)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribeWith(object :DisposableSingleObserver<PopularMovies>(){
                        override fun onSuccess(t: PopularMovies) {
                           saveSQLite(t)
                            Toast.makeText(getApplication(),"Internetten aldım",Toast.LENGTH_SHORT).show()
                        }
                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
            )
    }
    private fun getDataFromSQLite(){
        movieYukleniyor.value = true

        launch {
            val movieList = MovieDatabase(getApplication()).movieDao().getAllMovie()
            displayMovies(movieList)
            Toast.makeText(getApplication(),"Roomdan aldım",Toast.LENGTH_SHORT).show()
        }
    }
    fun saveSQLite(popularMovies: PopularMovies){
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()

            dao.deleteAllMovie()
            dao.insertAll(*popularMovies.results.toTypedArray())

            displayMovies(popularMovies.results)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
    private fun displayMovies(movieList : List<Movie>){
        movieYukleniyor.value = false
        movies.value=movieList
    }
}