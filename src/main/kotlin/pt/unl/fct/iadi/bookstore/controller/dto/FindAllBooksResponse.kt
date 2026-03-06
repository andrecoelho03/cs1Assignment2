package pt.unl.fct.iadi.bookstore.controller.dto

import pt.unl.fct.iadi.bookstore.domain.Book

data class FindAllBooksResponse(
    val books: List<Book>
)