package com.example.summaytask12.presentation

import com.example.summaytask12.domain.model.ContactInformation
import com.example.summaytask12.domain.usecase.EmployeeCreationService
import com.example.summaytask12.domain.usecase.EmployeeDataHandler
import com.example.summaytask12.domain.validation.InputValidator

class EmployeeManagemenScreen(
    private val inputValidator: InputValidator,
    private val dataHandler: EmployeeDataHandler,
    private val creationService: EmployeeCreationService
) {

    private fun getBasicEmployeeInput(): Triple<String, Int, Double>? {

        print("Nhập họ tên: ")
        val fullName = readlnOrNull() ?: ""
        if (!inputValidator.isValidName(fullName)) {
            println("Họ tên không hợp lệ (Không được chứa số hoặc ký tự đặc biệt)!.")
            return null
        } else {
            //tiếp tuc
        }

        print("Nhập Năm sinh (ví dụ: 2000): ")
        val birthYearInt = readlnOrNull() ?: ""
        if (!inputValidator.isValidInt(birthYearInt)) {
            println("Năm sinh là số nguyên dương!")
            return null
        } else {
            //tiếp tuục
        }
        val birthYear = birthYearInt.toInt()

        print("Nhập Hệ số lương (ví dụ: 4.5): ")
        val salaryCoeffecient = readlnOrNull() ?: ""
        if (!inputValidator.isValidDouble(salaryCoeffecient)) {
            println("Hệ số lượng không hợp lệ")
            return null
        } else {
            //tiếp tục
        }

        val salaryCoefficient = salaryCoeffecient.replace(',', '.').toDouble()

        return Triple(fullName, birthYear, salaryCoefficient)
    }

    fun addEmployeeMenu() {
        println("\n--- Thêm Nhân Viên Mới ---")
        print("Chọn loại nhân viên (1: Chính thức, 2: Thực tập sinh): ")
        val typeChoice = readlnOrNull()?.toIntOrNull()

        if (typeChoice != 1 && typeChoice != 2) {
            println("Lựa chọn không hợp lệ.")
            return
        }

        print("Nhập Mã nhân viên (ví dụ: NV04, TTS03): ")
        val id = readlnOrNull()?.trim() ?: ""
        if (dataHandler.isIdExists(id)) {
            println("Mã nhân viên $id đã tồn tại.")
            return
        }
        if (id.isEmpty()) {
            println("Mã nhân viên không được để trống.")
            return
        }

        val basicInput = getBasicEmployeeInput() ?: return
        val (fullName, birthYear, salaryCoefficient) = basicInput

        print("Nhập Email: ")
        val email = readlnOrNull() ?: ""
        if (!inputValidator.isValidEmail(email)) {
            println("Email không hợp lệ")
            return
        } else {
            //tieeps tuc
        }
        print("Nhập Số điện thoại: ")
        val phoneNumber = readlnOrNull() ?: ""
        if (!inputValidator.isValidPhoneNumber(phoneNumber)) {
            println("Số điện thoại phải 9-11 số")
            return
        } else {
            // tiep tuc
        }
        val contactInformation = ContactInformation(email, phoneNumber)

        println("\n--- Nhập Trạng Thái ---")
        print("Chọn trạng thái (1: Active, 2: Onleave, 3: Retired): ")
        val statusChoice = readlnOrNull()?.toIntOrNull() ?: 1
        val employeeStatus = creationService.createEmployeeStatus(statusChoice) { readlnOrNull() }

        val newEmployee = if (typeChoice == 1) {

            print("Chọn Chức vụ (1: Quản lý phòng ban, 2: Chuyên viên): ")
            val positionChoice = readlnOrNull()?.toIntOrNull() ?: 2
            print("Nhập Phụ cấp chức vụ (ví dụ: 1500000.0): ")
            val allowanceStr = readlnOrNull()
            if (!inputValidator.isValidDouble(allowanceStr)) {
                println("Phụ cấp không hợp lệ")
                return
            } else {
                //tiep tuc
            }
            val allowance = allowanceStr!!.replace(',', '.').toDouble()
            print("Nhập Số ngày làm thêm giờ: ")
            val overtimeDays = readlnOrNull()?.toIntOrNull() ?: 0

            creationService.createFullTimeEmployee(
                id, fullName, birthYear, salaryCoefficient, contactInformation, employeeStatus,
                positionChoice, allowance, overtimeDays
            )
        } else {
            print("Nhập Chuyên ngành: ")
            val major = readlnOrNull() ?: ""
            print("Nhập Trường đào tạo: ")
            val university = readlnOrNull() ?: ""
            print("Nhập Số dự án hoàn thành: ")
            val projectsCompleted = readlnOrNull()?.toIntOrNull() ?: 0

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
        val idToDelete = readlnOrNull()?.trim()

        if (idToDelete.isNullOrEmpty()) {
            println("Mã nhân viên không được để trống.")
            return
        }

        val employeeToRemove = dataHandler.repository.getAllEmployees()
            .find { it.employeeId.equals(idToDelete, ignoreCase = true) }

        if (employeeToRemove != null) {
            print("Bạn có chắc chắn muốn xóa nhân viên '${employeeToRemove.fullName}' (Y/N)? ")
            val confirmation = readlnOrNull()?.trim()?.uppercase()

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
        val idToUpdate = readlnOrNull()?.trim()

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
            val statusChoice = readlnOrNull()?.toIntOrNull() ?: 1

            val newStatus = creationService.createEmployeeStatus(statusChoice) { readlnOrNull() }

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