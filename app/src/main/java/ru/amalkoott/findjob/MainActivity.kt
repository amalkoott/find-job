package ru.amalkoott.findjob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.amalkoott.findjob.ui.app.graphs.RootNavigationGraph
import ru.amalkoott.findjob.ui.theme.FindJobTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindJobTheme(darkTheme = true) {
                RootNavigationGraph(navController = rememberNavController())
            }
        }
    }
}