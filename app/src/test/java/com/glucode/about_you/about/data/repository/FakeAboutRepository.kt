package com.glucode.about_you.about.data.repository

import com.glucode.about_you.about.domain.model.AboutData
import com.glucode.about_you.about.domain.repository.AboutRepository

class FakeAboutRepository: AboutRepository {

    private val aboutData: AboutData? = null
    override suspend fun getEngineerByName(name: String): AboutData? {
        return aboutData
    }

    override suspend fun updateImage(name: String, imageName: String) {
        TODO("Not yet implemented")
    }
}