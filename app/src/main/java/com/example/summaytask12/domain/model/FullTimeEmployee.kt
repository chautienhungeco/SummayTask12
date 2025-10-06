package com.example.summaytask12.domain.model

class FullTimeEmployee(
    employeeId: String,
    fullName: String,
    birthYear: Int,
    salaryCoeffecient: Double,
    contactInformation: ContactInformation,
    employeeStatus: EmployeeStatus,
    val position: Position,
    val positionAllowance: Double,
    val overtimeDays: Int
) : Employee(
    employeeId,
    fullName,
    birthYear,
    salaryCoeffecient,
    contactInformation,
    employeeStatus
) {
    override fun calculateSalary(): Double {
        return (salaryCoefficient.times(1500000.0)).plus(positionAllowance)
            .plus(overtimeDays.times(500000.0))
    }
}