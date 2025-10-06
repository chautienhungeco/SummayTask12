package com.example.summaytask12.presentation

import com.example.summaytask12.domain.usecase.EmployeeSearchService
import com.example.summaytask12.domain.model.EmployeeStatus
import com.example.summaytask12.extension.findEmployeeByName
import com.example.summaytask12.extension.findEmployeeByStatus
import com.example.summaytask12.extension.formatCurrency
import com.example.summaytask12.extension.getDisplayInfor

class EmployeeSearchScreen(private val searchService: EmployeeSearchService) {

    private fun getEmployees() = searchService.repository.getAllEmployees()

    fun searchEmployeeByNameMenu() {
        println("\n--- Tìm Kiếm Nhân Viên theo Tên ---")
        print("Nhập tên (hoặc một phần tên): ")
        val keyword = readlnOrNull()

        val employee = getEmployees().findEmployeeByName(keyword)

        employee?.let {
            println("\n--- Kết quả tìm kiếm ---")
            println(it.getDisplayInfor())
        } ?: println("Không tìm thấy nhân viên nào với tên '$keyword'.")
    }

    fun searchEmployeeByStatusMenu() {
        println("\n--- Tìm Kiếm Nhân Viên theo Trạng Thái ---")
        print("Nhập tên (hoặc để trống): ")
        val name = readlnOrNull()

        println("Chọn Trạng Thái (1: Active, 2: Onleave, 3: Retired, [Bỏ qua]): ")

        val status = when (readlnOrNull()?.toIntOrNull()) {
            1 -> EmployeeStatus.Active
            2 -> EmployeeStatus.Onleave("")
            3 -> EmployeeStatus.Retired("", "")
            else -> null
        }

        val employee = getEmployees().findEmployeeByStatus(name, status)

        employee?.let {
            println("\n--- Kết quả tìm kiếm ---")
            println(it.getDisplayInfor())
        } ?: println("Không tìm thấy nhân viên nào phù hợp.")
    }

    fun filterEmployeeByBirthYearMenu() {
        println("\n--- Lọc Nhân Viên theo Năm Sinh tối đa ---")
        print("Nhập năm sinh tối đa: ")
        val maxYear = readlnOrNull()?.toIntOrNull()

        if (maxYear == null || maxYear <= 0) {
            println("Năm sinh không hợp lệ.")
            return
        }

        val findList = searchService.filterByBirthYear(maxYear)

        println("\n--- Danh sách NV sinh trước hoặc năm $maxYear (${findList.size} người) ---")
        if (findList.isNotEmpty()) {
            findList.forEach { employee ->
                println("- ${employee.fullName} | Năm sinh: ${employee.birthYear}")
            }
        } else {
            println("Không tìm thấy nhân viên nào phù hợp.")
        }
    }

    fun findEmployeeByRangeSalary() {
        println("\n--- Thống kê nhân viên theo khoảng lương ---\n")
        print("Nhập mức lương nhỏ nhất: ")
        val minSalary = readlnOrNull()?.toDoubleOrNull() ?: 0.0

        print("Nhập mức lương lớn nhất: ")
        val maxSalary = readlnOrNull()?.toDoubleOrNull() ?: 0.0

        if (maxSalary <= minSalary) {
            println("Mức lương tối đa phải lớn hơn mức lương tối thiểu.")
            return
        }

        val findList = searchService.filterBySalaryRange(minSalary, maxSalary)

        println("\n--- Danh sách NV lương từ ${minSalary.formatCurrency()} đến ${maxSalary.formatCurrency()} VNĐ (${findList.size} người) ---")
        if (findList.isNotEmpty()) {
            findList.forEach { employee ->
                println(
                    "- ${employee.fullName} | Lương: ${
                        employee.calculateSalary().formatCurrency()
                    } VNĐ"
                )
            }
        } else {
            println("Không tìm thấy nhân viên nào phù hợp.")
        }
    }

    fun countEmployeeByPositionMenu() {
        println("\n--- Thống kê Số lượng Nhân Viên theo Chức vụ ---")
        val countMap = searchService.countEmployeesByPosition()

        if (countMap.isNotEmpty()) {
            countMap.forEach { (position, count) ->
                println("- ${position.displayName}: $count người")
            }
        } else {
            println("Không có dữ liệu nhân viên.")
        }
    }
}