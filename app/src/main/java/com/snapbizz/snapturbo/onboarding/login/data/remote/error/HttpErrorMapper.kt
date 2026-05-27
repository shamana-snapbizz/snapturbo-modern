package com.snapbizz.snapturbo.onboarding.login.data.remote.error

object HttpErrorMapper {

    fun map(code: Int): String {
        return when (code) {
            400 -> "Invalid request"
            401 -> "Unauthorized access"
            403 -> "Access denied"
            404 -> "Service not found"
            412 -> "Preconditon failed"
            500 -> "Server error"
            502 -> "Bad gateway"
            503 -> "Service unavailable"
            504 -> "Request timeout"
            else -> "Something went wrong ($code)"
        }
    }
}