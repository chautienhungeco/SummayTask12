package com.example.summaytask12.domain.model

abstract class Employee(
    val employeeId: String,
    val fullName: String,
    val birthYear: Int,
    val salaryCoefficient: Double,
    val contactInformation: ContactInformation,
    var employeeStatus: EmployeeStatus
) {
    abstract fun calculateSalary(): Double
}