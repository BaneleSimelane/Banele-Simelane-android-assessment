package com.glucode.about_you.about.presentation.composable

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.glucode.about_you.R
import com.glucode.about_you.about.presentation.AboutAction
import com.glucode.about_you.about.presentation.AboutEvent
import com.glucode.about_you.about.presentation.AboutState
import com.glucode.about_you.about.presentation.AboutViewModel
import com.glucode.about_you.core.presentation.designsystem.components.AboutYouScaffold
import com.glucode.about_you.core.presentation.designsystem.components.FactsItem
import com.glucode.about_you.core.presentation.designsystem.components.ProfileCard
import com.glucode.about_you.core.presentation.designsystem.components.Toolbar
import com.glucode.about_you.core.presentation.designsystem.components.ui.ObserveAsEvents
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.model.QuickStatsData

@Composable
fun AboutScreenRoot(
    onNavigateBack: () -> Unit,
    viewModel: AboutViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.event) { event ->
        when (event) {
            is AboutEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            is AboutEvent.NavigateBack -> onNavigateBack()

            is AboutEvent.ImageChangedSuccessful -> Toast.makeText(
                context,
                "Imaged changed successfully",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    AboutScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                AboutAction.OnBackClick -> onNavigateBack()
                is AboutAction.SaveImage -> Toast.makeText(
                    context,
                    "Imaged changed successfully",
                    Toast.LENGTH_LONG).show()
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    state: AboutState,
    onAction: (AboutAction) -> Unit
) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )

    AboutYouScaffold(
        topAppBar = {
            Toolbar(
                showBackButton = true,
                title = stringResource(id = R.string.about_screen_title),
                onBackClick = {
                    onAction(AboutAction.OnBackClick)
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                item {
                    state.about?.let {
                        EngineerData(
                            name = it.name,
                            role = state.about.role,
                            defaultImageName = state.about.defaultImageName,
                            quickStats = QuickStatsData(
                                years = state.about.quickStats.years,
                                coffees = state.about.quickStats.coffees,
                                bugs = state.about.quickStats.bugs
                            )
                        )
                    }?.let {
                        ProfileCard(
                            isSimple = false,
                            imageSize = 100.dp,
                            cardHeight = 180.dp,
                            onImageClick = {
                                   photoPickerLauncher.launch(
                                       PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                   )
                                onAction(AboutAction.SaveImage(
                                    name = it.name,
                                    imageName = selectedImageUri.toString()
                                ))
                            },
                            engineerData = if (selectedImageUri != null) {
                                EngineerData(
                                    name = it.name,
                                    role = it.role,
                                    defaultImageName = selectedImageUri.toString(),
                                    quickStats = it.quickStats
                                )
                            } else it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )
                    }
                }

                state.about?.let { aboutData ->
                    items(aboutData.questions) { about ->
                        about.answer.text?.let {
                            FactsItem(
                                questionText = about.questionText,
                                answersOptions = about.answerOptions,
                                correctAnswer = it,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}