package com.example.summaytask12.presentation

import com.example.summaytask12.data.EmployeeDataSeeder
import com.example.summaytask12.data.EmployeeRepositoryImpl
import com.example.summaytask12.domain.usecase.EmployeeCreationService
import com.example.summaytask12.domain.usecase.EmployeeDataHandler
import com.example.summaytask12.domain.usecase.EmployeeReportService
import com.example.summaytask12.domain.usecase.EmployeeSearchService
import com.example.summaytask12.domain.validation.InputValidator
import kotlinx.coroutines.runBlocking

class EmployeeProgram {
    private val repository = EmployeeRepositoryImpl(EmployeeDataSeeder())

    private val reportService = EmployeeReportService(repository)
    private val searchService = EmployeeSearchService(repository)
    private val creationService = EmployeeCreationService()
    private val dataHandler = EmployeeDataHandler(repository)
    private val inputValidator = InputValidator()

    private val searchScreen = EmployeeSearchScreen(searchService)
    private val managementScreen = EmployeeManagemenScreen(inputValidator, dataHandler, creationService)

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
        println("12. Sắp sếp danh sách nhân viên(lương/năm sinh)")
        println("0. Thoát chương trình")
        print("Nhập lựa chọn của bạn: ")
    }

    fun run() {
        var isRunning = true
        while (isRunning) {
            displayMenu()
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> runBlocking {
                    println(reportService.generateEmployeeListReport())
                }
                2 -> runBlocking {
                    println(reportService.generateHighestPaidEmployeeReport())
                }
                3 -> runBlocking {
                    println(reportService.generateStatisticsReport())
                }
                4 -> runBlocking{
                    searchScreen.searchEmployeeByNameMenu()
                }
                5 -> runBlocking(){
                    searchScreen.searchEmployeeByStatusMenu()
                }
                6 -> runBlocking{
                    searchScreen.filterEmployeeByBirthYearMenu()
                }
                7 -> runBlocking(){
                    searchScreen.findEmployeeByRangeSalary()
                }
                8 -> runBlocking{
                    searchScreen.countEmployeeByPositionMenu()
                }
                9 -> runBlocking{
                    managementScreen.addEmployeeMenu()
                }
                10 -> runBlocking{
                    managementScreen.deleteEmployeeMenu()
                }
                11 -> runBlocking{
                    managementScreen.updateEmployeeStatusMenu()
                }
                12 -> runBlocking {
                    searchScreen.sortEmployeeMenu()
                }
                0 -> {
                    println("Đang thoát chương trình...")
                    isRunning = false
                }

                else -> println("Lựa chọn không hợp lệ, vui lòng thử lại.")
            }
        }
    }
}