package com.example.summaytask12.domain.usecase

import com.example.summaytask12.data.EmployeeRepository
import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.EmployeeStatus

class EmployeeDataHandler(val repository: EmployeeRepository) {

    suspend fun addEmployee(employee: Employee): Boolean {
        return repository.addEmployee(employee)
    }

    suspend fun deleteEmployee(employeeId: String): Boolean {
        return repository.deleteEmployee(employeeId)
    }

    suspend fun updateStatus(employeeId: String, newStatus: EmployeeStatus): Employee? {
        return repository.updateEmployeeStatus(employeeId, newStatus)
    }

    suspend fun isIdExists(employeeId: String): Boolean {
        return repository.isEmployeeIdExists(employeeId)
    }
}