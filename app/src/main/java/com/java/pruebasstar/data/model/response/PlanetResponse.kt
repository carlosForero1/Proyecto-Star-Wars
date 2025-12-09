package com.java.pruebasstar.data.model.response

data class PlanetResponse(
    val name: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val gravity: String,
    val rotation_period: String,
    val orbital_period: String,
    val diameter: String,
    val surface_water: String,
    val url: String
) {
    val id: String
        get() = url.trimEnd('/').substringAfterLast("/")
}
