package com.glucode.about_you.engineers.domain.usecase

import com.glucode.about_you.engineers.data.repository.FakeEngineerRepository
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.model.QuickStatsData
import com.glucode.about_you.engineers.domain.util.EngineerOrder
import com.glucode.about_you.engineers.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetEngineersTest {

    private lateinit var getEngineers: GetEngineers
    private lateinit var fakeEngineerRepository: FakeEngineerRepository

    private var fakeEngineers: MutableList<EngineerData> = mutableListOf()

    @Before
    fun setUp() {
        fakeEngineerRepository = FakeEngineerRepository()
        getEngineers = GetEngineers(fakeEngineerRepository)

         fakeEngineers = listOf(
             EngineerData(
                 name = "Banele", role = "Android dev", defaultImageName = "",
                 quickStats = QuickStatsData(years = 2, bugs = 23, coffees = 32)
             ),
             EngineerData(
                 name = "Goodenough", role = "QA", defaultImageName = "",
                 quickStats = QuickStatsData(years = 6, bugs = 43, coffees = 42)
             ),
             EngineerData(
                 name = "Simel", role = "Android dev", defaultImageName = "",
                 quickStats = QuickStatsData(years = 5, bugs = 33, coffees = 22)
             ),
             EngineerData(
                 name = "Goode", role = "QA", defaultImageName = "",
                 quickStats = QuickStatsData(years = 4, bugs = 433, coffees = 13)
             )
         ).toMutableList()
    }

    @Test
    fun `Order engineers by years descending, correct order`() = runBlocking {
        val engineers = getEngineers.invoke(EngineerOrder.Years(OrderType.Descending)).first()

        for (i in 0..engineers.size - 2) {
            assertThat(engineers[i].quickStats.years).isLessThan(engineers[i+1].quickStats.years)
        }
    }

    @Test
    fun `Order engineers by coffees descending, correct order`() = runBlocking {
        val engineers = getEngineers.invoke(EngineerOrder.Coffee(OrderType.Descending)).first()

        for (i in 0..engineers.size - 2) {
            assertThat(engineers[i].quickStats.coffees).isLessThan(engineers[i+1].quickStats.coffees)
        }
    }

    @Test
    fun `Order engineers by bugs descending, correct order`() = runBlocking {
        val engineers = getEngineers.invoke(EngineerOrder.Bugs(OrderType.Descending)).first()

        for (i in 0..engineers.size - 2) {
            assertThat(engineers[i].quickStats.bugs).isLessThan(engineers[i+1].quickStats.bugs)
        }
    }
}