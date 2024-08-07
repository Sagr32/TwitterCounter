package com.sagr32.counterview.domain.repositories

interface CounterRepository {
    fun getTweetLength(text: String): Int
    fun isTweetValid(text: String): Boolean
}