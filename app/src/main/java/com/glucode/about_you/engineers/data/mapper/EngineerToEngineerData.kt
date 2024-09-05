package com.glucode.about_you.engineers.data.mapper


import com.glucode.about_you.core.data.mapper.base.Mapper
import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.legacy.models.Engineer
import javax.inject.Inject

class EngineerToEngineerData @Inject constructor(
    private val quickStatsData: QuickStatsToQuickStatsData
) : Mapper<Engineer, EngineerData>() {
    override fun map(unmapped: Engineer): EngineerData =
       with(unmapped) {
           EngineerData(
               name = name,
               role = role,
               defaultImageName = defaultImageName,
               quickStats = quickStatsData.map(quickStats)
           )
       }
}