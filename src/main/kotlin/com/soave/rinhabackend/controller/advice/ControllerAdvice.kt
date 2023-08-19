package com.soave.rinhabackend.controller.advice

import com.soave.rinhabackend.controller.PessoaController
import com.soave.rinhabackend.domain.request.ErrorRequest
import com.soave.rinhabackend.exception.EntityAlreadyExistsException
import com.soave.rinhabackend.exception.NotFoundException
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

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorRequest> {
        logger.error("Handling NotFoundException: ${exception.message}", exception)
        return ResponseEntity(
            ErrorRequest(
                HttpStatus.NOT_FOUND.reasonPhrase,
                HttpStatus.NOT_FOUND.value(),
                exception.message,
            ),
            HttpStatus.NOT_FOUND,
        )
    }

    @ExceptionHandler(EntityAlreadyExistsException::class)
    fun handleEntityAlreadyExistsException(exception: EntityAlreadyExistsException): ResponseEntity<ErrorRequest> {
        logger.error("Handling Exception: ${exception.message}", exception)
        return ResponseEntity(
            ErrorRequest(
                HttpStatus.UNPROCESSABLE_ENTITY.reasonPhrase,
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.message,
            ),
            HttpStatus.UNPROCESSABLE_ENTITY,
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ErrorRequest> {
        logger.error("Handling Exception: ${exception.message}", exception)
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
