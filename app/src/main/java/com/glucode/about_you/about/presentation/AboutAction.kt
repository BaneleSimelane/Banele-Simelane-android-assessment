package com.glucode.about_you.about.presentation

sealed class AboutAction {

    object OnBackClick: AboutAction()
    data class SaveImage(
        val name: String,
        val imageName: String
    ): AboutAction()
}