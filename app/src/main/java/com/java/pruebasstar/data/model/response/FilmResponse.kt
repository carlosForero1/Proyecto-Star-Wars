package com.java.pruebasstar.data.model.response

data class FilmResponse(
    val title: String,
    val episode_id: Int,
    val release_date: String,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val url: String
) {
    val id: String
        get() = url.trimEnd('/').substringAfterLast("/")
}
