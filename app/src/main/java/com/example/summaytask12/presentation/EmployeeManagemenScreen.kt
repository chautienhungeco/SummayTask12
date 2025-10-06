package com.example.summaytask12.presentation

import com.example.summaytask12.domain.model.ContactInformation
import com.example.summaytask12.domain.usecase.EmployeeCreationService
import com.example.summaytask12.domain.usecase.EmployeeDataHandler

class EmployeeManagemenScreen(
    val dataHandler: EmployeeDataHandler,
    val creationService: EmployeeCreationService
) {

        fun getBasicEmployeeInput(id: String): Triple<String, Int, Double>? {
        print("Nhập Họ tên: ")
        val fullName = readLine() ?: ""
        print("Nhập Năm sinh (ví dụ: 2000): ")
        val birthYear = readLine()?.toIntOrNull() ?: 0
        print("Nhập Hệ số lương (ví dụ: 4.5): ")
        val salaryCoefficient = readLine()?.toDoubleOrNull() ?: 0.0

        if (fullName.isEmpty() || birthYear <= 0 || salaryCoefficient <= 0) {
            println("Lỗi: Dữ liệu cơ bản không hợp lệ.")
            return null
        }
        return Triple(fullName, birthYear, salaryCoefficient)
    }

    fun addEmployeeMenu() {
        println("\n--- Thêm Nhân Viên Mới ---")
        print("Chọn loại nhân viên (1: Chính thức, 2: Thực tập sinh): ")
        val typeChoice = readLine()?.toIntOrNull()

        if (typeChoice != 1 && typeChoice != 2) {
            println("Lựa chọn không hợp lệ.")
            return
        }

        print("Nhập Mã nhân viên (ví dụ: NV04, TTS03): ")
        val id = readLine()?.trim() ?: ""
        if (dataHandler.isIdExists(id)) {
            println("Mã nhân viên $id đã tồn tại.")
            return
        }
        if (id.isEmpty()) {
            println("Mã nhân viên không được để trống.")
            return
        }

        val basicInput = getBasicEmployeeInput(id) ?: return
        val (fullName, birthYear, salaryCoefficient) = basicInput

        print("Nhập Email: ")
        val email = readLine() ?: ""
        print("Nhập Số điện thoại: ")
        val phoneNumber = readLine() ?: ""
        val contactInformation = ContactInformation(email, phoneNumber)

        println("\n--- Nhập Trạng Thái ---")
        print("Chọn trạng thái (1: Active, 2: Onleave, 3: Retired): ")
        val statusChoice = readLine()?.toIntOrNull() ?: 1
        val employeeStatus = creationService.createEmployeeStatus(statusChoice) { readLine() }

        val newEmployee = if (typeChoice == 1) {
            // Logic cho FullTimeEmployee
            print("Chọn Chức vụ (1: Quản lý phòng ban, 2: Chuyên viên): ")
            val positionChoice = readLine()?.toIntOrNull() ?: 2
            print("Nhập Phụ cấp chức vụ (ví dụ: 1500000.0): ")
            val allowance = readLine()?.toDoubleOrNull() ?: 0.0
            print("Nhập Số ngày làm thêm giờ: ")
            val overtimeDays = readLine()?.toIntOrNull() ?: 0

            creationService.createFullTimeEmployee(
                id, fullName, birthYear, salaryCoefficient, contactInformation, employeeStatus,
                positionChoice, allowance, overtimeDays
            )
        } else {
            print("Nhập Chuyên ngành: ")
            val major = readLine() ?: ""
            print("Nhập Trường đào tạo: ")
            val university = readLine() ?: ""
            print("Nhập Số dự án hoàn thành: ")
            val projectsCompleted = readLine()?.toIntOrNull() ?: 0

            creationService.createInternEmployee(
                id, fullName, birthYear, salaryCoefficient, contactInformation, employeeStatus,
                major, university, projectsCompleted
            )
        }

        if (dataHandler.addEmployee(newEmployee)) {
            println("\nĐã thêm nhân viên '${newEmployee.fullName}' (ID: $id) thành công!")
        } else {
            println("Lỗi: Không thể thêm nhân viên. Mã nhân viên có thể đã tồn tại.")
        }
    }

    fun deleteEmployeeMenu() {
        println("\n--- Xóa Nhân Viên ---")
        print("Nhập Mã nhân viên cần xóa: ")
        val idToDelete = readLine()?.trim()

        if (idToDelete.isNullOrEmpty()) {
            println("Mã nhân viên không được để trống.")
            return
        }

        val employeeToRemove = dataHandler.repository.getAllEmployees()
            .find { it.employeeId.equals(idToDelete, ignoreCase = true) }

        if (employeeToRemove != null) {
            print("Bạn có chắc chắn muốn xóa nhân viên '${employeeToRemove.fullName}' (Y/N)? ")
            val confirmation = readLine()?.trim()?.uppercase()

            if (confirmation == "Y") {
                if (dataHandler.deleteEmployee(idToDelete)) {
                    println("Đã xóa nhân viên '${employeeToRemove.fullName}' thành công.")
                } else {
                    println("Lỗi: Không thể xóa nhân viên.")
                }
            } else {
                println("Thao tác xóa đã bị hủy.")
            }
        } else {
            println("Không tìm thấy nhân viên với Mã: '$idToDelete'.")
        }
    }

    fun updateEmployeeStatusMenu() {
        println("\n--- Cập Nhật Trạng Thái Nhân Viên ---")
        print("Nhập Mã nhân viên cần cập nhật trạng thái: ")
        val idToUpdate = readLine()?.trim()

        if (idToUpdate.isNullOrEmpty()) {
            println("Mã nhân viên không được để trống.")
            return
        }

        val employee = dataHandler.repository.getAllEmployees()
            .find { it.employeeId.equals(idToUpdate, ignoreCase = true) }

        if (employee != null) {
            println("Đang cập nhật trạng thái cho: ${employee.fullName} (ID: ${employee.employeeId})")

            println("\n--- Chọn Trạng Thái Mới ---")
            print("Chọn trạng thái (1: Active, 2: Onleave, 3: Retired): ")
            val statusChoice = readLine()?.toIntOrNull() ?: 1

            val newStatus = creationService.createEmployeeStatus(statusChoice) { readLine() }

            if (dataHandler.updateStatus(idToUpdate, newStatus) != null) {
                println("Đã cập nhật trạng thái cho nhân viên ${employee.fullName}: ${newStatus::class.simpleName}")
            } else {
                println("Lỗi: Không thể cập nhật trạng thái.")
            }
        } else {
            println("Không tìm thấy nhân viên với mã: $idToUpdate")
        }
    }
}