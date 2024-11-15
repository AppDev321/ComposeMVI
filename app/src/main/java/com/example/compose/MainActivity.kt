package com.example.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.common.SharedPreferenceDelegate
import com.example.compose.presentation.screens.SplashScreen
import com.example.compose.presentation.viewmodel.SplashScreenViewModel
import com.example.compose.ui.theme.ComposeStartProjectTheme
import com.example.compose.ui.theme.appTypography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()


    // Example properties using the SharedPreferenceDelegate
    var userToken: String? by SharedPreferenceDelegate(this, "user_token", null)
    var isLoggedIn: Boolean? by SharedPreferenceDelegate(context = this, key = "is_logged_in", defaultValue = false)
    var userAge: Int? by SharedPreferenceDelegate(this, "user_age", 0)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Writing to SharedPreferences

        // Reading from SharedPreferences
        Log.d("SharedPreferences", "Token: $userToken")
        Log.d("SharedPreferences", "Is Logged In: $isLoggedIn")
        Log.d("SharedPreferences", "User Age: $userAge")


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
