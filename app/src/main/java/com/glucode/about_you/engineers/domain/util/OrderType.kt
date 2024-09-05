package com.glucode.about_you.engineers.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}