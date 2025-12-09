package com.java.pruebasstar.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.VehicleResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

class VehiclesViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(VehiclesUiState())

    init { loadVehicles() }

    private fun loadVehicles() {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)
                val response = repo.getVehicles()
                uiState.value = VehiclesUiState(vehicles = response.results)
            } catch (e: Exception) {
                uiState.value = VehiclesUiState(error = e.message)
            }
        }
    }

    data class VehiclesUiState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val vehicles: List<VehicleResponse> = emptyList()
    )

    class Factory(private val repo: StarWarsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return VehiclesViewModel(repo) as T
        }
    }
}
