package com.example.summaytask12.util

import com.example.summaytask12.model.EmployeeStatus
import com.example.summaytask12.extension.findEmployeeByName
import com.example.summaytask12.extension.findEmployeeByStatus
import com.example.summaytask12.extension.formatCurrency
import com.example.summaytask12.extension.getDisplayInfor

class EmployeeManager {
    val employees = EmployeeDataSeeder().getSampleEmployee().toMutableList()
    val reportService = EmployeeReportService()
    val searchService = EmployeeSearchService()
    val managementService = EmployeeManagementService()

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
                7 -> findEmployeeByRangeSalary()
                8 -> countEmployeeByPositionMenu()
                9 -> managementService.addEmployeeMenu(employees)
                10 -> managementService.deleteEmployeeMenu(employees)
                11 -> managementService.updateEmployeeStatusMenu(employees)
                12 -> {
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
        println("3. Thống kê số lượng (TTS Bách Khoa, Chuyên viên)")
        println("4. Tìm kiếm nhân viên theo tên (Chính xác)")
        println("5. Tìm kiếm nhân viên theo Tên và Trạng thái")
        println("6. Lọc nhân viên sinh trước năm: ")
        println("7. Lọc nhân viên theo khoảng lương: ")
        println("8. Thống kê số lượng theo chức vụ")
        println("9. Thêm nhân viên mới")
        println("10. Xóa nhân viên hiện có")
        println("11. Cập nhật traạng thái cho nhân viên")
        println("12. Thoát chương trình")
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
        } else {

            val filteredList = searchService.filterByBirthYear(employees, maxYear)

            println("\n Nh---ân viên sinh trước năm $maxYear ---")
            if (filteredList.isNotEmpty()) {
                filteredList.forEach { employee ->
                    println("- ${employee.fullName} (Sinh năm: ${employee.birthYear})")
                }
            } else {
                println("Không tìm thấy nhân viên nào sinh trước năm $maxYear.")
            }
        }
    }

    private fun countEmployeeByPositionMenu() {
        println("\n--- Thống kê nhân viên theo chức vụ ---")
        val countMap = searchService.countEmployeesByPosition(employees)

        if (countMap.isNotEmpty()) {
            countMap.forEach { (position, count) ->
                println("- ${position.displayName}: $count người")
            }
        } else {
            println("Không có dữ liệu nhân viên.")
        }
    }

    private fun findEmployeeByRangeSalary() {
        println("\n--- Thống kê nhân viên theo khoảng lương ---")
        print("Nhập mức lương nhỏ nhất: ")
        val minSalary = readLine()?.toDoubleOrNull() ?: 0.0

        print("Nhập mức lương lớn nhất: ")
        val maxSalary = readLine()?.toDoubleOrNull() ?: 0.0

        if (maxSalary == null || maxSalary <= minSalary) {
            println("Mức lương tối đa không hợp lệ!")
            return
        } else {
            val findList = searchService.filterBySalaryRange(employees, minSalary, maxSalary)

            println("\n---Danh sách NV lương từ ${minSalary.formatCurrency()} dến ${maxSalary.formatCurrency()} VNĐ---")
            if (findList.isNotEmpty()) {
                findList.forEach { employee ->
                    println(
                        "- ${employee.fullName} | Luương: ${
                            employee.calculateSalary().formatCurrency()
                        } VNĐ"
                    )
                }
            } else {
                println("Không tìm thấy nhân viên nào phù hợp")
            }
        }
    }
}