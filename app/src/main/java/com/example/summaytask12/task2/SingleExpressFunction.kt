package com.example.summaytask12.task2

import android.util.Log
import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    val singleExpr = SingleExpressFunction()

    singleExpr.compareTranditionalAndSingleLine()
    singleExpr.testCollectionOperations()
    singleExpr.compareIfElseAndWhen()
}

class SingleExpressFunction {

    companion object {
        private const val TAG = "SingleExpressFunction"
    }

    fun compareTranditionalAndSingleLine(){
        Log.d(TAG, "1. base function")
        Log.d(TAG, "Tổng (hàm biểu thức đơn) : ${calculateSum(10, 20)}")
        Log.d(TAG, "Tổng (Hàm thông thường) : ${calculateSumTraditional(10, 20)}")
        Log.d(TAG, "Tổng (overflow) : ${calculateSumSafe(Int.MAX_VALUE, 1)}")
    }

    fun testCollectionOperations(){
        Log.d(TAG, "3. Collection Operations:")
        val testList = listOf(2, -13, 0, 10, 2, 8, 4, 12)
        Log.d(TAG, "Danh sách (Filter + Map): ${filterAndMapNumbers(testList)}")
        Log.d(TAG, "Danh sách (Optimized): ${filterAndMapNumbersOptimized(testList)}")
    }

    fun compareIfElseAndWhen(){
        Log.d(TAG, "4. Conditional Logic:")
        Log.d(TAG, "Điểm đạt (if-else): ${calculateGradeNested(85)}")
        Log.d(TAG, "Điểm đạt (when): ${calculateGradeWhen(85)}")
        Log.d(TAG, "Điểm đạt (range): ${calculateGradeRange(85)}")
    }

     //hàm biểu thức đơn: Ngắn gọn, dễ đọc, hiệu quả với phép tính đơn giản
    fun calculateSum(firstNumber: Int, secondNumber: Int): Int = firstNumber + secondNumber

     //hàm cơ bản: Dễ debug, có thể thêm validation, phù hợp ctruc phức tạp
    fun calculateSumTraditional(firstNumber: Int, secondNumber: Int): Int {
        Log.d(TAG, "Tính tổng: $firstNumber + $secondNumber")
        return firstNumber + secondNumber
    }

    // Xử lý overflow an toàn
    fun calculateSumSafe(firstNumber: Int, secondNumber: Int): Long {
        return firstNumber.toLong() + secondNumber.toLong()
    }

     //filter + map: Tạo 2 intermediate collections
    fun filterAndMapNumbers(numberList: List<Int>): List<String> = 
        numberList.filter { it > 0 }.map { "Number: $it" }

    //mapNotNull: Chỉ tạo 1 intermediate collection (TỐI ƯU HƠN)
    fun filterAndMapNumbersOptimized(numberList: List<Int>): List<String> = 
        numberList.mapNotNull { if (it > 0){
            "Number: $it"
        } else {
            null
        }
    }

    //vòng for: Không tạo intermediate collections (TỐI ƯU NHẤT cho large data)
    fun filterAndMapNumbersTraditional(numberList: List<Int>): List<String> {
        val result = mutableListOf<String>()
        for (number in numberList) {
            if (number > 0) {
                result.add("Number: $number")
            }
        }
        return result
    }

     // if-else lồng: Khó đọc với nhiều điều kiện
    fun calculateGradeNested(score: Int): String = 
        if (score >= 90) "A" 
        else if (score >= 80) "B" 
        else if (score >= 70) "C" 
        else if (score >= 60) "D" 
        else "F"

     //When: Dễ đọc, hiệu quả hơn với nhiều điều kiện if-else lồng
    fun calculateGradeWhen(score: Int): String = when {
        score >= 90 -> "A"
        score >= 80 -> "B"
        score >= 70 -> "C"
        score >= 60 -> "D"
        else -> "F"
    }

    //Range-based when: Ngắn gọn nhất, hiệu quả nhất khi xác định đc trước khoảng giá trị
    fun calculateGradeRange(score: Int): String = when (score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }

}
