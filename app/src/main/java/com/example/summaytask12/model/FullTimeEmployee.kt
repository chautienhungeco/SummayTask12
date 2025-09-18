package com.example.summaytask12.model

class FullTimeEmployee (
    employeeId: String,
    fullName: String,
    birthYear: Int,
    salaryCoeffecient: Double,
    val position: String,
    val positionAllowance: Double,
    val overtimeDays: Int
) : Employee(employeeId, fullName,birthYear, salaryCoeffecient){
    override fun calculateSalary(): Double {
        return (salaryCoeffecient.times(1500000.0)).plus(positionAllowance).plus(overtimeDays.times(500000.0))
    }
}