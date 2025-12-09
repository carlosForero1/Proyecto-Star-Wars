package com.java.pruebasstar.ui.details

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val height: String = "",
    val mass: String = "",
    val gender: String = "",
    val planet: String = "",
    val films: List<String> = emptyList(),
    val error: String? = null
)
