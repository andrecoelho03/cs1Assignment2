package pt.unl.fct.iadi.bookstore.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.domain.Review
import pt.unl.fct.iadi.bookstore.service.domain.BookInput
import pt.unl.fct.iadi.bookstore.service.domain.ReviewInput

@Service
class BookstoreService {
    fun findAllBooks(): List<Book> {
        TODO()
    }
    fun createBook(book: BookInput): Book {
        TODO()
    }

    fun findBook(isbn: String): Book {
        TODO()
    }

    fun replaceBook(isbn: String, book: BookInput): Book {
        TODO()
    }

    fun updateBook(isbn: String, book: BookInput): Book {
        TODO()
    }

    fun deleteBook(isbn: String) {
        TODO()
    }

    fun findBookReviews(isbn: String): List<Review> {
        TODO()
    }

    fun createBookReview(isbn: String, review: ReviewInput): Review {
        TODO()
    }

    fun replaceBookReview(isbn: String, reviewId: Long, review: ReviewInput): Review {
        TODO()
    }

    fun updateBookReview(isbn: String, reviewId: Long, review: ReviewInput): Review {
        TODO()
    }

    fun deleteBookReview(isbn: String, reviewId: String) {
        TODO()
    }


}