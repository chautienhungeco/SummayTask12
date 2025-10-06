package com.example.summaytask12.presentation

import com.example.summaytask12.data.EmployeeDataSeeder
import com.example.summaytask12.data.EmployeeRepositoryImpl
import com.example.summaytask12.domain.usecase.EmployeeCreationService
import com.example.summaytask12.domain.usecase.EmployeeDataHandler
import com.example.summaytask12.domain.usecase.EmployeeReportService
import com.example.summaytask12.domain.usecase.EmployeeSearchService

class EmployeeProgram {
    val repository = EmployeeRepositoryImpl(EmployeeDataSeeder())

    val reportService = EmployeeReportService(repository)
    val searchService = EmployeeSearchService(repository)
    val creationService = EmployeeCreationService()
    val dataHandler = EmployeeDataHandler(repository)

    val searchScreen = EmployeeSearchScreen(searchService)
    val managementScreen = EmployeeManagemenScreen(dataHandler, creationService)

    fun displayMenu() {
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

    fun run() {
        var isRunning = true
        while (isRunning) {
            displayMenu()
            when (val choice = readLine()?.toIntOrNull()) {
                1 -> println(reportService.generateEmployeeListReport())
                2 -> println(reportService.generateHighestPaidEmployeeReport())
                3 -> println(reportService.generateStatisticsReport())
                4 -> searchScreen.searchEmployeeByNameMenu()
                5 -> searchScreen.searchEmployeeByStatusMenu()
                6 -> searchScreen.filterEmployeeByBirthYearMenu()
                7 -> searchScreen.findEmployeeByRangeSalary()
                8 -> searchScreen.countEmployeeByPositionMenu()
                9 -> managementScreen.addEmployeeMenu()
                10 -> managementScreen.deleteEmployeeMenu()
                11 -> managementScreen.updateEmployeeStatusMenu()
                0 -> {
                    println("Đang thoát chương trình...")
                    isRunning = false
                }
                else -> println("Lựa chọn không hợp lệ, vui lòng thử lại.")
            }
        }
    }
}