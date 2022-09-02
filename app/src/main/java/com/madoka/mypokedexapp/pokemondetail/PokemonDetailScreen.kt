package com.madoka.mypokedexapp.pokemondetail

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.madoka.mypokedexapp.data.remote.responses.Pokemon
import com.madoka.mypokedexapp.util.Resource

@Composable
fun PokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .padding(bottom = 16.dp)
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,//display the image at center of the box
            modifier = Modifier
                .fillMaxSize()
        ) {
            //display the image when the resource is success
            if (pokemonInfo is Resource.Success) {
                pokemonInfo.data?.sprites?.let {
                    Image(
                        data = it.front_default, //default image from the pokemon
                        contentDescription = pokemonInfo.data.name,
                        fadeIn = true,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .offset(y = topPadding)

                    )
                }
            }

        }
    }
}


@Composable
fun PokemonDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopCenter, modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent
                    )
                )
            )
    ) {
//back arrow icon
       Icon(imageVector = Icons.Default.ArrowBack,//from material icons
       contentDescription = null,
           tint = Color.White,
           modifier = Modifier
               .size(36.dp)
               .offset(16.dp,16.dp)
               .clickable {
                   navController.popBackStack()
               }
           )
    }
}


@Composable
fun PokemonDetailStateWrapper







