package pt.unl.fct.iadi.bookstore.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.iadi.bookstore.controller.dto.BookResponse
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.UpdateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.UpdateReviewRequest
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.service.BookstoreService
import java.util.UUID

@RestController
class BookstoreController(
    private val service: BookstoreService
): BookstoreAPI {
    override fun findAllBooks(): ResponseEntity<*> {
        val result = service.findAllBooks()

        return ResponseEntity.ok(result)
    }

    override fun createBook(book: CreateBookRequest, req: HttpServletRequest): ResponseEntity<*> {
        val result = service.createBook(book.toBook())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Location", "${req.requestURL}/${result}")
            .body(mapOf("message" to "Book created successfully"))
    }

    override fun findBook(
        isbn: String,
        acceptLanguage: String?
    ): ResponseEntity<BookResponse> {
        val result = service.findBook(isbn, acceptLanguage)

        return ResponseEntity
            .status(HttpStatus.OK)
            .header("Content-Language", acceptLanguage ?: "en")
            .body(result)
    }

    override fun replaceBook(
        isbn: String,
        book: ReplaceBookRequest
    ): ResponseEntity<*> {
        val result: Pair<String, Book> = service.replaceBook(isbn, book.toBook())
        if (result.first == "CREATED") {
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapOf("message" to "Book created successfully"))
        } else if (result.first == "REPLACED") {
            return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(mapOf("message" to "Book replaced successfully"))
        }

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(mapOf("message" to "Book replaced successfully"))
    }

    override fun updateBook(
        isbn: String,
        book: UpdateBookRequest
    ): ResponseEntity<*> {
        service.updateBook(isbn, book.toUpdateBookInput())
        return ResponseEntity.ok(mapOf("message" to "Book updated successfully"))
    }

    override fun deleteBook(isbn: String): ResponseEntity<*> {
        service.deleteBook(isbn)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(mapOf("message" to "Book deleted successfully"))
    }

    override fun findBookReviews(isbn: String): ResponseEntity<*> {
        val result = service.findBookReviews(isbn)

        return ResponseEntity.ok(result)
    }

    override fun createBookReview(
        isbn: String,
        review: CreateReviewRequest,
        req: HttpServletRequest
    ): ResponseEntity<*> {
        val result = service.createBookReview(isbn, review.toReviewInput())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Location", "${req.requestURL}/${result}")
            .body(mapOf("message" to "Book Review created successfully"))
    }

    override fun replaceBookReview(
        isbn: String,
        id: String,
        review: ReplaceReviewRequest
    ): ResponseEntity<*> {
        service.replaceBookReview(isbn, id, review.toReviewInput())

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(mapOf("message" to "Book Review replaced successfully"))
    }

    override fun updateBookReview(
        isbn: String,
        id: String,
        review: UpdateReviewRequest
    ): ResponseEntity<*> {
        service.updateBookReview(isbn, id, review.toUpdateReviewInput())

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(mapOf("message" to "Book Review updated successfully"))
    }

    override fun deleteBookReview(
        isbn: String,
        id: String
    ): ResponseEntity<*> {
        service.deleteBookReview(isbn, id)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(mapOf("message" to "Book Review deleted successfully"))
    }

}