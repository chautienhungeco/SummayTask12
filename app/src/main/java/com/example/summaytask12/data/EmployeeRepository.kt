package com.example.summaytask12.data

import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.EmployeeStatus

interface EmployeeRepository {
    fun getAllEmployees(): List<Employee>
    fun addEmployee(employee: Employee): Boolean
    fun deleteEmployee(employeeId: String): Boolean
    fun updateEmployeeStatus(employeeId: String, newStatus: EmployeeStatus): Employee?
    fun isEmployeeIdExists(employeeId: String): Boolean
}

class EmployeeRepositoryImpl(
    val dataSeeder: EmployeeDataSeeder
) : EmployeeRepository {

    val employees: MutableList<Employee> = dataSeeder.getSampleEmployee().toMutableList()

    override fun getAllEmployees(): List<Employee> = employees

    override fun addEmployee(employee: Employee): Boolean {
        if (isEmployeeIdExists(employee.employeeId)) return false
        employees.add(employee)
        return true
    }

    override fun deleteEmployee(employeeId: String): Boolean {
        return employees.removeIf { it.employeeId.equals(employeeId, ignoreCase = true) }
    }

    override fun updateEmployeeStatus(employeeId: String, newStatus: EmployeeStatus): Employee? {
        val employee = employees.find { it.employeeId.equals(employeeId, ignoreCase = true) }
        employee?.employeeStatus = newStatus
        return employee
    }

    override fun isEmployeeIdExists(employeeId: String): Boolean {
        return employees.any { it.employeeId.equals(employeeId, ignoreCase = true) }
    }
}