package com.soave.rinhabackend.domain.request

data class ErrorRequest(val error: String, val status: Int, val cause: String?)