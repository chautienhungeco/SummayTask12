package com.example.summaytask12.domain.usecase

import com.example.summaytask12.domain.model.*

class EmployeeCreationService {

    fun createEmployeeStatus(choice: Int, readLine: () -> String?): EmployeeStatus {
        return when (choice) {
            1 -> EmployeeStatus.Active
            2 -> {
                print("Nhập lý do nghỉ phép: ")
                val reason = readLine() ?: ""
                EmployeeStatus.Onleave(reason)
            }

            3 -> {
                print("Nhập ngày nghỉ hưu: ")
                val retireDate = readLine() ?: ""
                print("Nhập lý do nghỉ hưu: ")
                val reason = readLine() ?: ""
                EmployeeStatus.Retired(retireDate, reason)
            }

            else -> EmployeeStatus.Active
        }
    }

    fun createFullTimeEmployee(
        id: String,
        fullName: String,
        birthYear: Int,
        salaryCoefficient: Double,
        contact: ContactInformation,
        status: EmployeeStatus,
        positionChoice: Int,
        positionAllowance: Double,
        overtimeDays: Int
    ): FullTimeEmployee {
        val position = when (positionChoice) {
            1 -> Position.DEPARTMENT_MANAGER
            2 -> Position.EXPERT
            else -> Position.EXPERT
        }

        return FullTimeEmployee(
            id, fullName, birthYear, salaryCoefficient, contact, status,
            position, positionAllowance, overtimeDays
        )
    }

    fun createInternEmployee(
        id: String,
        fullName: String,
        birthYear: Int,
        salaryCoefficient: Double,
        contact: ContactInformation,
        status: EmployeeStatus,
        major: String,
        university: String,
        projectsCompleted: Int
    ): Intern {
        return Intern(
            id, fullName, birthYear, salaryCoefficient, contact, status,
            major, university, projectsCompleted
        )
    }
}