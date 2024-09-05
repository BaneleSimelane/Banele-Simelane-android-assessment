package com.glucode.about_you.about.data.mapper

import com.glucode.about_you.about.domain.model.AboutData
import com.glucode.about_you.core.data.mapper.base.Mapper
import com.glucode.about_you.engineers.data.mapper.QuickStatsToQuickStatsData
import com.glucode.about_you.engineers.legacy.models.Engineer
import javax.inject.Inject


class EngineerToAbout @Inject constructor(
    private val quickStatsData: QuickStatsToQuickStatsData,
    private val questionData: QuestionToQuestionData
)
    : Mapper<Engineer, AboutData>() {
    override fun map(unmapped: Engineer): AboutData =
        with(unmapped) {
            AboutData(
                name = name,
                defaultImageName = defaultImageName,
                role = role,
                quickStats = quickStatsData.map(quickStats) ,
                questions = questionData.map(questions)
            )
        }

}