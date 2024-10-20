package com.example.bottomnavbardemo.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.StateFlow
import ru.amalkoott.findjob.ui.HomeScreen
import ru.amalkoott.findjob.ui.app.graphs.HomeNavGraph
import ru.amalkoott.findjob.ui.app.view.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(), viewModel: HomeViewModel = hiltViewModel()) {
    val counter:(HomeScreen) -> StateFlow<Int>? = { item -> viewModel.getCounter(item) }
    Scaffold(
        bottomBar = { BottomBar(navController = navController, counter = counter) }
    ) {
        HomeNavGraph(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun BottomBar(navController: NavHostController, counter: (HomeScreen) -> StateFlow<Int>?) {
    val screens = listOf(
        HomeScreen.Home,
        HomeScreen.Favorites,
        HomeScreen.Response,
        HomeScreen.Message,
        HomeScreen.Profile,

    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        Surface(modifier = Modifier
            .shadow(elevation = 2.dp, ambientColor = Color.White, shape = RectangleShape))
        {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .height(60.dp)
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController,
                        bubble = counter(screen)
                    )
                }
            }
        }


    }
}

@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun RowScope.AddItem(
    screen: HomeScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    bubble: StateFlow<Int>?
) {
    val bubbleValue = bubble?.collectAsState()
    val color = if(screen.route != currentDestination?.route) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.secondary
        BottomNavigationItem(
            label = {
                Text(text = screen.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = color,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            },
            icon = {
                Box(
                    contentAlignment = Alignment.TopEnd
                ){
                    bubbleValue.let {
                        if (it !== null && it.value != 0){
                            Box(modifier = Modifier
                                .size(10.dp)
                                .background(Color.Red, shape = RoundedCornerShape(50.dp))
                                .zIndex(4f),
                                contentAlignment = Alignment.Center){
                                Text(
                                    text = it.value.toString(),
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = "Navigation Icon",
                        tint = color,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true,
            selectedContentColor = MaterialTheme.colorScheme.secondary,
            unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,//LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        )
}