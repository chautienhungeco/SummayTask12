package com.example.summaytask12.extension

import android.content.ContentValues.TAG
import android.util.Log
import com.example.summaytask12.model.Employee
import com.example.summaytask12.model.EmployeeStatus
import com.example.summaytask12.model.FullTimeEmployee
import com.example.summaytask12.model.Intern
import com.example.summaytask12.model.Position

fun Employee.getDisplayInfor(): String {
    return when (this) {
        is FullTimeEmployee -> {
            "Mã nhân viên: ${this.employeeId}\n" +
                    "Họ tên: ${this.fullName}\n" +
                    "Chức vụ: ${this.position}\n" +
                    "Lương: ${this.calculateSalary().formatCurrency()} VNĐ"
        }

        is Intern -> {
            "Mã học sinh: ${this.employeeId}\n" +
                    "Họ tên: ${this.fullName}\n" +
                    "Trờng đào tạo: ${this.university}" +
                    "Lương: ${this.calculateSalary().formatCurrency()} VNĐ"
        }

        else -> {
            "Mã nHân viên: ${this.employeeId}\n" +
                    "Họ tên: ${this.fullName}\n" +
                    "Lương: ${this.calculateSalary().formatCurrency()} VNĐ"
        }
    }
}

fun List<Employee>.findHighestPaidEmployee(): Employee? {
    return this.maxByOrNull { it.calculateSalary() }
}

fun List<Employee>.getSicenceInternsCount(): Int {
    return this.filterIsInstance<Intern>()
        .count { it.university.lowercase().contains("đại học bách khoa") }
}

fun List<Employee>.getExpertCount(): Int {
    return this.filterIsInstance<FullTimeEmployee>()
        .count { it.position == Position.EXPERT }
}

fun List<Employee>.findEmployeeByName(keyword: String?): Employee? {
    return keyword?.let {
        val lowerKeyword = it.lowercase().trim()

        this.find { employee ->
            employee.fullName.trim()?.lowercase()?.contains(lowerKeyword) ?: false
        }
    }
}

fun List<Employee>.findEmployeeByStatus(
    name: String? = null,
    status: EmployeeStatus? = null
): Employee? {
    return this.find { employee ->
        val checkName = name?.let {
            employee.fullName.trim()?.lowercase()?.contains(it.trim().lowercase())
        } ?: true

        val checkStatus = status.let {
            employee.employeeStatus == it
        }

        checkName == true && checkStatus == true
    }
}
