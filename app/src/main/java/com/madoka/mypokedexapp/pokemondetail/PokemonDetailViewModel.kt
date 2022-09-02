package com.madoka.mypokedexapp.pokemondetail

import androidx.lifecycle.ViewModel
import com.madoka.mypokedexapp.data.remote.responses.Pokemon
import com.madoka.mypokedexapp.repository.PokemonRepository
import com.madoka.mypokedexapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}