package com.madoka.mypokedexapp.util

sealed class Resource<T>(
    val data: T? = null, val message: String? = null
) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data)
    class Loading<T> : Resource<T>()//will use this loading in our PokemonDetailViewModel
}