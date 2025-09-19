package com.example.summaytask12.util

import com.example.summaytask12.model.Employee
import com.example.summaytask12.extension.*
import com.example.summaytask12.model.EmployeeStatus

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

        val sicenceInterns = employees.getSicenceInternsCount()
        report.appendLine("Thực tập sinh từ Bách Khoa: $sicenceInterns")

        val expertEmployee = employees.getExpertCount()
        report.appendLine("Danh sách chuyên viên: $expertEmployee")

        return report.toString()
    }

    fun generateFindEmployee(employees: List<Employee>): String{
        val report = StringBuilder()
        report.appendLine("===Danh sách nhân viên tên Hưng===")

        val employeeByName = employees.findEmployeeByName("Hưng")
        employeeByName?.let { employee ->
            report.appendLine(employee.getDisplayInfor())
        }
        return report.toString()
    }

    fun genarateAdvenceSearch(employees: List<Employee>): String{
        val report = StringBuilder()
        report.appendLine("=== Tìm kiếm theo tên và trạng thái====")

        val activeByName = employees.findEmployeeByStatus("Hưng", status = EmployeeStatus.Active)
        activeByName?.let { employee ->
            report.appendLine("Nhân viên 'Hưng' đang hoạt động")
            report.appendLine("- ${employee.fullName}")
        } ?: run {
            report.appendLine("Không tìm thấy nhân viên nào phù hợp với yêu cầu.")
        }
        return report.toString()
    }

    fun generateFullReport(employees: List<Employee>): String {
        return genarateEmployeeListReport(employees) +
                generateHighestPaidEmployeeReport(employees) +
                generateStatisticsReport(employees) +
                generateFindEmployee(employees) + genarateAdvenceSearch(employees)
    }
}