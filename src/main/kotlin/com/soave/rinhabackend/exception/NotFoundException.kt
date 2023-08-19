package com.soave.rinhabackend.exception

class NotFoundException(message: String) : RuntimeException(message) {
    companion object { private const val serialVersionUID: Long = 8481847006406469617L }
}