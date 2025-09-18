package com.example.summaytask12.util

import com.example.summaytask12.model.Employee
import com.example.summaytask12.extension.*

object ReportGenarator {
    fun genarateEmployeeListReport(employees: List<Employee>): String{
        val report = StringBuilder()
        report.appendLine("/n===Danh sách nhân viên===\n")
        employees.forEach{
            employee -> report.appendLine(employee.getDisplayInfor())
            report.appendLine("----")
        }
        return report.toString()
    }

    fun generateHighestPaidEmployeeReport(employees: List<Employee>): String{
        val report = StringBuilder()
        report.appendLine("/n===Nhân viên lương vip nhất===")

        val highestPaidEmployee = employees.findHighestPaidEmployee()
        highestPaidEmployee?.let { employee ->
            report.appendLine(employee.getDisplayInfor())
        }
        return report.toString()
    }

    fun generateStatisticsReport(employees: List<Employee>): String{
        val report = StringBuilder()
        report.appendLine("/n===Thống kê===")

        val sicenceInterns = employees.getBachKhoaInternsCount()
        report.appendLine("Thực tập sinh từ Bách Khoa: $sicenceInterns")

        val expertEmployee = employees.getChuyenVienCount()
        report.appendLine("Danh sách chuyên viên: $expertEmployee")

        return report.toString()
    }

    fun generateFullReport(employees: List<Employee>): String {
        return genarateEmployeeListReport(employees) +
                generateHighestPaidEmployeeReport(employees) +
                generateStatisticsReport(employees)
    }
}