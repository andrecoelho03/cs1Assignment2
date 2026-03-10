package pt.unl.fct.iadi.bookstore.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.domain.Review
import pt.unl.fct.iadi.bookstore.service.domain.ReviewInput
import pt.unl.fct.iadi.bookstore.service.domain.UpdateBookInput
import pt.unl.fct.iadi.bookstore.service.domain.UpdateReviewInput
import pt.unl.fct.iadi.bookstore.service.exception.ISBNAlreadyExists
import pt.unl.fct.iadi.bookstore.service.exception.ISBNMismatch
import pt.unl.fct.iadi.bookstore.service.exception.ISBNNotFound
import pt.unl.fct.iadi.bookstore.service.exception.NoFieldsProvided
import pt.unl.fct.iadi.bookstore.service.exception.ReviewNotFound
import pt.unl.fct.iadi.bookstore.service.exception.TooManyFieldsProvided
import java.util.UUID

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

    fun replaceBook(isbn: String, book: Book): Pair<String, Book> {
        if (isbn != book.isbn) {
            throw ISBNMismatch("ISBN in the path does not match ISBN in the body")
        }

        val existingBook = books.find { it.isbn == isbn }

        val replacedBook = Book(
            isbn = isbn,
            title = book.title,
            author = book.author,
            price = book.price,
            image = book.image
        )

        if (existingBook != null) {
            books.remove(replacedBook)
            books.add(replacedBook)
            return "REPLACED" to replacedBook
        } else {
            books.add(replacedBook)
            return "CREATED" to replacedBook
        }
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
        books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        return reviews.filter { it.isbn == isbn }
    }

    fun createBookReview(isbn: String, review: ReviewInput): Review {
        books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        val newReview = Review(
            id = UUID.randomUUID(),
            isbn = isbn,
            rating = review.rating,
            comment = review.comment
        )

        reviews.add(newReview)
        return newReview
    }

    fun replaceBookReview(isbn: String, reviewId: String, review: ReviewInput): Review {
        books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        val prevReview = reviews.find { it.id == UUID.fromString(reviewId) }
            ?: throw ReviewNotFound("Review with ID $reviewId not found for book with ISBN $isbn")

        val replacedReview = Review(
            id = UUID.fromString(reviewId),
            isbn = isbn,
            rating = review.rating,
            comment = review.comment ?: prevReview.comment
        )

        val idx = reviews.indexOf(prevReview)
        reviews[idx] = replacedReview

        return replacedReview
    }

    fun updateBookReview(isbn: String, reviewId: String, review: UpdateReviewInput): Review {
        books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")
        val prevReview = reviews.find { it.id == UUID.fromString(reviewId) }
            ?: throw ReviewNotFound("Review with ID $reviewId not found for book with ISBN $isbn")

        if (review.rating != null && review.comment != null) {
            throw TooManyFieldsProvided("Only one of rating or comment can be updated at a time")
        }

        if (review.rating == null && review.comment == null) {
            throw NoFieldsProvided("At least one of rating or comment must be provided")
        }

        // determine which field to update: the one that is not null
        val updatedReview = if (review.rating != null) {
            // update rating only
            Review(
                id = prevReview.id,
                isbn = prevReview.isbn,
                rating = review.rating,
                comment = prevReview.comment
            )
        } else {
            // update comment only
            Review(
                id = prevReview.id,
                isbn = prevReview.isbn,
                rating = prevReview.rating,
                comment = review.comment
            )
        }

        val idx = reviews.indexOf(prevReview)
        reviews[idx] = updatedReview

        return updatedReview
    }

    fun deleteBookReview(isbn: String, reviewId: String) {
        books.find { it.isbn == isbn }
            ?: throw ISBNNotFound("Book with ISBN $isbn not found")

        val prevReview = reviews.find { it.id == UUID.fromString(reviewId) }
            ?: throw ReviewNotFound("Review with ID $reviewId not found for book with ISBN $isbn")

        reviews.remove(prevReview)
    }


}