package com.example.summaytask12.domain.usecase

import com.example.summaytask12.data.EmployeeRepository
import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.FullTimeEmployee
import com.example.summaytask12.domain.model.Intern
import com.example.summaytask12.domain.model.Position
import com.example.summaytask12.domain.model.SortableCriteria

class EmployeeSearchService(val repository: EmployeeRepository) {

    suspend fun countEmployeesByPosition(): Map<Position, Int> {
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

    suspend fun filerEmployee(
        predicate: (Employee) -> Boolean
    ): List<Employee> {
        val employees = repository.getAllEmployees()
        return employees.filter(predicate)
    }

    suspend fun sortEmployees(
        criteria: SortableCriteria<Employee>,
        isDescending: Boolean = false
    ): List<Employee>{
        val employees = repository.getAllEmployees()
        val sortedList = employees.sortedWith(compareBy{
            employee ->
                criteria.getKey(employee)
        })

        if (isDescending){
            return sortedList.reversed()
        }else{
            return sortedList
        }
    }
}
/*
    suspend fun filterByBirthYear(maxYear: Int): List<Employee> {
        val employees = repository.getAllEmployees()
        return employees.filter { it.birthYear <= maxYear }
    }

    suspend fun filterBySalaryRange(
        minSalary: Double,
        maxSalary: Double
    ): List<Employee> {
        val employees = repository.getAllEmployees()
        return employees.filter { employee ->
            val salary = employee.calculateSalary()
            salary >= minSalary && salary <= maxSalary
        }
    }
}*/