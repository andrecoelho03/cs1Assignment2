package pt.unl.fct.iadi.bookstore.controller.dto

data class ReplaceBookRequest(
    val title: String,
    val author: String,
    val price: Double,
    val image: String
)