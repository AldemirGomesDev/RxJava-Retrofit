package com.aldemir.rxjavakotlinretrofit.service

import com.google.gson.annotations.SerializedName

//data class StateResult(val results: List<State>)

data class StateResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sigla")
    val sigla: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("regiao")
    val regiao: Region
)

data class Region(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sigla")
    val sigla: String,
    @SerializedName("nome")
    val nome: String
)