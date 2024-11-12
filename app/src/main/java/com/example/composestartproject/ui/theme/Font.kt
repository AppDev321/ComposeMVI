package com.example.composestartproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.composestartproject.R


val appFontFamily = FontFamily(
    Font(R.font.poppins_regular,FontWeight.Normal), // Normal weight
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_medium, FontWeight.Medium)
)




private val defaultTypography = Typography()
val appTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = appFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = appFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = appFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = appFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = appFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = appFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = appFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = appFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = appFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = appFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = appFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = appFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = appFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = appFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = appFontFamily)
)