package com.glucode.about_you.core.presentation.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.glucode.about_you.R
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.model.QuickStatsData

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileCard(
    isSimple: Boolean,
    imageSize: Dp,
    cardHeight: Dp,
    onImageClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    engineerData: EngineerData,
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(cardHeight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = if (engineerData.defaultImageName.isNotEmpty()) {
                    rememberAsyncImagePainter(engineerData.defaultImageName)
                } else {
                    painterResource(id = R.drawable.ic_person)
                },
                contentDescription = engineerData.name,
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.CenterVertically)
                    .clickable { 
                        onImageClick()
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = engineerData.name,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = engineerData.role,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (!isSimple) {
                    QuickStats(
                        quickStats = engineerData.quickStats,
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickStats(
    quickStats: QuickStatsData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.LightGray)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuickStatsItem(
                title = "Years",
                value = quickStats.years.toString()
            )
            QuickStatsItem(
                title = "Coffees",
                value = quickStats.coffees.toString()
            )
            QuickStatsItem(
                title = "Bugs",
                value = quickStats.bugs.toString()
            )
        }
    }
}

@Composable
private fun QuickStatsItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.Black
        )
        Text(
            text = value,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun ProfileCardPreview() {
    Theme {
        Background(
            modifier = Modifier.fillMaxSize()
        ) {
            ProfileCard(
                isSimple = false,
                imageSize = 100.dp,
                cardHeight = 180.dp,
                engineerData = EngineerData(
                    name = "Banele Simelane",
                    role = "Android Developer",
                    defaultImageName = "",
                    quickStats = QuickStatsData(
                        years = 3,
                        coffees = 29,
                        bugs = 50
                    )
                )
            )
        }
    }
}