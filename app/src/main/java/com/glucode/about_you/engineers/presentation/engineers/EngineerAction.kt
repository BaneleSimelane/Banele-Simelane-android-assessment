package com.glucode.about_you.engineers.presentation.engineers

import com.glucode.about_you.engineers.domain.util.EngineerOrder

sealed class EngineerAction {
    object OnToggleOrderSection: EngineerAction()
    data class Order(val engineerOrder: EngineerOrder): EngineerAction()
    data class OnEngineerClick(val selectedName: String): EngineerAction()
}