package com.example.summaytask12.domain.model

class SalarySortCriteria: SortableCriteria<Employee> {
    override fun getKey(item: Employee): Comparable<*> {
        return item.calculateSalary()
    }
}
class BirthYearSortCriteria: SortableCriteria<Employee>{
    override fun getKey(item: Employee): Comparable<*> {
        return item.birthYear
    }
}