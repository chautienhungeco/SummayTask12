package com.example.summaytask12.extension

fun Double.formatCurrency(): String {
    return String.format("%.0f", this)
}

fun Int.formatCureentcy(): String {
    return String.format("%d", this)
}