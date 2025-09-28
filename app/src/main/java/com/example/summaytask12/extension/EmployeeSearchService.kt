package com.example.summaytask12.util

import com.example.summaytask12.model.Employee
import com.example.summaytask12.model.FullTimeEmployee
import com.example.summaytask12.model.Intern
import com.example.summaytask12.model.Position

class EmployeeSearchService {

    fun filterByBirthYear(employees: List<Employee>, maxYear: Int): List<Employee> {
        val filteredList = mutableListOf<Employee>()

        for (employee in employees) {
            if (employee.birthYear <= maxYear) {
                filteredList.add(employee)
            }
        }
        return filteredList
    }

    fun countEmployeesByPosition(employees: List<Employee>): Map<Position, Int> {

        val positions = employees.map { employee ->
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
}