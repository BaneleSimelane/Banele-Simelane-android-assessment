package com.glucode.about_you.about.data.mapper

import com.glucode.about_you.about.domain.model.QuestionData
import com.glucode.about_you.core.data.mapper.base.Mapper
import com.glucode.about_you.engineers.legacy.models.Question
import javax.inject.Inject

class QuestionToQuestionData @Inject constructor(
    private val answerData: AnswerToAnswerData
)
    : Mapper<Question, QuestionData> (){
    override fun map(unmapped: Question): QuestionData = with(unmapped) {
        QuestionData(
            questionText = questionText,
            answerOptions = answerOptions,
            answer = answerData.map(answer)
        )
    }
}