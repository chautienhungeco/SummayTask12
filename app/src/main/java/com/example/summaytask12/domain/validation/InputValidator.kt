package com.example.summaytask12.domain.validation

class InputValidator {
    //chữ cái và khoảng trắng
    private val nameRegex = Regex("^[a-zA-Z]*\$")

    //số nguyên dương (loại chữ cái và kí tự đặc biệt)
    private val positiveIntRegex = Regex("^[1-9]\\d*\$")

    //số thập phân(.,) loại chữ cái
    private val doubleRegex = Regex("^[0-9]+([.,][0-9]+)?\$")

    //email cơ bản (abc@gmail.com)
    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")

    //số điện thoại cơ bản (9-11) số
    private val phoneRegex = Regex("^\\d{9,11}\$")

    fun isValidName(name: String?): Boolean{
        if (name.isNullOrBlank()){
            return false
        }else{
            return name.trim().matches(nameRegex)
        }
    }

    fun isValidInt(value: String?): Boolean{
        if (value.isNullOrBlank()){
            return false
        }else{
            return value.trim().matches(positiveIntRegex)
        }
    }

    fun isValidDouble(value: String?): Boolean{
        if (value.isNullOrBlank()){
            return false
        }else{
            val normalizer = value.replace(',','.')
            return normalizer.trim().matches(doubleRegex)
        }
    }

    fun isValidEmail(email: String?): Boolean{
        if (email.isNullOrBlank()){
            return false
        }else{
            return email.trim().matches(emailRegex)
        }
    }

    fun isValidPhoneNumber(phone: String?): Boolean{
        if (phone.isNullOrBlank()){
            return false
        }else{
            return phone.trim().matches(phoneRegex)
        }
    }
}