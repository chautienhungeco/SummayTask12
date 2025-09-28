package com.example.summaytask12.model

sealed class EmployeeStatus {
    object Active : EmployeeStatus()
    data class Onleave(val reason: String) : EmployeeStatus()
    data class Retired(val retireDate: String, val reason: String) : EmployeeStatus()
}