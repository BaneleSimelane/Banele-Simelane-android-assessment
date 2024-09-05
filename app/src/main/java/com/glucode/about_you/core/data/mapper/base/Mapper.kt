package com.glucode.about_you.core.data.mapper.base

abstract class Mapper<U, M> : BaseMapper<U, M> {

    override fun map(unmappedList: List<U>): List<M> = unmappedList.map { map(it) }
}

interface BaseMapper<U, M> {

    fun map(unmappedList: List<U>): List<M> = unmappedList.map { map(it) }

    fun map(unmapped: U): M
}