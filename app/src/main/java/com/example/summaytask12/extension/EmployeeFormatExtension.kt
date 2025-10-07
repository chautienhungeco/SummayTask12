package com.example.summaytask12.extension

import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.FullTimeEmployee
import com.example.summaytask12.domain.model.Intern

fun Employee.getDisplayInfor(): String {
    return when (this) {
        is FullTimeEmployee -> {
            "Mã nhân viên: ${this.employeeId}\n" +
                    "Họ tên: ${this.fullName}\n" +
                    "Năm sinh: ${this.birthYear}\n" +
                    "Chức vụ: ${this.position}\n" +
                    "Lương: ${this.calculateSalary().formatCurrency()} VNĐ"
        }

        is Intern -> {
            "Mã thực tập sinh: ${this.employeeId}\n" +
                    "Họ tên: ${this.fullName}\n" +
                    "Năm sinh: ${this.birthYear}\n" +
                    "Trờng đào tạo: ${this.university}" +
                    "Lương: ${this.calculateSalary().formatCurrency()} VNĐ"
        }

        else -> {
            "Mã nHân viên: ${this.employeeId}\n" +
                    "Họ tên: ${this.fullName}\n" +
                    "Năm sinh: ${this.birthYear}\n" +
                    "Lương: ${this.calculateSalary().formatCurrency()} VNĐ"
        }
    }
}