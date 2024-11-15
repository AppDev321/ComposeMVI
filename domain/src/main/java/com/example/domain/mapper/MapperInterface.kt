package com.example.domain.mapper

interface MapperInterface<F, T> {
    fun mapFrom(from: F): T
}