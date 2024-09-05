package com.glucode.about_you.about.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glucode.about_you.R
import com.glucode.about_you.about.domain.usecase.GetEngineer
import com.glucode.about_you.about.domain.usecase.UpdateImageName
import com.glucode.about_you.core.presentation.designsystem.components.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val getEngineer: GetEngineer,
    private val updateImageName: UpdateImageName,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(AboutState())
        private set

    private val eventChannel = Channel<AboutEvent>()
    val event = eventChannel.receiveAsFlow()

    private var currentName: String? = null

    init {
        savedStateHandle.get<String>("name")?.let { name ->
            viewModelScope.launch {
                getEngineer.invoke(name)?.also { aboutData ->
                    currentName = aboutData.name
                    state = state.copy(
                        about = aboutData
                    )
                }
            }
        }
    }

    fun onAction(action: AboutAction) {
        when(action) {
            AboutAction.OnBackClick -> {
                viewModelScope.launch {
                    eventChannel.send(AboutEvent.NavigateBack)
                }
            }
            is AboutAction.SaveImage -> updateImage(
                name = action.name,
                imageName = action.imageName
            )
        }
    }

    private fun updateImage(name: String, imageName: String) {
        viewModelScope.launch {
            try {
                updateImageName.invoke(
                    name = name,
                    imageName = imageName
                )
                eventChannel.send(AboutEvent.ImageChangedSuccessful)
                getAbout(name)
            } catch (e: Exception) {
                eventChannel.send(AboutEvent.Error(UiText.StringResource(R.string.image_error)))
            }
        }
    }

    private fun getAbout(name: String) {
        viewModelScope.launch {
            getEngineer.invoke(name)?.also { aboutData ->
                state = state.copy(
                    about = aboutData
                )
            }
        }
    }
}