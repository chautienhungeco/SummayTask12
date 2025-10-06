package com.example.summaytask12.domain.usecase

import com.example.summaytask12.data.EmployeeRepository
import com.example.summaytask12.extension.findHighestPaidEmployee
import com.example.summaytask12.extension.getDisplayInfor
import com.example.summaytask12.extension.getExpertCount
import com.example.summaytask12.extension.getSicenceInternsCount

class EmployeeReportService(private val repository: EmployeeRepository) {

    fun generateEmployeeListReport(): String {
        val employees = repository.getAllEmployees()
        val report = StringBuilder()
        report.appendLine("\n--- Danh sách ${employees.size} nhân viên ---\n")
        employees.forEach { employee ->
            report.appendLine(employee.getDisplayInfor())
            report.appendLine("----")
        }
        return report.toString()
    }

    fun generateHighestPaidEmployeeReport(): String {
        val employees = repository.getAllEmployees()
        val report = StringBuilder()
        report.appendLine("\n--- Nhân viên lương VIP nhất ---")

        val highestPaidEmployee = employees.findHighestPaidEmployee()
        highestPaidEmployee?.let { employee ->
            report.appendLine(employee.getDisplayInfor())
        } ?: report.appendLine("Không có dữ liệu nhân viên.")

        return report.toString()
    }

    fun generateStatisticsReport(): String {
        val employees = repository.getAllEmployees()
        val report = StringBuilder()
        report.appendLine("\n--- Thống kê ---\n")

        val scienceInterns = employees.getSicenceInternsCount()
        report.appendLine("- Thực tập sinh từ Bách Khoa: $scienceInterns người")

        val expertEmployee = employees.getExpertCount()
        report.appendLine("- Nhân viên chức vụ Chuyên viên: $expertEmployee người")

        return report.toString()
    }
}