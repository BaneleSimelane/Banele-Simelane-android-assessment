package com.glucode.about_you.about.data.mapper

import com.glucode.about_you.about.domain.model.AnswersData
import com.glucode.about_you.core.data.mapper.base.Mapper
import com.glucode.about_you.engineers.legacy.models.Answer
import javax.inject.Inject

class AnswerToAnswerData @Inject constructor()
    :Mapper<Answer, AnswersData>() {
    override fun map(unmapped: Answer): AnswersData = with(unmapped) {
        AnswersData(
            text = text?: "",
            index = index
        )
    }
}