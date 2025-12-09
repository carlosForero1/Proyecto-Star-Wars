package com.java.pruebasstar.data.repository

import com.java.pruebasstar.data.model.response.PersonajesDetallesRespuesta
import com.java.pruebasstar.data.remote.ApiService

class StarWarsRepository(private val api: ApiService) {

    suspend fun getCharacters() = api.getCharacters()

    suspend fun getCharacterDetail(id: String): PersonajesDetallesRespuesta {
        return api.getCharacterDetail(id)
    }

    suspend fun getPlanetName(url: String): String {
        val id = url.trimEnd('/').split("/").last()
        return api.getPlanetDetail(id).id
    }

    suspend fun getFilmTitles(urls: List<String>): List<String> {
        val titles = mutableListOf<String>()

        for (url in urls) {
            val id = url.trimEnd('/').split("/").last()
            val film = api.getFilmDetail(id)
            titles.add(film.title)
        }

        return titles
    }

    suspend fun getFilms() = api.getFilms()

    suspend fun getFilmDetail(id: String) = api.getFilmDetail(id)

    suspend fun getPlanets() = api.getPlanets()

    suspend fun getPlanetDetail(id: String) = api.getPlanetDetail(id)

    suspend fun getStarships() = api.getStarships()

    suspend fun getStarshipDetail(id: String) = api.getStarshipDetail(id)

    suspend fun getVehicles() = api.getVehicles()

    suspend fun getVehicleDetail(id: String) = api.getVehicleDetail(id)

}
