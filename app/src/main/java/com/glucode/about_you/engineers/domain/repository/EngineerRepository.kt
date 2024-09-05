package com.glucode.about_you.engineers.domain.repository

import com.glucode.about_you.engineers.domain.model.EngineerData
import kotlinx.coroutines.flow.Flow

interface EngineerRepository {

    suspend fun getEngineers(): Flow<List<EngineerData>>
}