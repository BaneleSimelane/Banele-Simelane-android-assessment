package com.glucode.about_you.engineers.domain.model

data class EngineerData(
    val name: String,
    val role: String,
    val defaultImageName: String,
    val quickStats: QuickStatsData,
)
