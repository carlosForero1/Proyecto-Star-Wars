package com.java.pruebasstar.data.remote


import com.java.pruebasstar.data.model.CharacterResponse
import com.java.pruebasstar.data.model.response.FilmListResponse
import com.java.pruebasstar.data.model.response.FilmResponse
import com.java.pruebasstar.data.model.response.PersonajesDetallesRespuesta
import com.java.pruebasstar.data.model.response.PlanetListResponse
import com.java.pruebasstar.data.model.response.PlanetResponse
import com.java.pruebasstar.data.model.response.StarshipResponse
import com.java.pruebasstar.data.model.response.VehicleListResponse
import com.java.pruebasstar.data.model.response.VehicleResponse
import com.java.pruebesstar.data.model.response.StarshipListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("people/")
    suspend fun getCharacters(): CharacterResponse

    @GET("people/{id}/")
    suspend fun getCharacterDetail(@Path("id") id: String): PersonajesDetallesRespuesta

    @GET("films/")
    suspend fun getFilms(): FilmListResponse

    @GET("films/{id}/")
    suspend fun getFilmDetail(@Path("id") id: String): FilmResponse

    @GET("planets/")
    suspend fun getPlanets(): PlanetListResponse

    @GET("planets/{id}/")
    suspend fun getPlanetDetail(@Path("id") id: String): PlanetResponse

    @GET("starships/")
    suspend fun getStarships(): StarshipListResponse

    @GET("starships/{id}/")
    suspend fun getStarshipDetail(@Path("id") id: String): StarshipResponse

    @GET("vehicles/")
    suspend fun getVehicles(): VehicleListResponse

    @GET("vehicles/{id}/")
    suspend fun getVehicleDetail(@Path("id") id: String): VehicleResponse


}