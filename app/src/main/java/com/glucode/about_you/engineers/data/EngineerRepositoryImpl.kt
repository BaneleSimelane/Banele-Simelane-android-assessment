package com.glucode.about_you.engineers.data

import com.glucode.about_you.engineers.data.mapper.EngineerToEngineerData
import com.glucode.about_you.engineers.domain.repository.EngineerRepository
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.mockdata.MockData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EngineerRepositoryImpl @Inject constructor(
    private val mapper: EngineerToEngineerData
): EngineerRepository {
    override suspend fun getEngineers(): Flow<List<EngineerData>> {
        val engineerData = MockData.engineers.map {
            mapper.map(it)
        }

        return flow {
            emit(engineerData)
        }
    }
}