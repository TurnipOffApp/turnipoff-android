package fr.insideapp.turnipoff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.insideapp.turnipoff.ui.Navigator
import fr.insideapp.turnipoff.ui.screens.HomeScreen
import fr.insideapp.turnipoff.ui.theme.TurnipOffTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TurnipOffTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold {
                        NavigationComponent(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator = Navigator()
) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it.label)
        }.launchIn(this)
    }
    NavHost(
        navController = navController,
        startDestination = Navigator.NavTarget.Home.label
    ) {
        composable(Navigator.NavTarget.Home.label) {
            HomeScreen(navController)
        }
        /*composable("movie") {
            DetailScreen()
        }
        composable("actor") {
            DetailScreen()
        }*/
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TurnipOffTheme {
        Greeting("Android")
    }
}