package com.smartloanadvisornad.domain.utils



fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")
    return email.matches(emailRegex)
}