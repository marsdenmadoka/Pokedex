package com.madoka.mypokedexapp.data.remote

import com.madoka.mypokedexapp.data.remote.responses.Pokemon
import com.madoka.mypokedexapp.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.ZoneOffset

interface PokeApi {
/**1.getting pokemon list
 *limit - page size :how many pokemon we load per page
 * offset-from which pokemon do we want to start from */

@GET("pokemon  ")
    suspend fun getPokemonList(
        @Query("limit") limit:Int,
        @Query("offset") offset: Int
    ): PokemonList

    // getting a single pokemon
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
   @Path("name")name:String
    ):Pokemon
}