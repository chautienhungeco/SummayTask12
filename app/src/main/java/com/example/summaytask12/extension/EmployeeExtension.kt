package com.example.summaytask12.extension

import com.example.summaytask12.model.Employee
import com.example.summaytask12.model.FullTimeEmployee
import com.example.summaytask12.model.Intern

fun Employee.getDisplayInfor(): String {
    return when (this){
        is FullTimeEmployee -> {
            "Mã nhân viên: ${this.employeeId}\n"+
            "Họ tên: ${this.fullName}\n"+
            "Chức vụ: ${this.position}\n"+
            "Lương: ${String.format("%.0f", this.calculateSalary())} VNĐ"
        }
        is Intern -> {
            "Mã học sinh: ${this.employeeId}\n"+
            "Họ tên: ${this.fullName}\n"+
            "Trờng đào tạo: ${this.university}"+
            "Lương: ${String.format("%.0f", this.calculateSalary())} VNĐ"
        }
        else ->{
            "Mã nHân viên: ${this.employeeId}\n"+
            "Họ tên: ${this.fullName}\n"+
            "Lương: ${String.format("%.0f", this.calculateSalary())} VNĐ"
        }
    }
}

fun Employee.getEmployeeType(): String{
    return when(this){
        is FullTimeEmployee -> "Nhân viên chính thức"
        is Intern -> "Thực tập sinh"
        else -> "Nhân viên"
    }
}

fun List<Employee>.findHighestPaidEmployee(): Employee?{
    return this.maxByOrNull { it.calculateSalary() }
}

fun List<Employee>.getBachKhoaInternsCount(): Int {
    return this.filterIsInstance<Intern>()
        .count{it.university == "Đại học Bách Khoa"}
}

fun List<Employee>.getChuyenVienCount(): Int{
    return this.filterIsInstance<FullTimeEmployee>()
        .count{it.position == "Chuyên viên"}
}