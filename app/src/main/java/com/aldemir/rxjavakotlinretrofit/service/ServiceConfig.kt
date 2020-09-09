package com.aldemir.rxjavakotlinretrofit.service

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class ServiceConfig {

    val service: StatesAPI

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://servicodados.ibge.gov.br/api/v1/localidades/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<StatesAPI>(StatesAPI::class.java)
    }

    fun loadStates(): Observable<States>? {
        Log.d("estados", "loadStatesAPI")
        return service.listStates()
            .flatMap { filmResults -> Observable.from(filmResults) }
            .map { state ->
                States(state.id, state.sigla, state.nome, ArrayList<Regiao>())
            }
    }
}