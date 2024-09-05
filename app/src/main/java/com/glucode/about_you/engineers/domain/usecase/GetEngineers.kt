package com.glucode.about_you.engineers.domain.usecase

import com.glucode.about_you.engineers.domain.repository.EngineerRepository
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.util.EngineerOrder
import com.glucode.about_you.engineers.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetEngineers(
    private val repository: EngineerRepository
) {

    suspend operator fun invoke(
        engineerOrder: EngineerOrder = EngineerOrder.Years(OrderType.Descending)
    ): Flow<List<EngineerData>> {
        return repository.getEngineers().map { engineers ->
            when(engineerOrder.orderType) {
                is OrderType.Descending -> {
                    when(engineerOrder) {
                        is EngineerOrder.Bugs -> engineers.sortedBy { it.quickStats.bugs }
                        is EngineerOrder.Coffee -> engineers.sortedBy { it.quickStats.coffees }
                        is EngineerOrder.Years -> engineers.sortedBy { it.quickStats.years }
                    }
                }

                is OrderType.Ascending  -> {
                    when(engineerOrder) {
                        is EngineerOrder.Bugs -> engineers.sortedBy { it.quickStats.bugs }
                        is EngineerOrder.Coffee -> engineers.sortedBy { it.quickStats.coffees }
                        is EngineerOrder.Years -> engineers.sortedBy { it.quickStats.years }
                    }
                }
            }
        }
    }
}