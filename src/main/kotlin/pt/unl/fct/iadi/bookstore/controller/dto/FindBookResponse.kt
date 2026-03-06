package pt.unl.fct.iadi.bookstore.controller.dto

import pt.unl.fct.iadi.bookstore.domain.Book

data class FindBookResponse(
    val book: Book
)