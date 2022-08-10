package dev.iaiabot.furuhuru.exampleandroid

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import dev.iaiabot.furuhuru.decorator.IssueBodyBuilder
import dev.iaiabot.furuhuru.entity.ApplicationInfo

@Composable
fun SecondScreen(
    navController: NavController,
    applicationInfo: ApplicationInfo,
) {
    val observer = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.d("koko", "second on_create")
                }
                Lifecycle.Event.ON_START -> {
                    Log.d("koko", "second on_start")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("koko", "second on_resume")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("koko", "second on_pause")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("koko", "second on_stop")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.d("koko", "second on_destroy")
                }
                Lifecycle.Event.ON_ANY -> {
                }
            }
        }
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(Unit) {
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "second")
        Text(text = "second")
        Text(text = "second")
        Text(
            text = IssueBodyBuilder.build(
                "",
                "user body",
                "image url",
                "file url",
                applicationInfo,
            )
        )
        Button(
            onClick = {
                navController.navigate("first")
            }
        ) {
            Text(text = "go to first")
        }
    }
}
