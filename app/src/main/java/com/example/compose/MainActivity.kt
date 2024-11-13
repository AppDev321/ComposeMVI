package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.compose.presentation.screens.SplashScreen
import com.example.compose.presentation.viewmodel.ApiViewModel
import com.example.compose.presentation.viewmodel.SplashScreenViewModel
import com.example.compose.ui.theme.ComposeStartProjectTheme
import com.example.compose.ui.theme.appTypography
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
