package com.example.composestartproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.composestartproject.presentation.screens.SplashScreen
import com.example.composestartproject.presentation.viewmodel.ApiViewModel
import com.example.composestartproject.presentation.viewmodel.SplashScreenViewModel
import com.example.composestartproject.ui.theme.ComposeStartProjectTheme
import com.example.composestartproject.ui.theme.appTypography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ApiViewModel by viewModels()
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStartProjectTheme {
                //  StartScreen(viewModel)
                AppTheme(
                    content = { SplashScreen(splashScreenViewModel) }
                )

            }
        }
    }
}


@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        typography = appTypography,
        content = content
    )
}
