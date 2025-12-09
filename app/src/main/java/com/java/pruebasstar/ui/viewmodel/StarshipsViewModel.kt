package com.java.pruebasstar.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.StarshipResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

class StarshipsViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(
        StarshipsUiState()
    )

    init { loadStarships() }

    private fun loadStarships() {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)
                val response = repo.getStarships()
                uiState.value = StarshipsUiState(films = response.results)
            } catch (e: Exception) {
                uiState.value = StarshipsUiState(error = e.message)
            }
        }
    }

    data class StarshipsUiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val films: List<StarshipResponse> = emptyList()
    )

    class Factory(private val repo: StarWarsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StarshipsViewModel(repo) as T
        }
    }
}
