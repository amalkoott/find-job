package ru.amalkoott.findjob.ui.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.amalkoott.findjob.R

@Composable
fun TopBar(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: Int?,
    modifier: Modifier = Modifier,
    textColor: Color,
    contentColor: Color,
    toolColor: Color,
    backgroundColor: Color,
    placeholder: String = "",
    placeholderColor: Color,
    padding: Dp,
    navigateAction: () -> Unit,
    extraAction: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(horizontal = 16.dp)){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxHeight()
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .padding(horizontal = padding + 4.dp, vertical = padding)
                .weight(1f)) {

            if(leadingIcon != null){
                Icon(
                    painter = painterResource(leadingIcon),"",
                    Modifier.size(24.dp).clickable { navigateAction() },//.padding(horizontal = (padding.value).dp),
                    tint = contentColor)
            }

            val textModifier = Modifier
                .padding(start = if (leadingIcon != null) 4.dp else 0.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)

            Box{
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(
                        color = textColor
                    ),
                    modifier = textModifier.fillMaxWidth(),
                    maxLines = 1,)
                if (value === "") {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = textModifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Box(modifier = Modifier
            .size(width = 40.dp, height = 40.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center){
            Icon(
                painter = painterResource(R.drawable.ic_launcher_filter_foreground),
                contentDescription = "filters",
                modifier = Modifier.size(24.dp),
                tint = toolColor)
        }
    }
}