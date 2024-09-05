package com.glucode.about_you.engineers.presentation.engineers

import com.glucode.about_you.engineers.domain.model.EngineerData
import com.glucode.about_you.engineers.domain.util.EngineerOrder
import com.glucode.about_you.engineers.domain.util.OrderType

data class EngineerState(
    val engineers: List<EngineerData> = emptyList(),
    val engineerOrder: EngineerOrder = EngineerOrder.Years(OrderType.Descending),
)
