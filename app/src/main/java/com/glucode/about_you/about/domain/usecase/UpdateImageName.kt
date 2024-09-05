package com.glucode.about_you.about.domain.usecase

import com.glucode.about_you.about.domain.repository.AboutRepository

class UpdateImageName(
    private val repository: AboutRepository
) {

    suspend operator fun invoke(name: String, imageName: String) {
        repository.updateImage(
            name = name,
            imageName = imageName
        )
    }
}