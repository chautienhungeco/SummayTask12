package com.example.summaytask12.util

import android.content.ContentValues.TAG
import android.util.Log
import com.example.summaytask12.model.Employee
import com.example.summaytask12.model.FullTimeEmployee
import com.example.summaytask12.model.Intern

object EmployeeDataProvider {
    fun getSampleEmployee(): List<Employee>{
        return listOf<Employee>(
            FullTimeEmployee(
                employeeId = "NV01",
                fullName = "Châu Tiến Hưng",
                birthYear = 2002,
                salaryCoeffecient = 8.5,
                position = "Trưởng phòng",
                positionAllowance = 25000000.0,
                overtimeDays = 1
            ),
            FullTimeEmployee(
                employeeId = "NV02",
                fullName = "Long Lanh",
                birthYear = 1999,
                salaryCoeffecient = 5.5,
                position = "Nhân viên",
                positionAllowance = 15000000.0,
                overtimeDays = 2
            ),
            FullTimeEmployee(
                employeeId = "NV03",
                fullName = "nóng nánh",
                birthYear = 2005,
                salaryCoeffecient = 5.5,
                position = "Chuyên viên",
                positionAllowance = 5000000.0,
                overtimeDays = 3
            ), Intern(
                employeeId = "TTS01",
                fullName = "Lo Lắng",
                birthYear = 2005,
                salaryCoeffecient = 3.5,
                major = "Công nghệ thông tin",
                university = "Đại học Công nghệ",
                projectsCompleted = 4
            ),
            Intern(
                employeeId = "TTS02",
                fullName = "Lo Lắng",
                birthYear = 2005,
                salaryCoeffecient = 3.5,
                major = "Kỹ thuật mật mã",
                university = "Đại học Bách Khoa",
                projectsCompleted = 4
            )
        )
    }
}