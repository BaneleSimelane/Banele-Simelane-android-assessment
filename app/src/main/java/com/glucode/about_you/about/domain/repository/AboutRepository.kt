package com.glucode.about_you.about.domain.repository

import com.glucode.about_you.about.domain.model.AboutData

interface AboutRepository {
    suspend fun getEngineerByName(name: String): AboutData?
    suspend fun updateImage(name: String, imageName: String)
}