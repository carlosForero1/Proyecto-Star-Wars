package com.java.pruebasstar.ui.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")

    object Home : Screen("home/{name}/{side}") {
        fun createRoute(name: String, side: String) = "home/$name/$side"
    }
    object Characters : Screen("personajes")

    object CharacterDetail : Screen("characters/{id}") {
        fun createRoute(id: String) = "characters/$id"
    }

    object Films : Screen("films")

    object FilmDetail : Screen("films/{id}") {
        fun createRoute(id: String) = "films/$id"
    }

    object Planets : Screen("planets")
    object PlanetDetail : Screen("planets/{id}") {
        fun createRoute(id: String) = "planets/$id"
    }
    object Starships : Screen("starships")
    object StarshipDetail : Screen("starships/{id}") {
        fun createRoute(id: String) = "starships/$id"
    }

    object Vehicles : Screen("vehicles")
    object VehicleDetail : Screen("vehicles/{id}") {
        fun createRoute(id: String) = "vehicles/$id"
    }

}
