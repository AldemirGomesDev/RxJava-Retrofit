package com.aldemir.rxjavakotlinretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.aldemir.rxjavakotlinretrofit.service.ServiceConfig
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var movieAdapter : ArrayAdapter<String>
    var movies = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        listView = ListView(this)
        setContentView(listView)
        movieAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, movies)
        listView.adapter = movieAdapter

        val api = ServiceConfig()
        api.loadStates()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe ({ movie ->
                Log.d("estados", "nome: ${movie.nome}")
                movies.add("${movie.id} -- ${movie.nome} - ${movie.sigla}")
            }, { e ->
                Log.e("estados", "error: ${e.message}")
                e.printStackTrace()
            },{
                Log.d("estados", "notifyDataChanged:")
                movieAdapter.notifyDataSetChanged()
            })
    }

}