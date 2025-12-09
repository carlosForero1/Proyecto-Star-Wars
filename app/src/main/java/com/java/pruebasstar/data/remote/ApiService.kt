package com.java.pruebasstar.data.remote


import com.java.pruebasstar.data.model.CharacterResponse
import com.java.pruebasstar.data.model.response.FilmResponse
import com.java.pruebasstar.data.model.response.PersonajesDetallesRespuesta
import com.java.pruebasstar.data.model.response.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("people/")
    suspend fun getCharacters(): CharacterResponse

    @GET("people/{id}/")
    suspend fun getCharacterDetail(@Path("id") id: String): PersonajesDetallesRespuesta

    @GET("planets/{id}/")
    suspend fun getPlanet(@Path("id") id: String): PlanetResponse

    @GET("films/{id}/")
    suspend fun getFilm(@Path("id") id: String): FilmResponse
}