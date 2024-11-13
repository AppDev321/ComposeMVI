package com.example.compose.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFF3B6DE9)
val PurpleGrey80 = Color(0xFF3B6DE9)
val Pink80 = Color(0xFF3B6DE9)

val Purple40 = Color(0xFF3B6DE9)
val PurpleGrey40 = Color(0xFF3B6DE9)
val Pink40 = Color(0xFF3B6DE9)





fun hexToColor(hex: String): Color {
    val sanitizedHex = if (hex.startsWith("#")) hex.substring(1) else hex
    return Color(android.graphics.Color.parseColor("#$sanitizedHex"))
}