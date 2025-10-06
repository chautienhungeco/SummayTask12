package com.example.summaytask12.data

import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.EmployeeStatus
import kotlinx.coroutines.delay

interface EmployeeRepository {
    suspend fun getAllEmployees(): List<Employee>
    suspend fun addEmployee(employee: Employee): Boolean
    suspend fun deleteEmployee(employeeId: String): Boolean
    suspend fun updateEmployeeStatus(employeeId: String, newStatus: EmployeeStatus): Employee?
    suspend fun isEmployeeIdExists(employeeId: String): Boolean
}

class EmployeeRepositoryImpl(
    val dataSeeder: EmployeeDataSeeder
) : EmployeeRepository {

    val employees: MutableList<Employee> = dataSeeder.getSampleEmployee().toMutableList()

    override suspend fun getAllEmployees(): List<Employee> {
        delay(500L) // giả lập I/O đọc dữ liệu từ db
        return employees
    }

    override suspend fun addEmployee(employee: Employee): Boolean {
        delay(200L)
        if (isEmployeeIdExists(employee.employeeId)) return false
        employees.add(employee)
        return true
    }

    override suspend fun deleteEmployee(employeeId: String): Boolean {
        delay(300L)
        return employees.removeIf { it.employeeId.equals(employeeId, ignoreCase = true) }
    }

    override suspend fun updateEmployeeStatus(employeeId: String, newStatus: EmployeeStatus): Employee? {
        delay(1000L)
        val employee = employees.find { it.employeeId.equals(employeeId, ignoreCase = true) }
        employee?.employeeStatus = newStatus
        return employee
    }

    override suspend fun isEmployeeIdExists(employeeId: String): Boolean {
        delay(300L)
        return employees.any { it.employeeId.equals(employeeId, ignoreCase = true) }
    }
}