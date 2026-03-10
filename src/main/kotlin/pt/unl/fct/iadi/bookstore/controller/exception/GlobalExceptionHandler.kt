package pt.unl.fct.iadi.bookstore.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.Locale

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BookException::class)
    fun handleBookNotFound(ex: BookException, locale: Locale):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("NOT_FOUND", "Book not found"))
    }
    @ExceptionHandler(BookConflictException::class)
    fun handleBookNotFound(ex: BookException, locale: Locale):
            ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("NOT_FOUND", "Book not found"))
    }
}