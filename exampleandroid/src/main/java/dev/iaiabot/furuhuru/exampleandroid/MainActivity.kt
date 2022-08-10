package dev.iaiabot.furuhuru.exampleandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.iaiabot.furuhuru.entity.ApplicationInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {

                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val applicationInfo = ApplicationInfo("Furuhuru_example", "12.345")

                    NavHost(navController = navController, startDestination = "first") {
                        composable("first") {
                            FirstScreen(navController, applicationInfo)
                        }
                        composable("second") {
                            SecondScreen(navController, applicationInfo)
                        }
                    }
                }
            }
        }
    }
}
