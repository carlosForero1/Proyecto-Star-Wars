package com.java.pruebasstar.data.model.response

data class VehicleResponse(
    val name: String,
    val model: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val url: String
) {
    val id: String
        get() = url.trimEnd('/').substringAfterLast("/")
}
