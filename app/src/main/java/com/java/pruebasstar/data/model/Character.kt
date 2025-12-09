package com.java.pruebasstar.data.model

data class Character(
    val name: String,
    val url: String
) {
    val id: String
        get() = url.trimEnd('/').substringAfterLast("/")
}
