package com.example.summaytask12.presentation

import com.example.summaytask12.domain.usecase.EmployeeSearchService
import com.example.summaytask12.domain.model.EmployeeStatus
import com.example.summaytask12.extension.findEmployeeByName
import com.example.summaytask12.extension.findEmployeeByStatus
import com.example.summaytask12.extension.formatCurrency
import com.example.summaytask12.extension.getDisplayInfor
import com.example.summaytask12.domain.model.SalarySortCriteria
import com.example.summaytask12.domain.model.BirthYearSortCriteria

class EmployeeSearchScreen(private val searchService: EmployeeSearchService) {

    private suspend fun getEmployees() = searchService.repository.getAllEmployees()

    suspend fun searchEmployeeByNameMenu() {
        println("\n--- Tìm Kiếm Nhân Viên theo Tên ---")
        print("Nhập tên (hoặc một phần tên): ")
        val keyword = readlnOrNull()

        val employee = getEmployees().findEmployeeByName(keyword)

        employee?.let {
            println("\n--- Kết quả tìm kiếm ---")
            println(it.getDisplayInfor())
        } ?: println("Không tìm thấy nhân viên nào với tên '$keyword'.")
    }

    suspend fun searchEmployeeByStatusMenu() {
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

    suspend fun filterEmployeeByBirthYearMenu() {
        println("\n--- Lọc Nhân Viên theo Năm Sinh tối đa ---")
        print("Nhập năm sinh tối đa: ")
        val maxYear = readlnOrNull()?.toIntOrNull()

        if (maxYear == null || maxYear <= 1700) {
            println("Năm sinh không hợp lệ.")
            return
        }

        val findList = searchService.filerEmployee { employee ->
            employee.birthYear <= maxYear
        }

        println("\n--- Danh sách NV sinh trước hoặc năm $maxYear (${findList.size} người) ---")
        if (findList.isNotEmpty()) {
            findList.forEach { employee ->
                println("- ${employee.fullName} | Năm sinh: ${employee.birthYear}")
            }
        } else {
            println("Không tìm thấy nhân viên nào phù hợp.")
        }
    }

    suspend fun findEmployeeByRangeSalary() {
        println("\n--- Thống kê nhân viên theo khoảng lương ---\n")
        print("Nhập mức lương nhỏ nhất: ")
        val minSalary = readlnOrNull()?.toDoubleOrNull() ?: 0.0

        print("Nhập mức lương lớn nhất: ")
        val maxSalary = readlnOrNull()?.toDoubleOrNull() ?: 0.0

        if (maxSalary <= minSalary) {
            println("Mức lương tối đa phải lớn hơn mức lương tối thiểu.")
            return
        }

        val findList = searchService.filerEmployee { employee ->
            val salary = employee.calculateSalary()
            salary >= minSalary && salary <= maxSalary
        }

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

    suspend fun countEmployeeByPositionMenu() {
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

    suspend fun sortEmployeeMenu(){
        println("\n---Sắp xếp danh sách nhaan viên---")
        println("Chọn tiêu chí sắp xếp: 1 - Lương, 2 - Năm sinh ")
        print("Nhập lựa chọn: ")
        val criteriaChoice = readlnOrNull()?.toIntOrNull()
        val criteria = when(criteriaChoice){
            1 -> SalarySortCriteria()
            2 -> BirthYearSortCriteria()
            else -> {
                println("Lựa chọn không hợp lệ, mặc định sẽ sếp theo năm sinh!")
                BirthYearSortCriteria()
            }
        }

        println("Chọn thứ tự sắp xếp: 1 - Tăng dần, 2 - Giảm dần")
        print("Nhập lựa chọn: ")
        val orderChoice = readlnOrNull()?.toIntOrNull()
        val isDescending = orderChoice == 2 //sx giam dan true (2)

        val sortedList = searchService.sortEmployees(criteria, isDescending)

        val criteriaName = if (criteria is SalarySortCriteria){
            "Lương"
        }else{
            "Năm Sinh"
        }
        val orderName = if (isDescending){
            "Giảm dần"
        }else{
            "Tăng dần"
        }

        println("\n Danh sách sắp xếp $criteriaName ($orderName)")

        if (sortedList.isNotEmpty()){
            sortedList.forEachIndexed{index, employee ->
                println("${index + 1}. ${employee.getDisplayInfor()}")
                println("----")
            }
        }else{
            println("Không có thông tin")
        }
    }
}