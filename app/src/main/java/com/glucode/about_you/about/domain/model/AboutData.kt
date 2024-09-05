package com.glucode.about_you.about.domain.model

import com.glucode.about_you.engineers.domain.model.QuickStatsData

data class AboutData(
    val name: String,
    val role: String,
    val defaultImageName: String,
    val quickStats: QuickStatsData,
    val questions: List<QuestionData>
)
