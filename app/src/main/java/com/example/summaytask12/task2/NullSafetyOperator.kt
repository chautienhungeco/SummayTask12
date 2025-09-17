package com.example.summaytask12.task2

import android.util.Log

private const val TAG = "NullSafetyOperator"

fun main() {
    getDomainOrDefault(null)
    getDomainOrDefault("hungct@eco.com")

    forceUppercase(null)
    forceUppercase("helloEco")
}
    // trích xuất Domain từ mail
    fun getDomainOrDefault(email: String?): String {
        val domain = email?.substringAfter('@')?.takeIf { it.isNotBlank() } ?: "unknown.domain"
        Log.d(TAG, "Domain: $domain (input=$email)")
        return domain
    }

    // !! dùng khi đã được kiểm tra null
    fun forceUppercase(text: String?): String {
        return try {
            val result = text!!.uppercase()
            Log.d(TAG, "Yêu cầu viết Hoa: $result")
            result
        } catch (e: NullPointerException) {
            Log.d(TAG, "NullPointerException")
            ""
        }
    }


