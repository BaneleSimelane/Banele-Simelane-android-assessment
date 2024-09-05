package com.glucode.about_you.about.data

import com.glucode.about_you.about.data.mapper.EngineerToAbout
import com.glucode.about_you.about.domain.model.AboutData
import com.glucode.about_you.about.domain.repository.AboutRepository
import com.glucode.about_you.mockdata.MockData
import javax.inject.Inject

class AboutRepositoryImpl @Inject constructor(
    private val mapper: EngineerToAbout
): AboutRepository {
    override suspend fun getEngineerByName(name: String): AboutData {
        val engineers = MockData.engineers
        val engineer = engineers.filter {
            it.name.equals(name, ignoreCase = true)
        }

        val about = engineer.map {
            mapper.map(it)
        }

        return about[0]
    }

    override suspend fun updateImage(name: String, imageName: String) {
       MockData.updateEngineerImageName(
           nameToUpdate = name,
           newImageName = imageName
       )
    }
}