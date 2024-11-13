package com.example.compose.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.R
import com.example.compose.presentation.viewmodel.ApiViewModel
import com.example.compose.ui.compose.ButtonType
import com.example.compose.ui.compose.ButtonWidget
import com.example.compose.ui.compose.Spacing
import com.example.compose.ui.compose.TextWithHorizontalLine

@Composable
fun StartScreen(apiViewModel: ApiViewModel) {
 val loadingApi by apiViewModel.loadingApi.collectAsState(false)
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxSize(),

        ) {
        Image(
            painter = painterResource(R.drawable.login_splash_bg),
            modifier = Modifier.fillMaxHeight(0.8f),
            contentDescription = "",
            alignment = Alignment.TopStart
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Round top corners
                .background(Color.White) // Background color for visibility
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Upfront", fontSize = 35.sp, fontWeight = FontWeight.W600)
                Spacing.sizeVerticalSmall()
                Text(
                    "Instant Liquidity for Real Estate Commissions", fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center
                )
                Spacing.sizeVerticalSmall()
                ButtonWidget(
                    text = "Create an account",
                    onClick = {
                        apiViewModel.getListing()

                    },
                    buttonType = ButtonType.fill,
                    isLoading = loadingApi, // Pass the isLoading state here
                    isEnabled= !loadingApi
                )
                Spacing.sizeVerticalMedium()
                TextWithHorizontalLine("Or SignIn with email")



            }


        }

    }

}