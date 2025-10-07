package com.example.summaytask12.domain.model

interface SortableCriteria<T> {
    //trích xuất Double (lương) hoặc Int (năm sinh)
    fun getKey(item: T): Comparable<*>
}