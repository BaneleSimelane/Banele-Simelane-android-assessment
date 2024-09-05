package com.glucode.about_you.engineers.data.repository

import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.repository.EngineerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeEngineerRepository: EngineerRepository {

    private val engineer = mutableListOf<EngineerData>()
    override suspend fun getEngineers(): Flow<List<EngineerData>> {
        return flow { emit(engineer) }
    }
}