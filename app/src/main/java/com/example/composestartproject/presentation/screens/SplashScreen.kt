package com.example.composestartproject.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.composestartproject.R
import com.example.composestartproject.presentation.reducer.SplashEffect
import com.example.composestartproject.presentation.viewmodel.SplashScreenViewModel
import com.example.composestartproject.ui.theme.hexToColor
import com.example.composestartproject.utils.rememberFlowWithLifecycle

@Composable
fun SplashScreen(splashScreenViewModel: SplashScreenViewModel) {

    val state = splashScreenViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(splashScreenViewModel.effect)
    val context = LocalContext.current


    LaunchedEffect(effect) {
        effect.collect { action ->
            when (action) {
                is SplashEffect.ShowSnackBar -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // Splash logo in the center of the screen
        Image(
            painter = painterResource(R.drawable.splash_logo),
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center)
        )



        // Text at the bottom of the screen
        Text(
            text = "Datsarap",
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.W500,
                color = hexToColor("#E8A401")
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter) // Align to the bottom center
                .padding(bottom = 20.dp)
        )
    }
}
