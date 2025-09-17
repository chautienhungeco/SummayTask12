package com.example.summaytask12.task2

import android.util.Log

private const val TAG = "NullSafetyBasics"

fun main() {
    getUserNameLength(null)
    getUserNameLength("Hưngka")

    printEmailIfAvailable(null)
    printEmailIfAvailable("hungct@eco.com")
}

    // độ dài hợp lệ về tên, cần check null
    fun getUserNameLength(userName: String?): Int {
        val length = userName?.length ?: 0
        Log.d(TAG, "Độ dài Tên người dùng: $length (input=$userName)")
        return length
    }

    // kiểm tra email không đc để trống với let-run
    fun printEmailIfAvailable(email: String?) {
        email?.let {
            Log.d(TAG, "Email hợp lệ: $it")
        } ?: run {
            Log.d(TAG, "Email null hoặc rỗng")
        }
    }

