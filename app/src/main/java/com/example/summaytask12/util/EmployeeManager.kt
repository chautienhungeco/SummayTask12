package com.example.summaytask12.util

import com.example.summaytask12.model.EmployeeStatus
import com.example.summaytask12.extension.findEmployeeByName
import com.example.summaytask12.extension.findEmployeeByStatus
import com.example.summaytask12.extension.getDisplayInfor

class EmployeeManager {
    val employees = EmployeeDataSeeder().getSampleEmployee()
    val reportService = EmployeeReportService()
    val searchService = EmployeeSearchService()


    fun runEmployeeManagementSystem() {
        var isRunning = true
        while (isRunning) {
            displayMenu()
            when (val choice = readLine()?.toIntOrNull()) {
                1 -> println(reportService.generateEmployeeListReport(employees))
                2 -> println(reportService.generateHighestPaidEmployeeReport(employees))
                3 -> println(reportService.generateStatisticsReport(employees))
                4 -> searchEmployeeByNameMenu()
                5 -> searchEmployeeByStatusMenu()
                6 -> filterEmployeeByBirthYearMenu()
                7 -> countEmployeeByPositionMenu()
                8 -> {
                    println("\nĐang thoát chương trình. bye bye")
                    isRunning = false
                }

                else -> println("\nLựa chọn không hợp lệ. Vui lòng chọn lại.")
            }
        }
    }

    private fun displayMenu() {
        println("---CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN---")
        println("1. Hiển thị danh sách nhân viên")
        println("2. Tìm nhân viên lương cao nhất")
        println("3. Thống kê (TTS Bách Khoa, Chuyên viên)")
        println("4. Tìm kiếm nhân viên theo tên (Chính xác)")
        println("5. Tìm kiếm nhân viên theo Tên và Trạng thái")
        println("6. Lọc nhân viên sinh trước năm: ")
        println("7. Thống kê số lượng theo chức vụ")
        println("8. Thoát chương trình")
        print("Nhập lựa chọn của bạn: ")
    }

    private fun searchEmployeeByNameMenu() {
        print("Nhập tên (hoặc một phần tên) cần tìm: ")
        val keyword = readLine()
        val foundEmployee = employees.findEmployeeByName(keyword)
        if (foundEmployee != null) {
            println("\n---Kết quả tìm kiếm (theo tên)---")
            println(foundEmployee.getDisplayInfor())
        } else {
            println("Không tìm thấy nhân viên nào có tên chứa '$keyword'.")
        }
    }

    private fun searchEmployeeByStatusMenu() {
        print("Nhập tên (hoặc một phần tên) cần tìm: ")
        val nameKeyword = readLine()

        println("\nChọn trạng thái nhân viên:")
        println("1. Active (Đang hoạt động)")
        println("2. Onleave (Đang nghỉ phép)")
        println("3. Retired (Đã nghỉ việc)")
        print("Nhập số trạng thái: ")

        val statusChoice = readLine()?.toIntOrNull()
        val status = when (statusChoice) {
            1 -> EmployeeStatus.Active
            2 -> EmployeeStatus.Onleave("Lý do không xác định")
            3 -> EmployeeStatus.Retired("Ngày không xác định", "Lý do không xác định")
            else -> {
                println("Lựa chọn trạng thái không hợp lệ. Trở về menu chính.")
                return
            }
        }

        val foundEmployee = employees.findEmployeeByStatus(nameKeyword, status)
        if (foundEmployee != null) {
            println("\n=== Kết quả tìm kiếm (Tên và Trạng thái) ===")
            println("Nhân viên tìm thấy:")
            println(foundEmployee.getDisplayInfor())
        } else {
            println("Không tìm thấy nhân viên nào phù hợp với yêu cầu tìm kiếm.")
        }
    }

    private fun filterEmployeeByBirthYearMenu() {
        print("Nhập năm sinh tối đa (vd: 2000): ")
        val maxYear = readLine()?.toIntOrNull()

        if (maxYear == null) {
            println("Năm nhập vào không hợp lệ.")
            return
        }

        val filteredList = searchService.filterByBirthYear(employees, maxYear)

        println("\n=== Nhân viên sinh trước năm $maxYear ===")
        if (filteredList.isNotEmpty()) {
            filteredList.forEach { employee ->
                println("- ${employee.fullName} (Sinh năm: ${employee.birthYear})")
            }
        } else {
            println("Không tìm thấy nhân viên nào sinh trước năm $maxYear.")
        }
    }

    private fun countEmployeeByPositionMenu() {
        println("\n=== Thống kê nhân viên theo chức vụ ===")
        val countMap = searchService.countEmployeesByPosition(employees)

        if (countMap.isNotEmpty()) {
            countMap.forEach { (position, count) ->
                println("- ${position.displayName}: $count người")
            }
        } else {
            println("Không có dữ liệu nhân viên.")
        }
    }
}