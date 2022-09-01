package com.madoka.mypokedexapp.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.graphics.Palette
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madoka.mypokedexapp.models.PokedexListEntry
import com.madoka.mypokedexapp.repository.PokemonRepository
import com.madoka.mypokedexapp.util.Constants.PAGE_SIZE
import com.madoka.mypokedexapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
/*Together with pagination*/
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var curPage = 0

    //composable states
    var PokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    fun loadPokemonPaginated() {
        isLoading.value = true
        viewModelScope.launch {
            val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    endReached.value =
                        curPage * PAGE_SIZE >= result.data!!.count  //get the number of pokemon

                    val pokedexEnries = result.data.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(
                            entry.name.capitalize(java.util.Locale.ROOT),
                            url,
                            number.toInt()
                        )
                    }
                    curPage++
                    loadError.value = ""
                    isLoading.value = false
                    PokemonList.value += pokedexEnries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }


    //calculate the dominant color
    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate() { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}