package com.example.composestartproject.ui.compose


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWidget(
    text: String,
    onClick: (() -> Unit)? = null,
    textStyle: TextStyle = TextStyle(fontWeight = FontWeight.W600),
    color: Color? = null,
    margin: PaddingValues = PaddingValues(0.dp),
    isLoading: Boolean = false,
    buttonType: ButtonType,
    icon: @Composable (() -> Unit)? = null,
    height: Dp = 50.dp,
    width: Dp? = null,
    radius: Dp = 30.dp,
    expand: Boolean = true,
    isEnabled: Boolean = true,
) {
    val buttonColor = color ?: MaterialTheme.colorScheme.primary
    val backgroundColor = getBackgroundColor(isEnabled, buttonType, buttonColor)
    val foregroundColor = getForegroundColor(isEnabled, buttonType)

    // Adjust width when button is loading
    val currentWidth = if (isLoading) 70.dp else width ?: Dp.Unspecified

    // Define the button style
    val buttonModifier = Modifier
        .padding(margin)
        .height(height)
        //.width(currentWidth)
        .fillMaxWidth((if (expand) 1f else Dp.Unspecified) as Float)
        .let { modifier ->
            // Check if the button type is gradient
            if (buttonType == ButtonType.gradient) {
                modifier.background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF72AB66), Color(0xFF31634D))
                    ),
                    shape = RoundedCornerShape(radius)
                )
            } else {
                modifier.background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(radius)
                )
            }
        }



    // Button
    Box(modifier = buttonModifier) {
        Button(
            onClick = {
                if (isEnabled && !isLoading) {
                    onClick?.invoke()
                }
            },
            enabled = isEnabled && !isLoading,
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(radius),
            colors = buttonColors(
                containerColor = backgroundColor,
                contentColor = foregroundColor
            ),

            contentPadding = PaddingValues(0.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = getProgressIndicatorColor(buttonType, buttonColor)
                )
            } else {
                // Icon and Text when not loading
                if (icon != null) {
                    icon()
                }
                Text(
                    text = text,
                    style = textStyle,
                    color = foregroundColor
                )
            }
        }
    }
}

@Composable
fun getForegroundColor(isEnabled: Boolean, buttonType: ButtonType): Color {
    return when {
        !isEnabled -> Color.Gray
        buttonType == ButtonType.fill || buttonType == ButtonType.gradient -> Color.White
        else -> Color.Black
    }
}

@Composable
fun getBackgroundColor(isEnabled: Boolean, buttonType: ButtonType, buttonColor: Color): Color {
    return when {
        !isEnabled -> Color.Gray.copy(alpha = 0.3f)
        buttonType == ButtonType.gradient -> Color.Transparent
        buttonType == ButtonType.transparent -> Color.Transparent
        buttonType == ButtonType.white -> Color.Transparent
        buttonType == ButtonType.fill -> buttonColor
        else -> Color.White
    }
}

@Composable
fun getProgressIndicatorColor(buttonType: ButtonType, buttonColor: Color): Color {
    return if (buttonType == ButtonType.fill || buttonType == ButtonType.gradient) {
        Color.White
    } else {
        buttonColor
    }
}

enum class ButtonType {
    transparent, fill, white, gradient
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ButtonWidget(
            text = "Press me",
            onClick = { /* Handle click */ },
            buttonType = ButtonType.gradient,
            isLoading = false,
            isEnabled = true
        )
    }
}
