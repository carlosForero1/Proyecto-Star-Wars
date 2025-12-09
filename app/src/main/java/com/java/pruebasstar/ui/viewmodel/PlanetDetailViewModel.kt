package com.java.pruebasstar.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.PlanetResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class PlanetDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val planet: PlanetResponse? = null
)

class PlanetDetailViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(PlanetDetailUiState())
        private set

    fun loadPlanetDetail(id: String) {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)

                val planet = repo.getPlanetDetail(id)

                uiState.value = uiState.value.copy(
                    isLoading = false,
                    planet = planet
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
            return PlanetDetailViewModel(repo) as T
        }
    }
}
