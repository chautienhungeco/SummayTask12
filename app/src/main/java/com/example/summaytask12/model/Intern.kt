package com.example.summaytask12.model

class Intern(
    employeeId: String,
    fullName: String,
    birthYear: Int,
    salaryCoeffecient: Double,
    val major: String,
    val university: String,
    val projectsCompleted: Int
): Employee(employeeId, fullName, birthYear, salaryCoeffecient){
    override fun calculateSalary(): Double {
        var salary = salaryCoeffecient.times(1000000).plus(projectsCompleted.times(1200000))
        if (university == "Đại học Bách Khoa"){
            salary += 1000000.0
        }else{
            //không làm gì cả
        }
        if (projectsCompleted > 5){
            salary += 500000.0
        }else{
            //Không làm gì cả
        }
        return salary
    }
}