package com.madoka.mypokedexapp.repository

import com.madoka.mypokedexapp.data.remote.PokeApi
import com.madoka.mypokedexapp.data.remote.responses.Pokemon
import com.madoka.mypokedexapp.data.remote.responses.PokemonList
import com.madoka.mypokedexapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {

    /**instead of using try catch design a base repository and extend the Resource the call a safe api function
     * this will help write one single line of these repository functions **/

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }

    //getting a single Pokemon
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }


}











