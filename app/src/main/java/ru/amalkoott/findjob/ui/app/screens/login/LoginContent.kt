package ru.amalkoott.findjob.ui.app.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.amalkoott.findjob.ui.app.components.EnterTextField
import ru.amalkoott.findjob.ui.app.view.LoginViewModel

@Composable
fun LoginContent(
    onWithEmailClick: () -> Unit,
    onWithPasswordClick: () -> Unit,
    viewModel: LoginViewModel
) {
    val onContinueClick:(String) -> Unit = { value -> viewModel.setEnteredValue(value); onWithEmailClick() }
    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val context = LocalContext.current
        val text = remember { mutableStateOf("your@mail.com") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, bottom = 0.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier,
                text = "Вход в личный кабинет",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                color = MaterialTheme.colorScheme.onBackground
            )
            Column(
                modifier = Modifier
                    .padding(top = 144.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Поиск работы",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            fontFamily = MaterialTheme.typography.titleMedium.fontFamily
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            EnterTextField(
                                value = text.value,
                                onValueChange = { text.value = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                                    .height(40.dp),
                                textColor = MaterialTheme.colorScheme.onSecondary,
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                placeholder = "",
                                placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth()
                            ) {
                                Button(
                                    onClick = { if (text.value !== "") onContinueClick(text.value) },
                                    colors = if(text.value !== "") ButtonColors(//todo color with state
                                        containerColor = MaterialTheme.colorScheme.secondary,
                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                                    ) else ButtonColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                        contentColor = MaterialTheme.colorScheme.onSecondary,
                                        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        disabledContentColor = MaterialTheme.colorScheme.onSecondary,
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = "Продолжить",
                                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                        fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                                        fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                                    )
                                }
                                Text(
                                    modifier = Modifier
                                        .padding(start = 24.dp)
                                        .clickable {
                                            Toast.makeText(context, "Скоро будет сделано!", Toast.LENGTH_SHORT).show()
                                            // onWithPasswordClick()
                                        }
                                    ,
                                    text = "Войти с паролем",
                                    color = MaterialTheme.colorScheme.secondary,
                                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                    fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                                    fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                                )
                            }
                        }
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 16.dp),
                                text = "Поиск сотрудников",
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                            )
                            Text(
                                modifier = Modifier,
                                text = "Размещение вакансий и доступ к базе резюме",
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                            )
                        }
                        Button(
                            onClick = { Toast.makeText(context, "Скоро будет сделано!", Toast.LENGTH_SHORT).show() },
                            colors = ButtonColors(//todo color with state
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary,
                                disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                            ),
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()) {
                            Text(
                                modifier = Modifier,
                                text = "Я ищу сотрудников",
                                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                                fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                                fontFamily = MaterialTheme.typography.displaySmall.fontFamily,
                            )
                        }
                    }
                }
            }
        }
    }
}