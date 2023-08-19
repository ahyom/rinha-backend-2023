package com.soave.rinhabackend.controller.advice

import com.soave.rinhabackend.controller.PessoaController
import com.soave.rinhabackend.domain.request.ErrorRequest
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

private val logger = KotlinLogging.logger {}

@ControllerAdvice(
    assignableTypes = [
        PessoaController::class,
    ],
)
class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorRequest> {
        logger.error("Handling IllegalArgumentException: ${exception.message}", exception)
        return ResponseEntity(
            ErrorRequest(
                HttpStatus.UNPROCESSABLE_ENTITY.reasonPhrase,
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.message,
            ),
            HttpStatus.UNPROCESSABLE_ENTITY,
        )
    }
}
