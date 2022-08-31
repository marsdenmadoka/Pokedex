package com.madoka.mypokedexapp.data.remote.responses

data class Pokemon(
    val height: Int,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val sprites: Sprites,
    val stats: List<Stat>,
    val weight: Int
)