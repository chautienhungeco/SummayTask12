package com.example.summaytask12

import android.util.Log

private const val TAG = "NullSafetyBasics"

fun runExtensionDemo() {
    val name = "   hƯnG   nÈ   "
    Log.d(TAG, "Chuẩn Hóa + Viết Hoa: ${name.normalizeAndCapitalize()}")

    val emailOk = "hungct@eco.com"
    val emailBad = "dat@@eco"
    Log.d(TAG, "Email hợp lệ ($emailOk): ${emailOk.isLikelyEmail()}")
    Log.d(TAG, "Email không hợp lệ ($emailBad): ${emailBad.isLikelyEmail()}")

    val rawPhone = " 374-062-849 "
    Log.d(TAG, "Điện thoại: ${rawPhone.formatAsLocalPhone()} ")

    val items = (1..17).toList()

}
    //chuẩn hóa khoảng trắng và viết hoa chữ cái đầu mỗi từ
    fun String.normalizeAndCapitalize(): String =
        this.trim()
            .split(Regex("\\s+"))
            .joinToString(" ") { word ->
                if (word.isNotEmpty()) word.replaceFirstChar { it.uppercase() } else word
            }

    // kiểm tra email cơ bản: có '@' và có '.' phía sau '@'
    fun String.isLikelyEmail(): Boolean {
        val atIndex = this.indexOf('@')
        if (atIndex <= 0) return false
        val dotIndex = this.indexOf('.', atIndex + 2)
        return dotIndex > atIndex + 1 && dotIndex < this.lastIndex
    }

    // chuẩn hóa số điện thoại cơ bản: chỉ giữ số, thêm '0' nếu chưa có
    fun String.formatAsLocalPhone(): String {
        val digits = this.filter { it.isDigit() }
        if (digits.isEmpty()) return ""
        return if (digits.startsWith("0")){
            digits
        }  else {
            "0$digits"
        }
    }


