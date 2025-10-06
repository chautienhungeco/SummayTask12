package com.example.summaytask12.extension

import com.example.summaytask12.domain.model.Employee
import com.example.summaytask12.domain.model.FullTimeEmployee
import com.example.summaytask12.domain.model.Intern
import com.example.summaytask12.domain.model.Position

fun List<Employee>.findHighestPaidEmployee(): Employee? {
    return this.asSequence().maxByOrNull { it.calculateSalary() }
}

fun List<Employee>.getSicenceInternsCount(): Int {
    return this.asSequence().filterIsInstance<Intern>()
        .count { it.university.lowercase().contains("đại học bách khoa") }
}

fun List<Employee>.getExpertCount(): Int {
    return this.asSequence().filterIsInstance<FullTimeEmployee>()
        .count { it.position == Position.EXPERT }
}
