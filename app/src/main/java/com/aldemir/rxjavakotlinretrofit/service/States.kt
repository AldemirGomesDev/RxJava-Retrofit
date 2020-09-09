package com.aldemir.rxjavakotlinretrofit.service


data class States(
    val id: Int,
    val sigla: String,
    val nome: String,
    val regiao : MutableList<Regiao>
)

data class Regiao(
    val id: Int,
    val sigla: String,
    val nome: String
)
