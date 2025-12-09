package com.java.pruebasstar.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.Character
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class CharactersUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val characters: List<Character> = emptyList(),
    val searchQuery: String = ""
)

class CharacterViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(CharactersUiState())
        private set

    init {
        println("VIEWMODEL INIT: cargando personajes...")
        loadCharacters() }

    private fun loadCharacters() {
        uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val response = repo.getCharacters()
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    characters = response.results
                )
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Error al cargar personajes"

                )
            }
        }
    }

    fun onSearchChange(query: String) {
        uiState.value = uiState.value.copy(searchQuery = query)
    }

    fun filteredCharacters(): List<Character> {
        val query = uiState.value.searchQuery.lowercase()
        return uiState.value.characters.filter {
            it.name.lowercase().contains(query)
        }
    }

    class Factory(private val repo: StarWarsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharacterViewModel(repo) as T
        }
    }
}
