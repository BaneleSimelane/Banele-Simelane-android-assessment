package com.glucode.about_you.about.presentation

import com.glucode.about_you.core.presentation.designsystem.components.ui.UiText

sealed class AboutEvent {

    object ImageChangedSuccessful: AboutEvent()
    object NavigateBack: AboutEvent()
    data class Error(val error: UiText): AboutEvent()
}