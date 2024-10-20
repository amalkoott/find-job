package ru.amalkoott.findjob.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.amalkoott.findjob.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.W600,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_semibold)),
        fontSize = 22.sp,
    ), // title1
    headlineMedium = TextStyle(
        fontWeight =  FontWeight.W500,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_medium)),
        fontSize = 16.sp,
    ), // title3
    titleSmall = TextStyle(
        fontWeight =  FontWeight.W500,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_medium)),
        fontSize = 14.sp,
    ), // title4


    bodySmall = TextStyle(
        fontWeight =  FontWeight.W400,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_regular)),
        fontSize = 14.sp,
    ), // text
    bodyLarge = TextStyle(
        fontWeight =  FontWeight.W600,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_semibold)),
        fontSize = 20.sp,
    ), // text, 2

    displayLarge = TextStyle(
        fontWeight =  FontWeight.W600,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_medium)),
        fontSize = 16.sp,
    ), // button 1
    displaySmall = TextStyle(
        fontWeight =  FontWeight.W400,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_regular)),
        fontSize = 14.sp,
    ), // button 2

    labelMedium = TextStyle(
        fontWeight = FontWeight.W400,
        fontSize = 10.sp,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_regular))
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 7.sp,
        fontFamily = FontFamily(Font(R.font.sfprodisplay_regular))
)
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
