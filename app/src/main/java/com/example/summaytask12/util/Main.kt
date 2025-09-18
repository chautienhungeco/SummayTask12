package com.example.summaytask12.util

fun main(){
    val employeeManger = EmployeeManager()
    employeeManger.runEmployeeManagementSystem()
}
class EmployeeManager{
    private val employees = EmployeeDataProvider.getSampleEmployee()

    fun runEmployeeManagementSystem(){
        val fullReport = ReportGenarator.generateFullReport(employees)
        println(fullReport)
    }
}