package com.example.summaytask12.model

abstract class Employee(
    val employeeId: String,
    val fullName: String,
    val birthYear: Int,
    val salaryCoeffecient: Double,
    val contactInformation: ContactInformation,
    var employeeStatus: EmployeeStatus
) {
    abstract fun calculateSalary(): Double
}