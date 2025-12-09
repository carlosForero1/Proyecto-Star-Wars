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
}
