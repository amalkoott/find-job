package ru.amalkoott.findjob.ui.app.graphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.amalkoott.findjob.ui.app.screens.login.LoginContent
import ru.amalkoott.findjob.ui.app.screens.login.WithEmailContent
import ru.amalkoott.findjob.ui.app.view.LoginViewModel

fun NavGraphBuilder.loginNavGraph(navController: NavHostController) {
    lateinit var viewModel: LoginViewModel
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = LoginScreen.Login.route
    ) {
        composable(route = LoginScreen.Login.route) {
            viewModel = hiltViewModel<LoginViewModel>()
            LoginContent(
                onWithEmailClick = {
                    navController.navigate(LoginScreen.WithEmail.route)
                },
                onWithPasswordClick = {
                    navController.navigate(LoginScreen.WithPassword.route)
                },
                viewModel = viewModel
            )
        }
        composable(route = LoginScreen.WithEmail.route) {
            WithEmailContent(
                name = viewModel.enteredValue,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }
            )
        }
    }
}

sealed class LoginScreen(val route: String) {
    object Login : LoginScreen(route = "LOGIN")
    object WithEmail : LoginScreen(route = "WITH_EMAIL")
    object WithPassword : LoginScreen(route = "WITH_PHONE")
}