package pt.unl.fct.iadi.bookstore.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pt.unl.fct.iadi.bookstore.service.exception.ISBNNotFound
import pt.unl.fct.iadi.bookstore.service.exception.ISBNAlreadyExists
import pt.unl.fct.iadi.bookstore.service.exception.ISBNMismatch
import pt.unl.fct.iadi.bookstore.service.exception.NoFieldsProvided
import pt.unl.fct.iadi.bookstore.service.exception.TooManyFieldsProvided
import pt.unl.fct.iadi.bookstore.service.exception.ReviewNotFound

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ISBNNotFound::class, ReviewNotFound::class)
    fun handleNotFound(ex: ISBNNotFound): ResponseEntity<ErrorResponse> {
        val msg = ex.message ?: "Resource not found"
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("NOT_FOUND", msg))
    }

    @ExceptionHandler(ISBNAlreadyExists::class)
    fun handleConflict(ex: ISBNAlreadyExists): ResponseEntity<ErrorResponse> {
        val msg = ex.message ?: "Conflict"
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse("CONFLICT", msg))
    }

    @ExceptionHandler(ISBNMismatch::class, NoFieldsProvided::class, TooManyFieldsProvided::class)
    fun handleBadRequest(ex: ISBNMismatch): ResponseEntity<ErrorResponse> {
        val msg = ex.message ?: "Bad request"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("BAD_REQUEST", msg))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors
            .joinToString("; ") { "${it.field}: ${it.defaultMessage}" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse("VALIDATION_ERROR", errors))
    }
}