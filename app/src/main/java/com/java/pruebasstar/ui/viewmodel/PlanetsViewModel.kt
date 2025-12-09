package com.java.pruebasstar.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.PlanetResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class PlanetsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val planets: List<PlanetResponse> = emptyList()
)

class PlanetsViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(PlanetsUiState())
        private set

    init {
        loadPlanets()
    }

    private fun loadPlanets() {
        uiState.value = uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val response = repo.getPlanets()
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    planets = response.results
                )
            } catch (e: Exception) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Error al cargar planetas"
                )
            }
        }
    }

    class Factory(private val repo: StarWarsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetsViewModel(repo) as T
        }
    }
}
