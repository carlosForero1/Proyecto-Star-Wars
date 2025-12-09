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
        return api.getPlanet(id).name
    }

    suspend fun getFilmTitles(urls: List<String>): List<String> {
        val titles = mutableListOf<String>()

        for (url in urls) {
            val id = url.trimEnd('/').split("/").last()
            val film = api.getFilm(id)
            titles.add(film.title)
        }

        return titles
    }
}
