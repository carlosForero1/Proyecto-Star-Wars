package com.java.pruebasstar.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.FilmResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class FilmDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val film: FilmResponse? = null
)
class FilmDetailViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(FilmDetailUiState())
        private set

    fun loadFilmDetail(id: String) {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)

                val film = repo.getFilmDetail(id)

                uiState.value = uiState.value.copy(
                    isLoading = false,
                    film = film
                )

            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    class Factory(private val repo: StarWarsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FilmDetailViewModel(repo) as T
        }
    }
}
