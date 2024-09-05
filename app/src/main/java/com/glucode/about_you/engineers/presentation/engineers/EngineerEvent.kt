package com.glucode.about_you.engineers.presentation.engineers

import com.glucode.about_you.core.presentation.designsystem.components.ui.UiText

sealed class EngineerEvent {
    data class Error(val error: UiText): EngineerEvent()
    data class NavigateToAbout(val selectedName: String): EngineerEvent()
}