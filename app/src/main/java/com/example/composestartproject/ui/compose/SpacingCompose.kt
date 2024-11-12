package com.example.composestartproject.ui.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


object Spacing {

    // Horizontal Spacers
    val sizeHorizontalSmall: @Composable () -> Unit = {
        Spacer(modifier = Modifier.width(15.dp))
    }

    val sizeHorizontalMedium: @Composable () -> Unit = {
        Spacer(modifier = Modifier.width(20.dp))
    }

    // Vertical Spacers
    val sizeVerticalSmall: @Composable () -> Unit = {
        Spacer(modifier = Modifier.height(10.dp))
    }

    val sizeVerticalMedium: @Composable () -> Unit = {
        Spacer(modifier = Modifier.height(20.dp))
    }

    val sizeVerticalLarge: @Composable () -> Unit = {
        Spacer(modifier = Modifier.height(30.dp))
    }
}