package com.java.pruebasstar.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.java.pruebasstar.data.repository.StarWarsRepository
import com.java.pruebasstar.ui.details.CharacterDetailUiState
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repo: StarWarsRepository
) : ViewModel() {

    var uiState = mutableStateOf(CharacterDetailUiState())
        private set

    fun loadCharacterDetail(id: String) {
        viewModelScope.launch {
            try {
                uiState.value = uiState.value.copy(isLoading = true)

                val detail = repo.getCharacterDetail(id)
                val planet = repo.getPlanetName(detail.homeworld)
                val films = repo.getFilmTitles(detail.films)

                uiState.value = uiState.value.copy(
                    isLoading = false,
                    name = detail.name,
                    height = detail.height,
                    mass = detail.mass,
                    gender = detail.gender,
                    planet = planet,
                    films = films
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
        androidx.lifecycle.ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharacterDetailViewModel(repo) as T
        }
    }
}

