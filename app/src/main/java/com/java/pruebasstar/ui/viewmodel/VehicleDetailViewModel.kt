package com.java.pruebasstar.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.model.response.VehicleResponse
import com.java.pruebasstar.data.repository.StarWarsRepository
import kotlinx.coroutines.launch

data class VehicleDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val vehicle: VehicleResponse? = null
)

class VehicleDetailViewModel(private val repo: StarWarsRepository) : ViewModel() {

    var uiState = mutableStateOf(VehicleDetailUiState())
        private set

    fun loadVehicleDetail(id: String) {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)

                val vehicle = repo.getVehicleDetail(id)

                uiState.value = uiState.value.copy(
                    isLoading = false,
                    vehicle = vehicle
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
            return VehicleDetailViewModel(repo) as T
        }
    }
}
