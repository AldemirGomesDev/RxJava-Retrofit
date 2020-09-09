package com.aldemir.rxjavakotlinretrofit.service

import retrofit2.http.GET
import rx.Observable

interface StatesAPI {
    @GET("estados")
    fun listStates() : Observable<List<StateResult>>
}