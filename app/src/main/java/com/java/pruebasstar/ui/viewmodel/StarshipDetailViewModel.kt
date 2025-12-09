package com.java.pruebasstar.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.StarshipResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class StarshipDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val ship: StarshipResponse? = null
)

class StarshipDetailViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(StarshipDetailUiState())
        private set

    fun loadStarshipDetail(id: String) {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)

                val starship = repo.getStarshipDetail(id)

                uiState.value = uiState.value.copy(
                    isLoading = false,
                    ship = starship
                )

            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Error desconocido"
                )
            }
        }
    }

    class Factory(private val repo: StarWarsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StarshipDetailViewModel(repo) as T
        }
    }
}
