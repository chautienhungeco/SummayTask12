package com.example.summaytask12.util

import com.example.summaytask12.model.*

class EmployeeManagementService {

    fun addEmployeeMenu(employees: MutableList<Employee>) {
        println("\n--- Thêm Nhân Viên Mới ---")
        print("Chọn loại nhân viên (1: Chính thức, 2: Thực tập sinh): ")
        val typeChoice = readLine()?.toIntOrNull()

        if (typeChoice != 1 && typeChoice != 2) {
            println("Lựa chọn không hợp lệ.")
            return
        } else {

            print("Nhập Mã nhân viên (ví dụ: NV04, TTS03): ")
            val id = readLine()?.trim() ?: ""
            if (employees.any { it.employeeId.equals(id, ignoreCase = true) }) {
                println("Mã nhân viên $id đã tồn tại")
                return
            } else {
                print("Nhập Họ tên: ")
                val fullName = readLine() ?: ""
                print("Nhập Năm sinh (ví dụ: 2000): ")
                val birthYear = readLine()?.toIntOrNull() ?: 0
                print("Nhập Hệ số lương (ví dụ: 4.5): ")
                val salaryCoeffecient = readLine()?.toDoubleOrNull() ?: 0.0

                print("Nhập Email: ")
                val email = readLine() ?: ""
                print("Nhập Số điện thoại: ")
                val phone = readLine() ?: ""
                val contact = ContactInformation(email, phone)

                val status = getEmployeeStatus()

                val newEmployee = when (typeChoice) {
                    1 -> createFullTimeEmployee(
                        id,
                        fullName,
                        birthYear,
                        salaryCoeffecient,
                        contact,
                        status
                    )

                    2 -> createInternEmployee(
                        id,
                        fullName,
                        birthYear,
                        salaryCoeffecient,
                        contact,
                        status
                    )

                    else -> null
                }

                if (newEmployee != null) {
                    employees.add(newEmployee)
                    println(" Đã thêm nhân viên '${newEmployee.fullName}' vào hệ thống.")
                } else {
                    println(" Không thể tạo nhân viên. Hủy thao tác.")
                }
            }
        }
    }

    fun getEmployeeStatus(): EmployeeStatus {
        println("\n--- Thiết lập Trạng thái ---")
        print("Chọn trạng thái (1: Active, 2: Onleave, 3: Retired): ")
        return when (readLine()?.toIntOrNull()) {
            2 -> {
                print("Nhập lý do nghỉ phép: ")
                EmployeeStatus.Onleave(readLine() ?: "Không rõ lý do")
            }

            3 -> {
                print("Nhập ngày nghỉ hưu (dd/MM/yyyy): ")
                val date = readLine() ?: "Không rõ ngày"
                print("Nhập lý do nghỉ hưu: ")
                val reason = readLine() ?: "Không rõ lý do"
                EmployeeStatus.Retired(date, reason)
            }

            else -> EmployeeStatus.Active
        }
    }

    //tạo nv chính thức
    fun createFullTimeEmployee(
        id: String, fullName: String, birthYear: Int, salaryCoeffecient: Double,
        contact: ContactInformation, status: EmployeeStatus
    ): FullTimeEmployee {
        println("\n--- Thông tin Nhân viên Chính thức ---")
        print("Chọn Chức vụ (1: Quản lý, 2: Chuyên viên, 3: TTS): ")
        val position = when (readLine()?.toIntOrNull()) {
            1 -> Position.DEPARTMENT_MANAGER
            2 -> Position.EXPERT
            else -> Position.INTERN
        }
        print("Nhập Phụ cấp chức vụ (ví dụ: 1000000.0): ")
        val allowance = readLine()?.toDoubleOrNull() ?: 0.0
        print("Nhập Số ngày tăng ca: ")
        val overtime = readLine()?.toIntOrNull() ?: 0

        return FullTimeEmployee(
            id, fullName, birthYear, salaryCoeffecient, contact, status,
            position, allowance, overtime
        )
    }

    //thêm tts
    fun createInternEmployee(
        id: String, fullName: String, birthYear: Int, salaryCoeffecient: Double,
        contact: ContactInformation, status: EmployeeStatus
    ): Intern {
        println("\n--- Thông tin Thực tập sinh ---")
        print("Nhập Chuyên ngành: ")
        val major = readLine() ?: ""
        print("Nhập Trường đại học: ")
        val university = readLine() ?: ""
        print("Nhập Số dự án hoàn thành: ")
        val projects = readLine()?.toIntOrNull() ?: 0

        return Intern(
            id, fullName, birthYear, salaryCoeffecient, contact, status,
            major, university, projects
        )
    }

    //xóa nhân viên
    fun deleteEmployeeMenu(employees: MutableList<Employee>) {
        println("\n=== Xóa Nhân Viên ===")
        print("Nhập Mã nhân viên cần xóa: ")
        val idToDelete = readLine()?.trim()

        if (idToDelete.isNullOrEmpty()) {
            println("Mã nhân viên không được để trống. Hủy thao tác.")
            return
        } else {

            // xác nhận xóa
            val employeeToRemove =
                employees.find { it.employeeId.equals(idToDelete, ignoreCase = true) }

            if (employeeToRemove != null) {
                print("Bạn có chắc chắn muốn xóa nhân viên '${employeeToRemove.fullName}' (ID: ${employeeToRemove.employeeId})? (Y/N): ")
                if (readLine()?.trim()?.uppercase() == "Y") {
                    employees.remove(employeeToRemove) // Xóa đối tượng đã tìm thấy
                    println("Đã xóa nhân viên '${employeeToRemove.fullName}' Thành công.")
                } else {
                    println("Thao tác xóa đã bị hủy.")
                }
            } else {
                println("Không tìm thấy nhân viên với Mã: '$idToDelete'.")
            }
        }
    }

    //cập nhật trạng thái nv
    fun updateEmployeeStatusMenu(employees: MutableList<Employee>) {
        println("\n--- Cập Nhật Trạng Thái Nhân Viên ---")
        print("Nhập Mã nhân viên cần cập nhật trạng thái: ")
        val idToUpdate = readLine()?.trim()

        if (idToUpdate.isNullOrEmpty()) {
            println("Mã nhân viên không được để trống.")
            return
        } else {
            val index =
                employees.indexOfFirst { it.employeeId.equals(idToUpdate, ignoreCase = true) }

            if (index != -1) {
                val employee = employees[index]
                println("Đang cập nhật trạng thái cho: ${employee.fullName} (ID: ${employee.employeeId})")

                val newStatus = getEmployeeStatus()

                employee.employeeStatus = newStatus
                println("Đã cập nhật trạng thái cho nhân viên ${employee.fullName}: ${newStatus::class.simpleName}")
            } else {
                println("Không tìm thấy nhân viên với mã: $idToUpdate")
            }
        }
    }
}