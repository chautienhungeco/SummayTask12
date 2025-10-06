package com.example.summaytask12.extension

import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.EmployeeStatus

fun List<Employee>.findEmployeeByName(keyword: String?): Employee? {
    return keyword?.let {
        val lowerKeyword = it.lowercase().trim()

        this.find { employee ->
            employee.fullName.trim().lowercase().contains(lowerKeyword)
        }
    }
}

fun List<Employee>.findEmployeeByStatus(
    name: String? = null,
    status: EmployeeStatus? = null
): Employee? {
    return this.find { employee ->
        val checkName = name?.let {
            employee.fullName.trim().lowercase().contains(it.trim().lowercase())
        } ?: true

        val checkStatus = status.let {
            employee.employeeStatus == it
        }

        checkName == true && checkStatus == true
    }
}