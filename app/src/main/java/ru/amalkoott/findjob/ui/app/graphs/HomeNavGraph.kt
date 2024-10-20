package ru.amalkoott.findjob.ui.app.graphs

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.flow.StateFlow
import ru.amalkoott.findjob.ui.HomeScreen
import ru.amalkoott.findjob.domain.entities.Vacancy
import ru.amalkoott.findjob.ui.app.screens.details.VacancyContent
import ru.amalkoott.findjob.ui.app.screens.home.FavoriteContent
import ru.amalkoott.findjob.ui.app.screens.home.MessageContent
import ru.amalkoott.findjob.ui.app.screens.home.ProfileContent
import ru.amalkoott.findjob.ui.app.screens.home.ResponseContent
import ru.amalkoott.findjob.ui.app.screens.home.SearchContent
import ru.amalkoott.findjob.ui.app.view.HomeViewModel

@Composable
fun HomeNavGraph(navController: NavHostController, viewModel: HomeViewModel) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreen.Home.route,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(350)) },
        exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(350)) },
        popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(350)) },
        popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(350))}
    ) {
        composable(route = HomeScreen.Home.route) {
            SearchContent(
                name = HomeScreen.Home.title,
                onClick = {
                    navController.navigate(Graph.VACANCY)
                },
                viewModel = viewModel
            )
        }
        composable(route = HomeScreen.Favorites.route) {
            FavoriteContent(
                name = HomeScreen.Favorites.title,
                onClick = {
                    navController.navigate(Graph.VACANCY)
                },
                viewModel = viewModel
            )
        }
        composable(route = HomeScreen.Response.route) {
            ResponseContent(
                name = HomeScreen.Response.title,
                onClick = { }
            )
        }
        composable(route = HomeScreen.Message.route) {
            MessageContent(
                name = HomeScreen.Message.title,
                onClick = { }
            )
        }
        composable(route = HomeScreen.Profile.route) {
            ProfileContent(
                name = HomeScreen.Profile.title,
                onClick = { }
            )
        }
        detailsNavGraph(navController = navController, viewModel.selectedVacancy )
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController, vacancy: StateFlow<Vacancy?>) {
    navigation(
        route = Graph.VACANCY,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            val selectedVacancy = vacancy.collectAsState()
            VacancyContent(
                name = DetailsScreen.Information.route,
                onClick = { navController.navigate(DetailsScreen.Overview.route)},
                vacancy = selectedVacancy)
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}
