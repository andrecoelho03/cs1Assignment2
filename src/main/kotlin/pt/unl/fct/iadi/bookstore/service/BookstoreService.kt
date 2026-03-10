package pt.unl.fct.iadi.bookstore.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.exception.BookException
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.domain.Review
import pt.unl.fct.iadi.bookstore.service.domain.BookInput
import pt.unl.fct.iadi.bookstore.service.domain.ReviewInput
import pt.unl.fct.iadi.bookstore.service.domain.UpdateBookInput
import pt.unl.fct.iadi.bookstore.service.exception.ISBNAlreadyExists
import pt.unl.fct.iadi.bookstore.service.exception.ISBNMismatch
import pt.unl.fct.iadi.bookstore.service.exception.ISBNNotFound

@Service
class BookstoreService {
    val books = mutableListOf<Book>()
    val reviews = mutableListOf<Review>()

    fun findAllBooks(): List<Book> {
        return books
    }
    fun createBook(book: Book): Book {
        if(books.any { it.isbn == book.isbn }) {
            throw ISBNAlreadyExists("Book with ISBN ${book.isbn} already exists")
        }
        books.add(book)
        return book
    }

    fun findBook(isbn: String, acceptLanguage: String?): Book {
        val language = acceptLanguage ?: "en"
        val errorMessage = when (language) {
            "pt" -> "Livro com ISBN $isbn não encontrado"
            else -> "Book with ISBN $isbn not found"
        }
        return books.find { it.isbn == isbn }
            ?: throw ISBNNotFound(errorMessage)
    }

    fun replaceBook(isbn: String, book: Book): Book {
        if (isbn != book.isbn) {
            throw ISBNMismatch("ISBN in the path does not match ISBN in the body")
        }

        val existingBook = books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        val replacedBook = Book(
            isbn = isbn,
            title = book.title,
            author = book.author,
            price = book.price,
            image = book.image
        )

        books.remove(existingBook)
        books.add(replacedBook)

        return replacedBook
    }

    fun updateBook(isbn: String, book: UpdateBookInput): Book {
        val existingBook = books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        val updatedBook = Book(
            isbn = isbn,
            title = book.title ?: existingBook.title,
            author = book.author ?: existingBook.author,
            price = book.price ?: existingBook.price,
            image = book.image ?: existingBook.image
        )

        books.remove(existingBook)
        books.add(updatedBook)

        return updatedBook
    }

    fun deleteBook(isbn: String) {
        val existingBook = books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        books.remove(existingBook)
    }

    fun findBookReviews(isbn: String): List<Review> {
        val existingBook = books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")


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