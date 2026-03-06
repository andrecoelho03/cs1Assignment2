package pt.unl.fct.iadi.bookstore.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.UpdateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.UpdateReviewRequest
import pt.unl.fct.iadi.bookstore.service.BookstoreService

@RestController
class BookstoreController(
    private val service: BookstoreService
): BookstoreAPI {
    override fun findAllBooks(): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun createBook(book: CreateBookRequest): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun findBook(isbn: String): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun replaceBook(
        isbn: String,
        book: ReplaceBookRequest
    ): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun updateBook(
        isbn: String,
        book: UpdateBookRequest
    ): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun deleteBook(isbn: String): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun findBookReviews(isbn: String): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun createBookReview(
        isbn: String,
        review: CreateReviewRequest
    ): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun replaceBookReview(
        isbn: String,
        id: String,
        review: ReplaceReviewRequest
    ): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun updateBookReview(
        isbn: String,
        id: String,
        review: UpdateReviewRequest
    ): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    override fun deleteBookReview(
        isbn: String,
        id: String
    ): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

}