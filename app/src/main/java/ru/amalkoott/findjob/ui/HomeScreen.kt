package ru.amalkoott.findjob.ui

import ru.amalkoott.findjob.R
import ru.amalkoott.findjob.domain.Countable

sealed class HomeScreen(
    val route: String,
    val title: String,
    val icon: Int,
) : Countable() {
    object Home : HomeScreen(
        route = "HOME",
        title = "Поиск",
        icon = R.drawable.ic_launcher_navigation_search_foreground
    ) {

    }

    object Favorites : HomeScreen(
        route = "FAVORITES",
        title = "Избранное",
        icon = R.drawable.ic_launcher_navigation_favorite_foreground
    ) {

    }

    object Response : HomeScreen(
        route = "RESPONSE",
        title = "Отклики",
        icon = R.drawable.ic_launcher_navigation_response_foreground
    ) {

    }

    object Message : HomeScreen(
        route = "MESSAGE",
        title = "Сообщения",
        icon = R.mipmap.ic_launcher_navigation_message_foreground
    ) {

    }

    object Profile : HomeScreen(
        route = "PROFILE",
        title = "Профиль",
        icon = R.drawable.ic_launcher_navigation_profile_foreground
    ) {

    }
}