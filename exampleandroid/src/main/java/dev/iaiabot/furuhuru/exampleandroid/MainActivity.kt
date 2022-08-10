package dev.iaiabot.furuhuru.exampleandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.iaiabot.furuhuru.entity.ApplicationInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
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
