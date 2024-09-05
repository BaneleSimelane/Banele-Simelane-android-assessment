package com.glucode.about_you.engineers.legacy.models

data class Engineer(
    val name: String,
    val role: String,
    val defaultImageName: String,
    val quickStats: QuickStats,
    val questions: List<Question>,
)