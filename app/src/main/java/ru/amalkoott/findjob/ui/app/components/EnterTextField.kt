package ru.amalkoott.findjob.ui.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EnterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color,
    containerColor: Color,
    placeholder: String = "",
    placeholderColor: Color,
){
    Box(
        modifier = modifier
            .background(containerColor, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ){
        BasicTextField(
            value = value,
            onValueChange =  onValueChange ,
            textStyle = TextStyle(
                color = textColor,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                lineHeight = MaterialTheme.typography.titleMedium.fontSize*2
            ),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            maxLines = 1,
        )

    }
}