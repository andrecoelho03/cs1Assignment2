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

@RestController
class BookstoreController(
    private val service: BookstoreService
): BookstoreAPI {
    private fun msg(text: String) = mapOf("message" to text)
    override fun findAllBooks(): ResponseEntity<*> {
        val result = service.findAllBooks()

        return ResponseEntity.ok(result)
    }

    override fun createBook(book: CreateBookRequest, req: HttpServletRequest): ResponseEntity<Unit> {
        val result = service.createBook(book.toBook())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Location", "${req.requestURL}/${result}")
            .build()
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
                .body(msg("Book created successfully"))
        } else if (result.first == "REPLACED") {
            return ResponseEntity.ok(msg("Book replaced successfully"))
        }

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(msg("Book replaced successfully"))
    }

    override fun updateBook(
        isbn: String,
        book: UpdateBookRequest
    ): ResponseEntity<*> {
        service.updateBook(isbn, book.toUpdateBookInput())
        return ResponseEntity.ok(msg("Book updated successfully"))
    }

    override fun deleteBook(isbn: String): ResponseEntity<*> {
        service.deleteBook(isbn)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(msg("Book deleted successfully"))
    }

    override fun findReviews(isbn: String): ResponseEntity<*> {
        val result = service.findBookReviews(isbn)

        return ResponseEntity.ok(result)
    }

    override fun createReview(
        isbn: String,
        review: CreateReviewRequest,
        req: HttpServletRequest
    ): ResponseEntity<Unit> {
        val result = service.createBookReview(isbn, review.toReviewInput())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .header("Location", "${req.requestURL}/${result}")
            .build()
    }

    override fun replaceReview(
        isbn: String,
        reviewId: String,
        review: ReplaceReviewRequest
    ): ResponseEntity<*> {
        service.replaceBookReview(isbn, reviewId, review.toReviewInput())

        return ResponseEntity.ok(msg("Book replaced successfully"))
    }

    override fun updateReview(
        isbn: String,
        reviewId: String,
        review: UpdateReviewRequest
    ): ResponseEntity<*> {
        service.updateBookReview(isbn, reviewId, review.toUpdateReviewInput())

        return ResponseEntity.ok(msg("Book replaced successfully"))
    }

    override fun deleteReview(
        isbn: String,
        reviewId: String
    ): ResponseEntity<*> {
        service.deleteBookReview(isbn, reviewId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(msg("Book Review deleted successfully"))
    }

}