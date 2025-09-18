package com.example.summaytask12

import android.util.Log
import kotlin.system.measureTimeMillis

fun main() {
    val singleExpr = SingleExpressFunction()

    singleExpr.compareTranditionalAndSingleLine()
    singleExpr.testCollectionOperations()
    singleExpr.compareIfElseAndWhen()
    singleExpr.comparePerformance()
}

class SingleExpressFunction {

    companion object {
        private const val TAG = "SingleExpressFunction"
    }

    fun compareTranditionalAndSingleLine(){
        Log.d(TAG, "===Các hàm cơ bản====")
        Log.d(TAG, "Tổng (hàm biểu thức đơn) : ${calculateSum(10, 20)}")
        Log.d(TAG, "Tổng (Hàm thông thường) : ${calculateSumTraditional(10, 20)}")
        Log.d(TAG, "Tổng (overflow) : ${calculateSumSafe(Int.MAX_VALUE, 1)}")
    }

    fun testCollectionOperations(){
        Log.d(TAG, "==Tập hợp====")
        val testList = listOf(2, -13, 0, 10, 2, 8, 4, 12)
        Log.d(TAG, "Danh sách (Filter + Map): ${filterAndMapNumbers(testList)}")
        Log.d(TAG, "Danh sách (Optimized): ${filterAndMapNumbersOptimized(testList)}")
    }

    fun compareIfElseAndWhen(){
        Log.d(TAG, "==Hàm logic có điều kiện===")
        Log.d(TAG, "Điểm đạt (if-else): ${calculateGradeNested(85)}")
        Log.d(TAG, "Điểm đạt (when): ${calculateGradeWhen(85)}")
        Log.d(TAG, "Điểm đạt (range): ${calculateGradeRange(85)}")
    }

    fun comparePerformance(){
        Log.d(TAG, "==So sánh về hiệu suất===")
        val largeList = (1..1000).toList()
        compareFilterMapPerformance(largeList)
        compareConditionalPerformance()
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

    fun compareFilterMapPerformance(numberList: List<Int>) {
        Log.d(TAG, "=== SO SÁNH HIỆU SUẤT FILTER + MAP ===")
        Log.d(TAG, "Dữ liệu test: ${numberList.size} phần tử")

        val time1 = measureTimeMillis {
            repeat(1000) {
                filterAndMapNumbers(numberList)
            }
        }
        Log.d(TAG, "Biểu thức đơn với (filter + map): ${time1}ms")  //192ms

        val time2 = measureTimeMillis {
            repeat(1000) {
                filterAndMapNumbersOptimized(numberList)
            }
        }
        Log.d(TAG, "Biểu thức đơn tối ưu với (mapNotNull): ${time2}ms") //134ms

        val time3 = measureTimeMillis {
            repeat(1000) {
                filterAndMapNumbersTraditional(numberList)
            }
        }
        Log.d(TAG, "Vòng lặp cơ bản: ${time3}ms")      //116ms
    }

    fun compareConditionalPerformance() {
        Log.d(TAG, "=== SO SÁNH HIỆU SUẤT CONDITIONAL ===")
        val scores = listOf(95, 85, 75, 65, 55)

        val time1 = measureTimeMillis {
            repeat(10000) {
                scores.forEach { calculateGradeNested(it) }
            }
        }
        Log.d(TAG, "if-else lồng: ${time1}ms")  //10ms

        val time2 = measureTimeMillis {
            repeat(10000) {
                scores.forEach { calculateGradeWhen(it) }
            }
        }
        Log.d(TAG, "Điều kiện When: ${time2}ms")    //5ms

        val time3 = measureTimeMillis {
            repeat(10000) {
                scores.forEach { calculateGradeRange(it) }
            }
        }
        Log.d(TAG, "Range-based when: ${time3}ms") //4ms
    }

}
