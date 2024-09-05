package com.glucode.about_you.about.domain.usecase

import com.glucode.about_you.about.domain.model.AboutData
import com.glucode.about_you.about.domain.repository.AboutRepository

class GetEngineer(
    private val repository: AboutRepository
) {

    suspend operator fun invoke(name: String): AboutData? {
        return repository.getEngineerByName(name)
    }
}