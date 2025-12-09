package com.java.pruebasstar.ui.state

import com.java.pruebasstar.data.model.Character

data class CharacterUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)
