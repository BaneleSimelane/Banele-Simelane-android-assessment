package com.glucode.about_you.engineers.domain.util

sealed class EngineerOrder(
    val orderType: OrderType
) {
    class Years(orderType: OrderType): EngineerOrder(orderType)
    class Coffee(orderType: OrderType): EngineerOrder(orderType)
    class Bugs(orderType: OrderType): EngineerOrder(orderType)

    fun copy(orderType: OrderType): EngineerOrder {
        return when(this) {
            is Years -> Years(orderType)
            is Coffee -> Coffee(orderType)
            is Bugs -> Bugs(orderType)

        }
    }
}