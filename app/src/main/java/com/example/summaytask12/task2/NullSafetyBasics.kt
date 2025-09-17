package com.example.summaytask12.task2

import android.util.Log

private const val TAG = "NullSafetyBasics"

fun main() {
    getUserNameLength(null)
    getUserNameLength("Hưngka")

    printEmailIfAvailable(null)
    printEmailIfAvailable("hungct@eco.com")

    sanitizeAndReport("   Xin chào  ")
    sanitizeAndReport("   ")

    buildDisplayName(UserProfile(null, null))
    buildDisplayName(UserProfile("Hưng", "Hungkaka@domain.com"))

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

    // let + also để xử lý chuỗi pipeline nullable
    fun sanitizeAndReport(input: String?): String {
        val result = input
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?.lowercase()
            ?.also { Log.d(TAG, "Đã chuẩn hóa: $it") }
            ?: "n/a"
        Log.d(TAG, "Kết quả cuối: $result")
        return result
    }

    // run với object nullable (cung cấp scope an toàn)
    data class UserProfile(val name: String?, val email: String?)

    fun buildDisplayName(profile: UserProfile?): String {
        val display = profile?.run {
            val safeName = name?.takeIf { it.isNotBlank() } ?: "Unknown"
            val safeEmail = email?.takeIf { it.isNotBlank() } ?: "-"
            "$safeName <$safeEmail>"
        } ?: "nó null <->"
        Log.d(TAG, "Tên là: $display")
        return display
    }


