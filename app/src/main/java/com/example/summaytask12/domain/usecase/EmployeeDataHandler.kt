package com.example.summaytask12.domain.usecase

import com.example.summaytask12.data.EmployeeRepository
import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.EmployeeStatus

class EmployeeDataHandler(val repository: EmployeeRepository) {

    fun addEmployee(employee: Employee): Boolean {
        return repository.addEmployee(employee)
    }

    fun deleteEmployee(employeeId: String): Boolean {
        return repository.deleteEmployee(employeeId)
    }

    fun updateStatus(employeeId: String, newStatus: EmployeeStatus): Employee? {
        return repository.updateEmployeeStatus(employeeId, newStatus)
    }

    fun isIdExists(employeeId: String): Boolean {
        return repository.isEmployeeIdExists(employeeId)
    }
}