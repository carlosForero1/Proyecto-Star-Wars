package com.java.pruebasstar.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.FilmResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class FilmsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val films: List<FilmResponse> = emptyList()
)

class FilmsViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(FilmsUiState())
        private set

    init {
        loadFilms()
    }

    private fun loadFilms() {
        uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val response = repo.getFilms()
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    films = response.results
                )
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Error al cargar películas"
                )
            }
        }
    }

    class Factory(private val repo: StarWarsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilmsViewModel(repo) as T
        }
    }
}
