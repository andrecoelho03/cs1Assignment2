package pt.unl.fct.iadi.bookstore.controller.dto

data class CreateBookRequest(
    val title: String,
    val author: String,
    val price: Double,
    val image: String
)