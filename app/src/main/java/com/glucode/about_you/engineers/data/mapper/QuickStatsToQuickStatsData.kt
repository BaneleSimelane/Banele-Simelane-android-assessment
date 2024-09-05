package com.glucode.about_you.engineers.data.mapper

import com.glucode.about_you.core.data.mapper.base.Mapper
import com.glucode.about_you.engineers.domain.model.QuickStatsData
import com.glucode.about_you.engineers.legacy.models.QuickStats
import javax.inject.Inject

class QuickStatsToQuickStatsData @Inject constructor()
    :Mapper<QuickStats, QuickStatsData> (){
    override fun map(unmapped: QuickStats): QuickStatsData =
        with(unmapped) {
            QuickStatsData(
                years = unmapped.years,
                coffees = unmapped.coffees,
                bugs = unmapped.bugs
            )
        }
}