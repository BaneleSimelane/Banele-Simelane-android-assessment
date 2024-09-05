package com.glucode.about_you.about.domain.model

data class QuestionData(
    val questionText: String,
    val answerOptions: List<String>,
    val answer: AnswersData
)
