package com.java.pruebasstar.data.model.response

data class PersonajesDetallesRespuesta(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>
)
