package com.example.summaytask12.domain.usecase

import com.example.summaytask12.data.EmployeeRepository
import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.FullTimeEmployee
import com.example.summaytask12.domain.model.Intern
import com.example.summaytask12.domain.model.Position

class EmployeeSearchService(val repository: EmployeeRepository) {

    fun filterByBirthYear(maxYear: Int): List<Employee> {
        val employees = repository.getAllEmployees()
        return employees.filter { it.birthYear <= maxYear }
    }

    fun countEmployeesByPosition(): Map<Position, Int> {
        val employees = repository.getAllEmployees()
        val positions = employees.asSequence().map { employee ->
            when (employee) {
                is FullTimeEmployee -> employee.position
                is Intern -> Position.INTERN
                else -> Position.INTERN
            }
        }

        return positions
            .groupBy { it }
            .mapValues { it.value.size }
    }

    fun filterBySalaryRange(
        minSalary: Double,
        maxSalary: Double
    ): List<Employee> {
        val employees = repository.getAllEmployees()
        return employees.filter { employee ->
            val salary = employee.calculateSalary()
            salary >= minSalary && salary <= maxSalary
        }
    }
}