package com.glucode.about_you.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FactsItem(
    modifier: Modifier = Modifier,
    questionText: String,
    answersOptions: List<String>,
    correctAnswer: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = questionText,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            answersOptions.forEach { option ->
                val isCorrectAnswer = option.equals(correctAnswer, ignoreCase = true)
                val optionBackgroundColor = if (isCorrectAnswer) Color.White else Color.Black

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = optionBackgroundColor,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option,
                        color = if (isCorrectAnswer) Color.Black else Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FactsItemPreview() {
    Theme {
        FactsItem(
            questionText = "When do you have the most energy?",
            answersOptions = listOf(
                "6am",
                "12pm",
                "6pm",
                "Midnight"
            ),
            correctAnswer = "midnight"
        )
    }
}


