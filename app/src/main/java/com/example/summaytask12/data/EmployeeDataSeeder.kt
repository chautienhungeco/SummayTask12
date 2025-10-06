package com.example.summaytask12.data

import com.example.summaytask12.domain.model.ContactInformation
import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.EmployeeStatus
import com.example.summaytask12.domain.model.FullTimeEmployee
import com.example.summaytask12.domain.model.Intern
import com.example.summaytask12.domain.model.Position


class EmployeeDataSeeder {
    fun getSampleEmployee(): List<Employee> {
        return listOf<Employee>(
            FullTimeEmployee(
                employeeId = "NV01",
                fullName = "Châu Tiến Hưng",
                birthYear = 2002,
                salaryCoeffecient = 8.5,
                position = Position.DEPARTMENT_MANAGER,
                positionAllowance = 25000000.0,
                overtimeDays = 1,
                contactInformation = ContactInformation("hungct@gmail.com", "0982738219"),
                employeeStatus = EmployeeStatus.Active
            ),
            FullTimeEmployee(
                employeeId = "NV02",
                fullName = "Long Lanh",
                birthYear = 1999,
                salaryCoeffecient = 5.5,
                position = Position.DEPARTMENT_MANAGER,
                positionAllowance = 15000000.0,
                overtimeDays = 2,
                contactInformation = ContactInformation("longlanh@gmail.com", "01234545"),
                employeeStatus = EmployeeStatus.Onleave("Nghỉ thai sản")
            ),
            FullTimeEmployee(
                employeeId = "NV03",
                fullName = "nóng nánh",
                birthYear = 2005,
                salaryCoeffecient = 5.5,
                position = Position.EXPERT,
                positionAllowance = 5000000.0,
                overtimeDays = 3,
                contactInformation = ContactInformation("nongnanh@gmail.com", "036748212"),
                employeeStatus = EmployeeStatus.Active
            ), Intern(
                employeeId = "TTS01",
                fullName = "Lo Lắng",
                birthYear = 2005,
                salaryCoeffecient = 3.5,
                major = "Công nghệ thông tin",
                university = "Đại học Công nghệ",
                projectsCompleted = 4,
                contactInformation = ContactInformation("lolang@gmail.com", "01324112"),
                employeeStatus = EmployeeStatus.Active
            ),
            Intern(
                employeeId = "TTS02",
                fullName = "Trần Thanh Long",
                birthYear = 2005,
                salaryCoeffecient = 3.5,
                major = "Kỹ thuật mật mã",
                university = "Đại học Bách Khoa",
                projectsCompleted = 4,
                contactInformation = ContactInformation("longtt@gmail.com", "018272318"),
                employeeStatus = EmployeeStatus.Retired("19/09/2025", "Môi trường không phù hợp")
            )
        )
    }
}